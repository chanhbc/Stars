package com.chanhbc.stars;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class Sky extends View implements Runnable {
    private Paint paint;
    private ArrayList<Star> stars;
    private int widthScreen;
    private int heightScreen;
    private int numberStar;

    public Sky(Context context) {
        super(context);
        setup();
    }

    public Sky(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public Sky(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    private void setup() {
        paint = new Paint();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        numberStar = 48;
        widthScreen = metrics.widthPixels;
        heightScreen = metrics.heightPixels;
        stars = new ArrayList<Star>();
        Random rd = new Random();
        for (int i = 0; i < numberStar; i++) {
            stars.add(new Star(rd.nextInt(widthScreen), rd.nextInt(heightScreen)));
        }

        (new Thread(this)).start();
    }

    public void setNumberStar(int numberStar) {
        this.numberStar = numberStar;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < stars.size(); i++) {
            Star star = stars.get(i);
            paint.setColor(star.getColor());
            paint.setTextSize(star.getSize());
            canvas.drawText(star.getStar(), star.getX(), star.getY(), paint);
        }
    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < stars.size(); i++) {
                stars.get(i).move(widthScreen, heightScreen);
            }
            postInvalidate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
