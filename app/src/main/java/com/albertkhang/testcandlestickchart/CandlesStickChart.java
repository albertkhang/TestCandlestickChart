package com.albertkhang.testcandlestickchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;

public class CandlesStickChart extends View {
    private Context mContext;

    public CandlesStickChart(Context context) {
        super(context);
        mContext = context;
    }

    final Paint candlePaint;

    {
        candlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        circlePaint.setAntiAlias(true);
//        circlePaint.setStrokeWidth(100);
        candlePaint.setColor(Color.RED);
        candlePaint.setStyle(Paint.Style.FILL);
    }

    private Paint mRenderPaint;
    private float highest;
    private float lowest;
    private float avg;

    @Override
    protected void onDraw(Canvas canvas) {
        mRenderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRenderPaint.setColor(Color.DKGRAY);
        mRenderPaint.setStyle(Paint.Style.STROKE);
        mRenderPaint.setStrokeWidth(2f);

        ArrayList<CandleEntry> values = MainActivity.values;
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).getmShadowHigh() > highest)
                highest = values.get(i).getmShadowHigh();

            if (values.get(i).getmShadowLow() < lowest)
                lowest = values.get(i).getmShadowLow();
        }
        avg = (highest + lowest) / 2f;

        drawBaseAxis(canvas);
        drawGridBackground(canvas);
        drawShadow(canvas);
        drawData(canvas);
    }

    private void drawShadow(Canvas c) {
        Utils utils = new Utils(mContext);
        ArrayList<CandleEntry> values = MainActivity.values;

        Paint mShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShadowPaint.setColor(Color.BLUE);
        mShadowPaint.setStyle(Paint.Style.STROKE);
        mShadowPaint.setStrokeWidth(6f);

        int xPos;
        float open;
        float close;
        float high;
        float low;
        for (int i = 1; i < values.size(); i++) {
            xPos = mMarginLeft + i * mBarWidth;

            open = values.get(i).getmOpen();
            close = values.get(i).getmClose();
            high = values.get(i).getmShadowHigh();
            low = values.get(i).getmShadowLow();

            mShadowBuffers[0] = xPos;
            mShadowBuffers[2] = xPos;
            mShadowBuffers[4] = xPos;
            mShadowBuffers[6] = xPos;

            mShadowBuffers[1] = open + 200 + avg;
            mShadowBuffers[3] = mShadowBuffers[1] - high;
            mShadowBuffers[5] = close * 10 + avg;
            mShadowBuffers[7] = mShadowBuffers[5] + low;

            utils.convertDpToPx(mShadowBuffers);

            c.drawLines(mShadowBuffers, mShadowPaint);
        }
    }

    private void drawBaseAxis(Canvas c) {
        Utils utils = new Utils(mContext);

        float density = getResources().getDisplayMetrics().density;
        float yPos = this.getMeasuredHeight() / density;

        mAxisBuffers[0] = mMarginLeft;
        mAxisBuffers[1] = mMarginLeft;
        mAxisBuffers[2] = mMarginLeft;
        mAxisBuffers[3] = yPos - mMarginLeft;
        Log.d("baseaxis", "[0]: " + mAxisBuffers[0] + ", [1]: " + mAxisBuffers[1] + ", [2]: " + mAxisBuffers[2] + ", [3]: " + mAxisBuffers[3]);

        utils.convertDpToPx(mAxisBuffers);

        Log.d("baseaxis", "[0]: " + mAxisBuffers[0] + ", [1]: " + mAxisBuffers[1] + ", [2]: " + mAxisBuffers[2] + ", [3]: " + mAxisBuffers[3]);

        c.drawLines(mAxisBuffers, mRenderPaint);

        mAxisBuffers[0] = mMarginLeft;
        mAxisBuffers[1] = yPos - mMarginLeft;
        mAxisBuffers[2] = utils.getMaxWidthDp() - mMarginLeft;
        mAxisBuffers[3] = yPos - mMarginLeft;

        utils.convertDpToPx(mAxisBuffers);
        c.drawLines(mAxisBuffers, mRenderPaint);
    }

    private float[] mBackgroundBuffers = new float[4];
    private float[] mShadowBuffers = new float[8];
    private float[] mAxisBuffers = new float[4];
    private float[] mBodyBuffers = new float[4];
    private int mMarginLeft = 20;
    private int mMarginTop = 10;
    private int mBarWidth = 50;

    private int mSize = 10;

    private void drawGridBackground(Canvas canvas) {
        Utils utils = new Utils(mContext);

        int xPos;
        float density = getResources().getDisplayMetrics().density;
        float yPos = this.getMeasuredHeight() / density;
        Log.d("measure", "yPos height: " + yPos);

        for (int i = 0; i < mSize; i++) {
            xPos = mMarginLeft + i * mBarWidth;

            mBackgroundBuffers[0] = xPos;
            mBackgroundBuffers[1] = mMarginTop;
            mBackgroundBuffers[2] = xPos;
            mBackgroundBuffers[3] = yPos - mMarginLeft;

            utils.convertDpToPx(mBackgroundBuffers);

            canvas.drawLines(mBackgroundBuffers, mRenderPaint);
        }
    }

    private void drawData(Canvas canvas) {
        Utils utils = new Utils(mContext);
        ArrayList<CandleEntry> values = MainActivity.values;

        int xPos;
        float open;
        float close;
        for (int i = 1; i < values.size(); i++) {
            xPos = mMarginLeft + i * mBarWidth;

            open = values.get(i).getmOpen();
            close = values.get(i).getmClose();

            mBodyBuffers[0] = xPos - mBarWidth / 2 + mMarginLeft;//left
            mBodyBuffers[1] = values.get(i).getmOpen() + 200 + avg;//top
            mBodyBuffers[2] = xPos + mBarWidth / 2 - mMarginLeft;//right
            mBodyBuffers[3] = values.get(i).getmClose() * 10 + avg;//bottom

            utils.convertDpToPx(mBodyBuffers);

            if (open > close)
                candlePaint.setColor(Color.RED);
            else if (open < close)
                candlePaint.setColor(Color.GREEN);
            else
                candlePaint.setColor(Color.BLUE);

            canvas.drawRect(mBodyBuffers[0], mBodyBuffers[1], mBodyBuffers[2], mBodyBuffers[3], candlePaint);
        }
    }
}
