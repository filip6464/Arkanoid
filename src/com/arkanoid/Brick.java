package com.arkanoid;

import javax.swing.ImageIcon;

public class Brick extends GameObject {

    public enum COLOR{
        BLUE ,
        YELLOW ,
        RED ,
        GREEN;
    }

    private boolean destroyed;
    private String imageSrc;

    public Brick(int x, int y, int color) {
        this.x = x;
        this.y = y;
        switch (color){
            case 0 -> imageSrc = "src/resources/brick1.png";
            case 1 -> imageSrc = "src/resources/brick3.png";
            case 2 -> imageSrc = "src/resources/brick2.png";
            case 3 -> imageSrc = "src/resources/brick4.png";
            default -> imageSrc = "src/resources/brick1.png";
        }
        loadImage();
        resetState();
    }

    @Override
    protected void resetState() {
        this.destroyed = false;
        setDefaultImageDimensions();
    }

    @Override
    protected void loadImage() {
        var ii = new ImageIcon(imageSrc);
        image = ii.getImage();
    }


    boolean isDestroyed() {
        return destroyed;
    }

    void setisDestroyed(boolean value) {
        destroyed = value;
    }
}
