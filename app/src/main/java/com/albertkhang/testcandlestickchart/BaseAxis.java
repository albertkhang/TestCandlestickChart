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

    public int getMaxXGrid() {
        if (mSettings != null) {
            float measuredWidth = mView.getMeasuredWidth() / mUtils.getDensity();
            float maxWidth = measuredWidth - mSettings.getAxisMargin();
            int max = 0;

            while (maxWidth > 0) {
                max++;
                maxWidth -= mSettings.getGridMargin();
            }

            return max;
        }

        return 0;
    }

    public int getMaxYGrid() {
        if (mSettings != null) {
            float measuredHeight = mView.getMeasuredHeight() / mUtils.getDensity();
            float maxHeight = measuredHeight - mSettings.getAxisMargin();
            int max = 0;

            while (maxHeight > 0) {
                max++;
                maxHeight -= mSettings.getGridMargin();
            }

            return max;
        }
        return 0;
    }
}
