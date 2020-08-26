package com.albertkhang.testcandlestickchart.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

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

    @Override
    public void render(Canvas c) {
        renderYLabel(c);
        renderXLabel(c);
    }

    private void renderXLabel(Canvas c) {
        ArrayList<ILabelData> xLabelData = mDataSet.getXLabelData();
        mLabelPaint.setTextAlign(Paint.Align.CENTER);

        Rect bounds = new Rect();
        mLabelPaint.getTextBounds("0", 0, 1, bounds);
        float textHeight = (float) bounds.height();

        String text;
        float x, y;
        for (ILabelData item : xLabelData) {
            text = String.valueOf(item.getValue());
            x = item.getPx();
            y = mViewPortHandler.getContentRect().bottom + 10 + textHeight;

            c.drawText(text, x, y, mLabelPaint);
        }
    }

    private void renderYLabel(Canvas c) {
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
            y = item.getPx() + textHeight;
//            Log.i("testValue", "text: " + text + ", x: " + x + ", y: " + y);

            c.drawText(text, x - 10, y, mLabelPaint);
        }
    }
}
