package com.albertkhang.testcandlestickchart.render;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.albertkhang.testcandlestickchart.dataset.BaseDataSet;
import com.albertkhang.testcandlestickchart.dataset.CandleDataSet;
import com.albertkhang.testcandlestickchart.model.ICandleData;
import com.albertkhang.testcandlestickchart.model.ILabelData;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

import java.util.ArrayList;

public class CandlestickDataRender extends DataRender {
    public CandlestickDataRender(BaseDataSet mDataSet, ViewPortHandler viewPortHandler) {
        super(mDataSet, viewPortHandler);
    }

    @Override
    public void render(Canvas c) {
        ArrayList<ILabelData> xLabelData = mDataSet.getXLabelData();
        ArrayList<ICandleData> data = mDataSet.getData();

        if (data.size() == 0)
            return;

        float top = mViewPortHandler.getContentRect().top;
        float bottom = mViewPortHandler.getContentRect().bottom;
        float dataRangeValue = Math.abs(mDataSet.getMaxHeight() - mDataSet.getMinHeight());

        //tìm giá trị bắt đầu của label trong chart
        float chartRange = Math.abs(top - bottom);
        float dataRange = chartRange * 0.8f;

        float oneDataUnitInChart = dataRange / dataRangeValue;

//        Log.i("testValue", "size: " + xLabelData.size());

        c.save();
        c.clipRect(mViewPortHandler.getContentRect());

        Paint paint = new Paint();
        paint.setStrokeWidth(4);
        float high, low, open, close;
        for (int i = 1; i < xLabelData.size(); i++) {
            high = data.get(i - 1).getHigh();
            low = data.get(i - 1).getLow();
            open = data.get(i - 1).getOpen();
            close = data.get(i - 1).getClose();

            if (open > close) {
                paint.setColor(Color.GREEN);
            } else {
                paint.setColor(Color.RED);
            }
            Log.i("testValue", "open: " + open + ", close: " + close);

            paint.setStyle(Paint.Style.FILL);
            float x = xLabelData.get(i).getPx();
            int dataWidth = 60;
            open = Math.abs(dataRangeValue - open) * oneDataUnitInChart;
            close = Math.abs(dataRangeValue - close) * oneDataUnitInChart;
            c.drawRect(x - dataWidth / 2, open, x + dataWidth / 2, close, paint);

            //draw shadow
            paint.setStyle(Paint.Style.STROKE);

            high = Math.abs(dataRangeValue - high) * oneDataUnitInChart;
            low = Math.abs(dataRangeValue - low) * oneDataUnitInChart;

            c.drawLine(xLabelData.get(i).getPx(), high, xLabelData.get(i).getPx(), low, paint);
        }

        c.restore();
    }
}
