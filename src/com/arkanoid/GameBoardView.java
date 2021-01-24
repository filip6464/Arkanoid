package com.arkanoid;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameBoardView extends View implements KeyListener {

    private Ball ball;
    private Paddle paddle;
    private Brick[] bricks;
    private int score;
    private int lives;
    private boolean win;
    private boolean showInstruction;
    private boolean isPause;
    private Font fontScoreLives;

    private Font fontInstruction;
    private String instruction;


    int n_bricks = Config.BRICKS_ROW_COUNT * Config.BRICKS_COLUMN_COUNT;

    GameBoardView() {
        init();
    }


    protected void init() {
        showInstruction = false;
        fontInstruction = new Font("Verdana", Font.BOLD, 20);
        fontScoreLives = new Font("Verdana", Font.BOLD, 24);
        instruction = "Press ESC to continue";
        resetGame();
    }

    public boolean isWin() {
        return win;
    }

    public int getScore() {
        return score;
    }

    private void decrementLives() {
        this.lives--;
    }

    private void incrementScore() {
        this.score++;
    }

    private void resetScore() {
        score = 0;
    }

    private void resetLives() {
        lives = 3;
    }

    public void resetGame() {
        setIsFirstTime(true);
        win = false;
        resetLives();
        resetScore();
        bricks = new Brick[n_bricks];
        ball = new Ball();
        paddle = new Paddle();

        int k = 0;

        for (int i = 0; i < Config.BRICKS_ROW_COUNT; i++) {

            for (int j = 0; j < Config.BRICKS_COLUMN_COUNT; j++) {
                //Dodawanie kolejnych bloczków i obliczanie ich pozycji,wielkości
                bricks[k] = new Brick(j * 60 + 40, i * 20 + 80, i);
                k++;
            }
        }

    }

    public void draw(Graphics2D g2d) {

        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                ball.getImageWidth(), ball.getImageHeight(), this);
        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);

        for (int i = 0; i < n_bricks; i++) {

            if (!bricks[i].isDestroyed()) {

                g2d.drawImage(bricks[i].getImage(), bricks[i].getX(),
                        bricks[i].getY(), bricks[i].getImageWidth(),
                        bricks[i].getImageHeight(), this);
            }
        }

        g2d.setColor(Color.BLACK);
        g2d.setFont(fontScoreLives);
        g2d.drawString("Score: " + score,
                Config.WINDOW_WIDTH - (Config.WINDOW_WIDTH) / 4,
                Config.WINDOW_HEIGHT / 12);
        g2d.drawString("Lives: " + lives,
                (Config.WINDOW_WIDTH) / 10,
                Config.WINDOW_HEIGHT / 12);

        if (isPause) {
            if (instructionVisible) {
                FontMetrics fontMetrics3 = this.getFontMetrics(fontInstruction);
                g2d.setColor(Color.BLACK);
                g2d.setFont(fontInstruction);
                g2d.drawString(instruction,
                        (Config.WINDOW_WIDTH - fontMetrics3.stringWidth(instruction)) / 2,
                        Config.WINDOW_HEIGHT - Config.WINDOW_HEIGHT / 2);
            }
            animationTimer();
        }
    }

    public void doGameboardCycle() {
        if (!isPause) {
            ball.move();
            paddle.move();
            checkDead();
            checkWin();
            checkCollision();
        }
    }

    private void endGame() {
        //podliczanie punktow za nie zuzyte zycia
        score += lives * 10;
        setIsFirstTime(true);
        Display.changeGameState(GAME_STATE.GAMEOVER);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key){
                case KeyEvent.VK_ESCAPE -> {
                    if (isPause)
                        isPause = false;
                    else
                        isPause = true;
                }
            case KeyEvent.VK_1 -> {
                if (!isPause)
                    breakAll();
            }

            case KeyEvent.VK_Q -> {
                if (!isPause)
                    endGame();
            }
            default -> {
                if (!isPause)
                    paddle.keyPressed(e);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        paddle.keyReleased(e);
    }

    private boolean isBrickCollision(Brick brick) {
        boolean result = false;
        result = ball.getImageBoundaryRectangle().intersects(brick.getImageBoundaryRectangle());
        return result;
    }

    private boolean isPaddleCollision() {
        boolean result = false;
        result = ball.getImageBoundaryRectangle().intersects(paddle.getImageBoundaryRectangle());
        return result;
    }

    private void breakAll() {
        for (int i = 0; i < n_bricks; i++) {
            if (!bricks[i].isDestroyed()) {
                incrementScore();
                bricks[i].setisDestroyed(true);
            }
        }
    }

    private void checkDead() {
        //Jeśli kulka wyleci poza dolna granice plaszny, następuje śmierć

        if (ball.getImageBoundaryRectangle().getMaxY() > Config.BOTTOM_EDGE) {
            if (lives > 1) {
                SoundPlayer.playSound(SoundPlayer.BLOCK_BREAK);
                decrementLives();
                isPause = true;
                ball.resetState();
                paddle.resetState();
            } else {
                decrementLives();
                endGame();
            }
        }
    }

    private void checkWin() {
        for (int i = 0, j = 0; i < n_bricks; i++) {

            //Zliczanie zbitych bloczków
            if (bricks[i].isDestroyed())
                j++;

            //Jeśli wszystkie bloczki są zbite, to koniec gry
            if (j == n_bricks) {
                win = true;
                endGame();
            }
        }
    }

    private void checkCollision() {

        //Jeśli kulka zderza się z platformą
        if (isPaddleCollision()) {
            int paddleLPos = (int) paddle.getImageBoundaryRectangle().getMinX();
            //Kulka odbija się od paletki, w zależności od miejsca uderzenia w paletkę
            ball.bounceOffPaddle(paddleLPos);
        }
        //Kolizje z bloczkami
        for (int i = 0; i < n_bricks; i++) {
            //Jeśli wystąpi kolizja bloczka
            if (isBrickCollision(bricks[i])) {
                if (!bricks[i].isDestroyed()) {
                    SoundPlayer.playSound(SoundPlayer.PADDLE_COLISION);
                    ball.bounceOffBrick(bricks[i]);
                    bricks[i].setisDestroyed(true);
                    incrementScore();
                }

            }
        }
    }
}

