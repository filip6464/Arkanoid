package com.arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class View extends JPanel implements KeyListener {

    protected int timer;
    protected boolean instructionVisible;

    protected abstract void init();

    private boolean isFirstTime = true;

    public boolean isFirstTime() {
        return isFirstTime;
    }

    public void setIsFirstTime(boolean firstTime) {
        isFirstTime = firstTime;
    }

    public abstract void draw(Graphics2D g2d);

    protected void animationTimer(){
        if(timer==100){
            if(instructionVisible)
                instructionVisible=false;
            else
                instructionVisible=true;
            timer=0;
        }
        timer++;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
