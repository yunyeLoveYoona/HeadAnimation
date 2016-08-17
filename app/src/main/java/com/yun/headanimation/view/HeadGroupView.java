package com.yun.headanimation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dell on 2016/8/16.
 */
public class HeadGroupView extends ViewGroup {
    private final static int PADDING = 20;
    private float x;
    private int height, width;
    private int moveX;
    private int center = 1;

    public HeadGroupView(Context context) {
        super(context);
    }

    public HeadGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        height = b - t;
        width = r - l;
        move(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                move((int) (x - event.getX()) / 2);
                x = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                int deviation = (moveX % (width / 2 - height / 6 - PADDING));
                if (deviation > (width / 2 - height / 6 - PADDING) / 2) {
                    moveX = moveX + (width / 2 - height / 6 - PADDING - deviation);
                } else {
                    moveX = moveX - deviation;
                }

                move(0);
                break;
        }
        return true;
    }

    private void move(int x) {
        moveX = moveX + x;
        center = moveX / ((width - PADDING * 2) / 3) + 1;
        int startX = PADDING - moveX;
        int centerX = width / 2 - height / 6;
        if (startX < centerX && startX > 1 - getChildCount() * (width / 3)) {
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                if (startX > PADDING + height / 3 && startX < width - (PADDING + height / 3)) {
                    int sale = 0;
                    if (startX < centerX) {
                        sale = (startX - (PADDING)) / 6;
                    } else {
                        sale = ((width - PADDING) - startX) / 6;
                    }
                    child.layout(startX - sale, height / 3 - sale, startX + height / 3 + sale, height / 3 * 2 + sale);
                } else {
                    child.layout(startX, height / 3, startX + height / 3, height / 3 * 2);
                }
                startX = startX + width / 2 - PADDING - height / 6;
            }
        }
    }
}
