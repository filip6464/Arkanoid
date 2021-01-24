package com.arkanoid;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverView extends View {

    private String endGameMessage;
    private boolean isFirstTime;
    private String score;

    private Font fontTitle;
    private Font fontScore;
    private Font fontInstruction;
    private String instruction;

    public GameOverView() {
        init();
    }

    @Override
    protected void init() {
        //setFocusable(false);
        timer = 0;
        instructionVisible = true;
        isFirstTime = true;
        endGameMessage = "Game Over";
        instruction = "Press space to restart...";
        this.score = "Score: "+String.valueOf(-1);
        fontTitle = new Font("Verdana", Font.BOLD, 30);
        fontScore = new Font("Verdana", Font.ITALIC, 24);
        fontInstruction = new Font("Verdana", Font.BOLD, 20);
    }

    public void setValues(int score, boolean isWin){
        this.score = "Score: "+String.valueOf(score);
        if(isWin){
            this.endGameMessage = "Win!";
        }else {
            this.endGameMessage = "Game Over";
        }
    }

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public void setIsFirstTime(boolean firstScreen) {
        isFirstTime = firstScreen;
    }

    public void draw(Graphics2D g2d) {

        FontMetrics fontMetrics1 = this.getFontMetrics(fontTitle);
        FontMetrics fontMetrics2 = this.getFontMetrics(fontScore);
        FontMetrics fontMetrics3 = this.getFontMetrics(fontInstruction);

        g2d.setColor(Color.BLUE);
        g2d.setFont(fontTitle);
        g2d.drawString(endGameMessage,
                (Config.WINDOW_WIDTH - fontMetrics1.stringWidth(endGameMessage)) / 2,
                Config.WINDOW_HEIGHT / 2);
        g2d.setColor(Color.BLACK);
        g2d.setFont(fontScore);
        g2d.drawString(score,
                (Config.WINDOW_WIDTH - fontMetrics2.stringWidth(score)) / 2,
                Config.WINDOW_HEIGHT - (Config.WINDOW_HEIGHT / 3));
        if(instructionVisible) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(fontInstruction);
            g2d.drawString(instruction,
                    (Config.WINDOW_WIDTH - fontMetrics3.stringWidth(instruction)) / 2,
                    Config.WINDOW_HEIGHT - (Config.WINDOW_HEIGHT/ 8));
        }
        animationTimer();
    }

    private void backToMenu(){
        isFirstTime = true;
        Display.changeGameState(GAME_STATE.INMENU);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            backToMenu();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
