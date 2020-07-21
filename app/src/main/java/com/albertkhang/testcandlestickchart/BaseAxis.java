package com.albertkhang.testcandlestickchart;

import android.content.Context;
import android.view.View;

public class BaseAxis {
    private View mView;
    private CandlestickSettings mSettings;
    private Utils mUtils;

    private float mBaseXAxis = 0;
    private float mBaseYAxis = 0;

    public BaseAxis(View view, CandlestickSettings settings) {
        this.mView = view;
        this.mSettings = settings;
        mUtils = new Utils(view.getContext());
    }

    public float getBaseXAxis() {
        if (mSettings != null) {
            return mSettings.getAxisMargin();
        }
        return mBaseXAxis;
    }

    public float getBaseYAxis() {
        if (mSettings != null) {
            return mView.getMeasuredHeight() / mUtils.getDensity() - mSettings.getAxisMargin();
        }
        return mBaseYAxis;
    }
}
