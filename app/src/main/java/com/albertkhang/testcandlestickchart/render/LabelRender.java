package com.albertkhang.testcandlestickchart.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.albertkhang.testcandlestickchart.dataset.BaseDataSet;
import com.albertkhang.testcandlestickchart.dataset.CandleDataSet;
import com.albertkhang.testcandlestickchart.model.ILabelData;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

import java.util.ArrayList;

public class LabelRender extends Render<CandleDataSet> {
    public LabelRender(CandleDataSet mDataSet, ViewPortHandler viewPortHandler) {
        super(mDataSet, viewPortHandler);
    }

    @Override
    public void render(Canvas c) {
        ArrayList<ILabelData> labelData = mDataSet.getLabelData();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(14 * 3);
        paint.setTextAlign(Paint.Align.RIGHT);

        Rect bounds = new Rect();
        paint.getTextBounds("0", 0, 1, bounds);
        float textHeight = (float) (bounds.height() / 2);

        String text;
        float x, y;

        for (ILabelData item : labelData) {
            text = String.valueOf(item.getValue());
            x = mViewPortHandler.getContentRect().left;
            y = item.getPx() + textHeight;
//            Log.i("testValue", "text: " + text + ", x: " + x + ", y: " + y);

            c.drawText(text, x - 10, y, paint);
        }
    }
}
