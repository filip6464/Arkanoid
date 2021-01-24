package com.arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Display extends JPanel implements ActionListener {

    private GameBoardView gameBoardView;
    private MenuView menuView;
    private GameOverView gameOverView;
    private Timer loopTimer;

    private static GAME_STATE active_gamestate = GAME_STATE.INMENU;

    public Display() {
        init();
    }

    private void init() {
        setFocusable(true);
        setPreferredSize(new Dimension(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        menuView = new MenuView();
        gameBoardView = new GameBoardView();
        gameOverView = new GameOverView();
        loopTimer = new Timer(Config.DISPLAY_PERIOD, this);
        loopTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainLoop();
    }

    private synchronized void mainLoop() {
        switch (active_gamestate) {
            case INMENU -> {
                if (menuView.isFirstTime()) {
                    removeKeyListener(gameOverView);
                    addKeyListener(menuView);
                    SoundPlayer.playSound(SoundPlayer.GAME_START);
                    menuView.setIsFirstTime(false);
                }
            }
            case INGAME -> {
                if (gameBoardView.isFirstTime()) {
                    gameBoardView.resetGame();
                    removeKeyListener(menuView);
                    addKeyListener(gameBoardView);
                    gameBoardView.setIsFirstTime(false);
                }
                gameBoardView.doGameboardCycle();
            }
            case GAMEOVER -> {
                if (gameOverView.isFirstTime()) {
                    removeKeyListener(gameBoardView);
                    addKeyListener(gameOverView);
                    if (gameBoardView.isWin())
                        SoundPlayer.playSound(SoundPlayer.WIN);
                    else
                        SoundPlayer.playSound(SoundPlayer.GAME_OVER);
                    gameOverView.setValues(gameBoardView.getScore(), gameBoardView.isWin());
                    gameOverView.setIsFirstTime(false);
                }
            }

        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        var g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        switch (active_gamestate) {
            case INMENU -> {
                menuView.draw(g2d);
                break;
            }
            case INGAME -> {
                gameBoardView.draw(g2d);
                break;
            }
            case GAMEOVER -> {
                gameOverView.draw(g2d);
                break;
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    public static GAME_STATE getActive_gamestate() {
        return active_gamestate;
    }

    public static void changeGameState(GAME_STATE activeGamestate) {
        active_gamestate = activeGamestate;
    }
}
