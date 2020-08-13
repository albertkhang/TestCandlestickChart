package com.albertkhang.testcandlestickchart.charts;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.albertkhang.testcandlestickchart.data.CandleData;
import com.albertkhang.testcandlestickchart.interfaces.dataprovider.CandleDataProvider;
import com.albertkhang.testcandlestickchart.renderer.CandleStickChartRenderer;

import static com.albertkhang.testcandlestickchart.MainActivity.FLOW_TAG;


public class CandleStickChart extends BarLineChartBase<CandleData> implements CandleDataProvider {

    public CandleStickChart(Context context) {
        super(context);
    }

    public CandleStickChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CandleStickChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        super.init();
        Log.d(FLOW_TAG, "CandleStickChart init");

        mRenderer = new CandleStickChartRenderer(this, mAnimator, mViewPortHandler);

        getXAxis().setSpaceMin(0.5f);
        getXAxis().setSpaceMax(0.5f);
    }

    @Override
    public CandleData getCandleData() {
        return mData;
    }
}