package com.arkanoid;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuView extends View {

    private String instruction;
    private String title;

    public MenuView() {
        init();
    }

    @Override
    protected void init() {
        timer=0;
        title = "Arkanoid";
        instruction = "Press space to start...";
        instructionVisible = true;
    }

    @Override
    public void draw(Graphics2D g2d) {
        var font_tittle = new Font("Verdana", Font.BOLD, 38);
        var font_instruction = new Font("Verdana", Font.BOLD & Font.ITALIC, 26);

        FontMetrics fontMetrics1 = this.getFontMetrics(font_tittle);
        g2d.setColor(Color.BLUE);
        g2d.setFont(font_tittle);
        g2d.drawString(title,
                (Config.WINDOW_WIDTH - fontMetrics1.stringWidth(title)) / 2,
                Config.WINDOW_HEIGHT- Config.WINDOW_HEIGHT / 2);

        FontMetrics fontMetrics2 = this.getFontMetrics(font_instruction);

        if(instructionVisible) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(font_instruction);
            g2d.drawString(instruction,
                    (Config.WINDOW_WIDTH - fontMetrics2.stringWidth(instruction)) / 2,
                    Config.WINDOW_HEIGHT- Config.WINDOW_HEIGHT / 6);
        }
        animationTimer();
    }

    private void startGame(){
        setIsFirstTime(true);
        Display.changeGameState(GAME_STATE.INGAME);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            startGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}



