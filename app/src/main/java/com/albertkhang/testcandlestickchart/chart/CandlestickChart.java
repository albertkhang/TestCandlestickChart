package com.albertkhang.testcandlestickchart.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.albertkhang.testcandlestickchart.dataset.CandleDataSet;
import com.albertkhang.testcandlestickchart.render.LabelRender;
import com.albertkhang.testcandlestickchart.render.XAxisRender;
import com.albertkhang.testcandlestickchart.render.YAxisRender;

import static com.albertkhang.testcandlestickchart.util.Log.flowLOG;
import static com.albertkhang.testcandlestickchart.util.Log.showFlowLog;

public class CandlestickChart extends Chart<CandleDataSet> {
    private LabelRender mLabelRender;
    private YAxisRender mYAxisRender;
    private XAxisRender mXAxisRender;

    public CandlestickChart(Context context, CandleDataSet dataSet) {
        super(context, dataSet);
        mLabelRender = new LabelRender(mDataSet, mViewportHandler);
        mYAxisRender = new YAxisRender(mDataSet, mViewportHandler);
        mXAxisRender = new XAxisRender(mDataSet, mViewportHandler);

        if (showFlowLog)
            Log.d(flowLOG, "CandlestickChart");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDataSet.calculateYLabel();
        mDataSet.calculateXLabel();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mLabelRender.render(canvas);
        mYAxisRender.render(canvas);
        mXAxisRender.render(canvas);
    }
}
