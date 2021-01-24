package com.arkanoid;

import javax.swing.ImageIcon;
import java.awt.*;
import java.util.Random;

public class Ball extends GameObject {

    private double X_Axis_Speed;
    private double Y_Axis_Speed;

    public Ball() {
        loadImage();
        resetState();
    }

    public Ball(int x, int y,double x_Axis_Speed, double y_Axis_Speed) {
        loadImage();
        resetState();
        this.x = x;
        this.y =y;
        this.X_Axis_Speed = x_Axis_Speed;
        this.Y_Axis_Speed = y_Axis_Speed;
    }

    @Override
    protected void loadImage() {
        var ii = new ImageIcon("src/resources/ball.png");
        image = ii.getImage();
    }

    public void move() {
        x += X_Axis_Speed;
        y += Y_Axis_Speed;

        //Odbicie od ściany lewej
        if (x <= 0) {
            setX_Axis_Speed(1);
        }

        //Odbicie od ściany prawej
        if (x >= Config.WINDOW_WIDTH - imageWidth) {
            setX_Axis_Speed(-1*X_Axis_Speed);
        }

        //Odbicie od sufitu
        if (y <= 0) {
            setY_Axis_Speed(-1*Y_Axis_Speed);
        }
    }

    public void bounceOffPaddle(double paddleX){
        double ballRectanglePos = getImageBoundaryRectangle().getMinX();

        int first = (int) paddleX + 10;
        int second = (int) paddleX + 33;
        int third = (int) paddleX + 37;
        int fourth = (int) paddleX + 60;

        //Odbicie od lewej scianki platformy strefa 1
        if (ballRectanglePos < first) {
            X_Axis_Speed = -2;
            Y_Axis_Speed = -2;
        }
        //Odbicie od lewej części platformy strefa 2
        if (ballRectanglePos >= first && x < second) {
            X_Axis_Speed = -1;
            Y_Axis_Speed = -2;
        }
        //Odbicie od środkowej części platformy strefa 3
        if (ballRectanglePos >= second && x < third) {
            Y_Axis_Speed = -1;
        }
        //Obcicie od prawej części platformy strefa 4
        if (ballRectanglePos >= third && ballRectanglePos < fourth) {
            X_Axis_Speed = 1;
            Y_Axis_Speed = -2;
        }
        //Odbicie od prawej ścianki platformy strefa 5
        if (ballRectanglePos > fourth) {
            X_Axis_Speed = 2;
            Y_Axis_Speed = -2;
        }
    }

    public void bounceOffBrick(Brick brick) {
        double ballRectanglePos = getImageBoundaryRectangle().getMinX();

        int ballLeft = (int) getImageBoundaryRectangle().getMinX();
        int ballHeight = (int) getImageBoundaryRectangle().getHeight();
        int ballWidth = (int) getImageBoundaryRectangle().getWidth();
        int ballTop = (int) getImageBoundaryRectangle().getMinY();

        var pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
        var pointLeft = new Point(ballLeft - 1, ballTop);
        var pointTop = new Point(ballLeft, ballTop - 1);
        var pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

        if (brick.getImageBoundaryRectangle().contains(pointTop) || brick.getImageBoundaryRectangle().contains(pointBottom)) {
            //odbicie od górnej lub od dolnej ścianki bloczka
            Y_Axis_Speed *= -1;
        }
        if (brick.getImageBoundaryRectangle().contains(pointRight) && !brick.getImageBoundaryRectangle().contains(pointLeft)) {
            //odbicie od prawej ścianki bloczka
            X_Axis_Speed = -1;
        } else if (!brick.getImageBoundaryRectangle().contains(pointRight) && brick.getImageBoundaryRectangle().contains(pointLeft)) {
            //odbicie od lewej ścianki bloczka
            X_Axis_Speed = 1;
        }
    }

    @Override
    protected void resetState() {
        x = Config.BALL_START_X;
        y = Config.BALL_START_Y;
        X_Axis_Speed = Config.BALL_START_X_Axis_Speed;
        Y_Axis_Speed = Config.BALL_START_Y_Axis_Speed;
        this.setDefaultImageDimensions();
    }

    public double getX_Axis_Speed() {
        return X_Axis_Speed;
    }

    public void setX_Axis_Speed(double x_Axis_Speed) {
        X_Axis_Speed = x_Axis_Speed;
    }

    public double getY_Axis_Speed() {
        return Y_Axis_Speed;
    }

    public void setY_Axis_Speed(double y_Axis_Speed) {
        Y_Axis_Speed = y_Axis_Speed;
    }
}
