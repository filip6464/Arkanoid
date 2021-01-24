package com.arkanoid;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int imageWidth;
    protected int imageHeight;
    protected Image image;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getImageBoundaryRectangle() {
        return new Rectangle(x, y,
                image.getWidth(null), image.getHeight(null));
    }

    protected void setDefaultImageDimensions() {
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }

    protected abstract void resetState();

    protected abstract void loadImage();

}
