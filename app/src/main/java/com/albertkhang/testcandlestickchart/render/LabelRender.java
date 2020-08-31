package com.albertkhang.testcandlestickchart.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.albertkhang.testcandlestickchart.chart.CandlestickChart;
import com.albertkhang.testcandlestickchart.dataset.CandleDataSet;
import com.albertkhang.testcandlestickchart.model.ILabelData;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

import java.util.ArrayList;

public class LabelRender extends Render<CandleDataSet> {
    private Paint mLabelPaint;

    public LabelRender(CandleDataSet mDataSet, ViewPortHandler viewPortHandler) {
        super(mDataSet, viewPortHandler);
        mLabelPaint = new Paint();
        mLabelPaint.setColor(Color.BLACK);
        mLabelPaint.setTextSize(14 * 3);
    }

    private float mPosX, mPosY;

    @Override
    public void render(Canvas c, float posX, float posY) {
        mPosX = posX;
        mPosY = posY;

        renderYLabel(c);
        renderXLabel(c);
    }

    private void renderXLabel(Canvas c) {
        c.save();
        RectF contentRect = mViewPortHandler.getContentRect();
        RectF offsetRect = mViewPortHandler.getOffsetRect();
        c.clipRect(contentRect.left, contentRect.bottom, contentRect.right, contentRect.bottom + offsetRect.bottom);

        ArrayList<ILabelData> xLabelData = mDataSet.getXLabelData();
        mLabelPaint.setTextAlign(Paint.Align.CENTER);

        Rect bounds = new Rect();
        mLabelPaint.getTextBounds("0", 0, 1, bounds);
        float textHeight = (float) bounds.height();

        String text;
        float x, y;
        for (ILabelData item : xLabelData) {
            text = String.valueOf(item.getValue());
            x = item.getPx() + mPosX;
            y = mViewPortHandler.getContentRect().bottom + 10 + textHeight;

            c.drawText(text, x, y, mLabelPaint);
        }

        c.restore();
    }

    private void renderYLabel(Canvas c) {
        c.save();
        RectF contentRect = mViewPortHandler.getContentRect();
        c.clipRect(0, contentRect.top, contentRect.left, contentRect.bottom);

        ArrayList<ILabelData> yLabelData = mDataSet.getYLabelData();
        mLabelPaint.setTextAlign(Paint.Align.RIGHT);

        Rect bounds = new Rect();
        mLabelPaint.getTextBounds("0", 0, 1, bounds);
        float textHeight = (float) (bounds.height() / 2);

        String text;
        float x, y;

        for (ILabelData item : yLabelData) {
            text = String.valueOf(item.getValue());
            x = mViewPortHandler.getContentRect().left;
            y = item.getPx() + textHeight + mPosY;
//            Log.i("testValue", "text: " + text + ", x: " + x + ", y: " + y);

            c.drawText(text, x - 10, y, mLabelPaint);
        }

        c.restore();
    }
}
