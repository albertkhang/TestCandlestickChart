package com.albertkhang.testcandlestickchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;

public class CandlestickChart extends View {
    private CandlestickSettings mSettings;
    private ArrayList<CandleItem> mItems;
    private BaseAxis mBaseAxis;
    private Utils mUtils;

    private float[] mBaseAxisBuffer = new float[8];
    private Paint mBaseAxisPaint;

    public CandlestickChart(Context context) {
        super(context);
        mUtils = new Utils(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBaseAxis(canvas);
    }

    private void drawBaseAxis(Canvas c) {
        float xPos = mBaseAxis.getBaseXAxis();
        float yPos = mBaseAxis.getBaseYAxis();

        mBaseAxisBuffer[0] = xPos;
        mBaseAxisBuffer[1] = 0;

        mBaseAxisBuffer[2] = xPos;
        mBaseAxisBuffer[3] = yPos;

        mBaseAxisBuffer[4] = xPos;
        mBaseAxisBuffer[5] = yPos;

        mBaseAxisBuffer[6] = getMeasuredWidth() / mUtils.getDensity();
        mBaseAxisBuffer[7] = yPos;

        mBaseAxisPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBaseAxisPaint.setStrokeWidth(mSettings.getAxisWidth());
        mBaseAxisPaint.setColor(mUtils.getColorInt(R.color.colorPrimaryDark));

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
