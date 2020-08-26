package com.albertkhang.testcandlestickchart.render;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.albertkhang.testcandlestickchart.dataset.BaseDataSet;
import com.albertkhang.testcandlestickchart.model.ICandleData;
import com.albertkhang.testcandlestickchart.model.ILabelData;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

import java.util.ArrayList;

public class YAxisRender extends AxisRender {
    public YAxisRender(BaseDataSet<ICandleData> mDataSet, ViewPortHandler viewPortHandler) {
        super(mDataSet, viewPortHandler);
    }

    @Override
    public void render(Canvas c) {
        ArrayList<ILabelData> yLabelData = mDataSet.getYLabelData();
        Paint paint = new Paint();
        paint.setStrokeWidth(mDataSet.getGridStrokeWidth());
        paint.setStyle(Paint.Style.STROKE);

        float y;
        for (ILabelData item : yLabelData) {
            y = item.getPx();
            c.drawLine(mViewPortHandler.getContentRect().left, y, mViewPortHandler.getContentRect().right, y, paint);
        }
    }
}
