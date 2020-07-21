package com.albertkhang.testcandlestickchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
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

        drawGridBackground(canvas);
        drawBaseAxis(canvas);
        drawBaseAxisText(canvas);
    }

    private float[] mGridLabelBuffer = new float[2];
    private TextPaint mGridLabelPaint;

    private void drawBaseAxisText(Canvas c) {
        mGridLabelPaint = new TextPaint();
        mGridLabelPaint.setColor(mUtils.getColorInt(mSettings.getAxisLabelColorId()));
        mGridLabelPaint.setTextSize(mSettings.getAxisLabelSize() * mUtils.getDensity());
        mGridLabelPaint.setTextAlign(Paint.Align.CENTER);

        int i;
        float a = 0;
        float b = 0;
        int max = mBaseAxis.getMaxXGrid();

        //draw XAxis label
        for (i = 0; i < max; i++) {
            mGridLabelBuffer[0] = mXPos + mSettings.getGridMargin() * i;
            mGridLabelBuffer[1] = mYPos + mSettings.getXAxisLabelMargin();

            if (i == 0) {
                a += mGridLabelBuffer[0];
                b += mGridLabelBuffer[1];
                continue;
            }

            mUtils.convertDpToPixel(mGridLabelBuffer);
            c.drawText(String.valueOf(i), mGridLabelBuffer[0], mGridLabelBuffer[1], mGridLabelPaint);
        }

        //draw yAxis label
        max = mBaseAxis.getMaxYGrid();
        for (i = 0; i < max; i++) {
            mGridLabelBuffer[0] = mXPos - mSettings.getYAxisLabelMargin();
            mGridLabelBuffer[1] = mYPos - mSettings.getGridMargin() * i + 3;

            if (i == 0) {
                a += mGridLabelBuffer[0];
                b += mGridLabelBuffer[1];
                continue;
            }

            mUtils.convertDpToPixel(mGridLabelBuffer);
            c.drawText(String.valueOf(i), mGridLabelBuffer[0], mGridLabelBuffer[1], mGridLabelPaint);
        }

        //draw 0,0 label
        mGridLabelBuffer[0] = a / 2f;
        mGridLabelBuffer[1] = b / 2f;
        mGridLabelPaint.setTextAlign(Paint.Align.RIGHT);

        mUtils.convertDpToPixel(mGridLabelBuffer);
        c.drawText("0", mGridLabelBuffer[0], mGridLabelBuffer[1], mGridLabelPaint);
    }

    private void drawGridBackground(Canvas c) {
        mGridBackgroundPaint = new Paint();
        mGridBackgroundPaint.setStrokeWidth(mSettings.getGridWidth());
        mGridBackgroundPaint.setColor(mUtils.getColorInt(mSettings.getGridColorId()));
        mGridBackgroundPaint.setStyle(Paint.Style.STROKE);

        //draw XGrid
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

        //draw YGrid
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
        mBaseAxisPaint = new Paint();
        mBaseAxisPaint.setStrokeWidth(mSettings.getAxisWidth());
        mBaseAxisPaint.setColor(mUtils.getColorInt(mSettings.getAxisColorId()));
        mBaseAxisPaint.setStyle(Paint.Style.STROKE);

        mBaseAxisBuffer[0] = mXPos;
        mBaseAxisBuffer[1] = 0;

        mBaseAxisBuffer[2] = mXPos;
        mBaseAxisBuffer[3] = mYPos;

        mBaseAxisBuffer[4] = mXPos;
        mBaseAxisBuffer[5] = mYPos;

        mBaseAxisBuffer[6] = getMeasuredWidth() / mUtils.getDensity();
        mBaseAxisBuffer[7] = mYPos;

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
