package com.chanhbc.stars;

import android.graphics.Color;

import java.util.Random;

public class Star {
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int UP = 4;

    private String star = "*";
    private int orient;
    int x;
    int y;
    int size;
    int color;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    public String getStar() {
        return star;
    }

    public Star(int x, int y) {
        Random rd = new Random();
        orient = rd.nextInt(4) + 1;
        size = rd.nextInt(121) + 24;
        this.x = x;
        this.y = y;
        color = Color.rgb(rd.nextInt(256), rd.nextInt(256), rd.nextInt(256));
    }

    public void move(int width, int height) {
        switch (orient) {
            case DOWN:
                if (y == height) {
                    y = 0;
                }
                y++;
                break;
            case LEFT:
                if (x == 0) {
                    x = width;
                }
                x--;
                break;
            case RIGHT:
                if (x == width) {
                    x = 0;
                }
                x++;
                break;
            case UP:
                if (y == 0) {
                    y = height;
                }
                y--;
                break;
        }
    }

}
