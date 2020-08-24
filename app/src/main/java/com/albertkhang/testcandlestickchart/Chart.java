package com.albertkhang.testcandlestickchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.MotionEventCompat;

public class Chart extends View {
    private static final String TAG = "Chart";

    public Chart(Context context) {
        super(context);
    }

    public Chart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Chart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Chart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private Paint paint = new Paint();

    private boolean isDragX = true;
    private boolean isDragY = false;

    private int mMaxXGrid = 20;
    private int mMaxYGrid = 20;

    private int mSpacing = 150;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.d(TAG, "onDraw");

        paint.setColor(getResources().getColor(R.color.colorPrimaryDark));
        paint.setStrokeWidth(2);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(40);

        canvas.save();

        //draw X label
        canvas.clipRect(45, 45, 1035, 2000);
        for (int i = 0; i < mMaxXGrid; i++) {
            canvas.drawText("" + i, mPosX + mSpacing * i, 1825, paint);
        }

        canvas.restore();

        //draw Y label
        canvas.clipRect(0, 0, 1035, 2000);
        for (int i = 0; i < mMaxYGrid; i++) {
            canvas.drawText("" + i, 75 - 25, mPosY + mSpacing * (11 - i), paint);
        }

        //draw base axis
        canvas.drawLine(75, 45, 75, 1785, paint);
        canvas.drawLine(75, 1785, 1035, 1785, paint);

        canvas.save();

        canvas.clipRect(75, 45, 1035, 1785);

        //draw X grid
        for (int i = 1; i < mMaxXGrid; i++) {
            canvas.drawLine(mPosX + mSpacing * i, 75, mPosX + mSpacing * i, 75 + 1785, paint);
        }
        //draw Y grid
        for (int i = 1; i < mMaxYGrid - 1; i++) {
            canvas.drawLine(75, mPosY + mSpacing * i, 75 + 1000, mPosY + mSpacing * i, paint);
        }

        canvas.restore();
    }

    private float mPosX = 75, mPosY = 75;
    private float mLastTouchX, mLastTouchY;
//    private int mActivePointerId = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION_DOWN");

//                int pointerIndex = event.getActionIndex();
                float x = event.getX();
                float y = event.getY();

//                Log.d(TAG, "ACTION_DOWN pointerIndex: " + pointerIndex + ", x: " + x + ", y: " + y);
//                Log.d(TAG, "ACTION_DOWN pointerIndex: " + pointerIndex + ", getx: " + event.getX() + ", gety: " + event.getY());
//                Log.d(TAG, "ACTION_DOWN pointerIndex: " + pointerIndex + ", rawx: " + event.getRawX() + ", rawy: " + event.getRawY());

                // Remember where we started (for dragging)
                mLastTouchX = x;
                mLastTouchY = y;
                // Save the ID of this pointer (for dragging)
//                mActivePointerId = event.getPointerId(0);

                Log.d(TAG, "ACTION_DOWN mLastTouchX: " + mLastTouchX + ", mLastTouchY: " + mLastTouchY);
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "ACTION_MOVE x: " + event.getX() + ", y: " + event.getY());

                // Find the index of the active pointer and fetch its position
//                pointerIndex = event.findPointerIndex(mActivePointerId);

                x = event.getX();
                y = event.getY();

//                Log.d(TAG, "ACTION_MOVE pointerIndex: " + pointerIndex + ", x: " + x + ", y: " + y);

                // Calculate the distance moved
                float dx = x - mLastTouchX;
                float dy = y - mLastTouchY;

                if (isDragX) {
                    mPosX += dx;
                    if (mPosX >= 75) {
                        mPosX = 75;
                    }

                    if (mPosX < -1875) {
                        mPosX = -1875;
                    }
                }

                if (isDragY)
                    mPosY += dy;

                invalidate();

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;

                break;
        }

        return true;
    }
}
