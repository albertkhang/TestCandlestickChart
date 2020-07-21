package com.albertkhang.testcandlestickchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class CandlestickChart extends View {
    private static final String TAG = "CandlestickChart";

    private CandlestickSettings mSettings;
    private ArrayList<CandleItem> mItems;
    private BaseAxis mBaseAxis;
    private Utils mUtils;

    private float mXPos;
    private float mYPos;

    private float[] mBaseAxisBuffer = new float[8];
    private Paint mBaseAxisPaint;

    private float[] mGridBackgroundBuffer = new float[4];
    private Paint mGridBackgroundPaint;

    public CandlestickChart(Context context) {
        super(context);
        mUtils = new Utils(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mXPos = mBaseAxis.getBaseXAxis();
        mYPos = mBaseAxis.getBaseYAxis();

        drawBaseAxis(canvas);
        drawGridBackground(canvas);
    }

    private void drawGridBackground(Canvas c) {
        mGridBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGridBackgroundPaint.setStrokeWidth(mSettings.getGridWidth());
        mGridBackgroundPaint.setColor(mUtils.getColorInt(mSettings.getGridColorId()));

        int maxGrid = mBaseAxis.getMaxXGrid();
//        Log.d(TAG, "maxXGrid: " + (maxGrid - 1));

        for (int i = 1; i < maxGrid; i++) {
            mGridBackgroundBuffer[0] = mXPos + mSettings.getGridMargin() * i;
            mGridBackgroundBuffer[1] = 0;

            mGridBackgroundBuffer[2] = mXPos + mSettings.getGridMargin() * i;
            mGridBackgroundBuffer[3] = mYPos;

            mUtils.convertDpToPixel(mGridBackgroundBuffer);
            c.drawLines(mGridBackgroundBuffer, mGridBackgroundPaint);
        }

        maxGrid = mBaseAxis.getMaxYGrid();
//        Log.d(TAG, "maxYGrid: " + (maxGrid - 1));
        float measuredWidth = getMeasuredWidth() / mUtils.getDensity();

        for (int i = 1; i < maxGrid; i++) {
            mGridBackgroundBuffer[0] = mXPos;
            mGridBackgroundBuffer[1] = mYPos - mSettings.getGridMargin() * i;

            mGridBackgroundBuffer[2] = measuredWidth;
            mGridBackgroundBuffer[3] = mYPos - mSettings.getGridMargin() * i;

            mUtils.convertDpToPixel(mGridBackgroundBuffer);
            c.drawLines(mGridBackgroundBuffer, mGridBackgroundPaint);
        }
    }

    private void drawBaseAxis(Canvas c) {
        mBaseAxisBuffer[0] = mXPos;
        mBaseAxisBuffer[1] = 0;

        mBaseAxisBuffer[2] = mXPos;
        mBaseAxisBuffer[3] = mYPos;

        mBaseAxisBuffer[4] = mXPos;
        mBaseAxisBuffer[5] = mYPos;

        mBaseAxisBuffer[6] = getMeasuredWidth() / mUtils.getDensity();
        mBaseAxisBuffer[7] = mYPos;

        mBaseAxisPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBaseAxisPaint.setStrokeWidth(mSettings.getAxisWidth());
        mBaseAxisPaint.setColor(mUtils.getColorInt(mSettings.getAxisColorId()));

        mUtils.convertDpToPixel(mBaseAxisBuffer);

        c.drawLines(mBaseAxisBuffer, mBaseAxisPaint);
    }

    public void setSettings(CandlestickSettings settings) {
        this.mSettings = settings;
        mBaseAxis = new BaseAxis(this, settings);
    }

    public void setItems(ArrayList<CandleItem> mItems) {
        this.mItems = mItems;
    }
}
