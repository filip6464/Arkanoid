package com.arkanoid;

public interface Config {

    //Dont change these values

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;
    public static final int BOTTOM_EDGE = (int) (WINDOW_HEIGHT - (0.1*WINDOW_HEIGHT));
    public static final int BRICKS_ROW_COUNT = 4;
    public static final int BRICKS_COLUMN_COUNT = 12;
    public static final int PADDLE_START_X = WINDOW_WIDTH/2;
    public static final int PADDLE_START_Y = (int) (WINDOW_HEIGHT - (0.2*WINDOW_HEIGHT));
    public static final int BALL_START_X = (int) 20+WINDOW_WIDTH/2;
    public static final int BALL_START_Y = (int) (WINDOW_HEIGHT - (0.2*WINDOW_HEIGHT)-6);
    public static final int DISPLAY_PERIOD = 7;
    public static final double BALL_START_X_Axis_Speed = 2;
    public static final double BALL_START_Y_Axis_Speed = -2;
    public static final int PADDLE_START_X_Axis_Speed = 3;

}
