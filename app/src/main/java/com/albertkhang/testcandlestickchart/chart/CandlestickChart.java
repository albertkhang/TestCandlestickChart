package com.albertkhang.testcandlestickchart.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.albertkhang.testcandlestickchart.dataset.CandleDataSet;
import com.albertkhang.testcandlestickchart.render.CandlestickChartRender;
import com.albertkhang.testcandlestickchart.render.LabelRender;

import static com.albertkhang.testcandlestickchart.util.Log.flowLOG;
import static com.albertkhang.testcandlestickchart.util.Log.showFlowLog;

public class CandlestickChart extends Chart<CandleDataSet> {
    private CandlestickChartRender chartRender;
    private LabelRender labelRender;

    public CandlestickChart(Context context, CandleDataSet dataSet) {
        super(context, dataSet);
        chartRender = new CandlestickChartRender(mDataSet, mViewportHandler);
        labelRender = new LabelRender(mDataSet, mViewportHandler);

        if (showFlowLog)
            Log.d(flowLOG, "CandlestickChart");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDataSet.calculateYLabel();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        chartRender.render(canvas);
        labelRender.render(canvas);
    }
}
