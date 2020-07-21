package com.albertkhang.testcandlestickchart;

import android.content.Context;

public class CandlestickSettings {
    private int mAxisMargin = 20;
    private int mGridMargin = 50;

    private int mAxisWidth = 4;
    private int mGridWidth = 2;

    private int mAxisColorId = R.color.colorPrimaryDark;
    private int mGridColorId = R.color.colorPrimary;

    private Context mContext;

    public CandlestickSettings(Context context) {
        this.mContext = context;
    }

    public int getAxisMargin() {
        return mAxisMargin;
    }

    public void setAxisMargin(int mAxisMargin) {
        this.mAxisMargin = mAxisMargin;
    }

    public int getGridMargin() {
        return mGridMargin;
    }

    public void setGridMargin(int mGridMargin) {
        this.mGridMargin = mGridMargin;
    }

    public int getAxisWidth() {
        return mAxisWidth;
    }

    public void setAxisWidth(int mAxisWidth) {
        this.mAxisWidth = mAxisWidth;
    }

    public int getGridWidth() {
        return mGridWidth;
    }

    public void setGridWidth(int mGridWidth) {
        this.mGridWidth = mGridWidth;
    }

    public int getAxisColorId() {
        return mAxisColorId;
    }

    public void setAxisColorId(int mAxisColorId) {
        this.mAxisColorId = mAxisColorId;
    }

    public int getGridColorId() {
        return mGridColorId;
    }

    public void setGridColorId(int mGridColorId) {
        this.mGridColorId = mGridColorId;
    }
}
