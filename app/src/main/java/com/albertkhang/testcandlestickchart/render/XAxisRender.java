package com.albertkhang.testcandlestickchart.render;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.albertkhang.testcandlestickchart.dataset.BaseDataSet;
import com.albertkhang.testcandlestickchart.model.ILabelData;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

import java.util.ArrayList;

public class XAxisRender extends AxisRender {
    public XAxisRender(BaseDataSet mDataSet, ViewPortHandler viewPortHandler) {
        super(mDataSet, viewPortHandler);
    }

    @Override
    public void render(Canvas c, float posX, float posY) {
        c.save();
        c.clipRect(mViewPortHandler.getContentRect());

        ArrayList<ILabelData> xLabelData = mDataSet.getXLabelData();
        Paint paint = new Paint();
        paint.setStrokeWidth(mDataSet.getGridStrokeWidth());
        paint.setStyle(Paint.Style.STROKE);

        float top = mViewPortHandler.getContentRect().top;
        float bottom = mViewPortHandler.getContentRect().bottom;

        float x;
        for (int i = 0; i < xLabelData.size(); i++) {
            x = xLabelData.get(i).getPx() + posX;
            c.drawLine(x, top, x, bottom, paint);
        }

        c.restore();
    }
}
