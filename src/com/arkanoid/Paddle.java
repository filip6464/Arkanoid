package com.arkanoid;

import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Paddle extends GameObject {

    private int X_Axis_Speed;

    public Paddle() {
        loadImage();
        resetState();
    }

    public void loadImage() {
        
        var ii = new ImageIcon("src/resources/paddle.png");
        image = ii.getImage();        
    }    

    public void move() {
        x += X_Axis_Speed;

        if (x <= 0) {
            x = 0;
        }

        if (x >= Config.WINDOW_WIDTH - imageWidth) {
            x = Config.WINDOW_WIDTH - imageWidth;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            X_Axis_Speed = -1 * Config.PADDLE_START_X_Axis_Speed;
        }
        if (key == KeyEvent.VK_RIGHT) {
            X_Axis_Speed = 1 * Config.PADDLE_START_X_Axis_Speed;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            X_Axis_Speed = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            X_Axis_Speed = 0;
        }
    }

    public void resetState() {
        x = Config.PADDLE_START_X;
        y = Config.PADDLE_START_Y;
        setDefaultImageDimensions();
        X_Axis_Speed = 0;
    }
}
