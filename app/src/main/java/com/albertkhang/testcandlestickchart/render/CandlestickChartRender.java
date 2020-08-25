package com.albertkhang.testcandlestickchart.render;

import android.graphics.Canvas;

import com.albertkhang.testcandlestickchart.dataset.BaseDataSet;
import com.albertkhang.testcandlestickchart.dataset.CandleDataSet;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

public class CandlestickChartRender extends DataRender {
    public CandlestickChartRender(BaseDataSet mDataSet, ViewPortHandler viewPortHandler) {
        super(mDataSet, viewPortHandler);
    }

    @Override
    public void render(Canvas c) {

    }
}