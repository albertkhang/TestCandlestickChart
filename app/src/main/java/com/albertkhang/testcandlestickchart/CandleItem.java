package com.albertkhang.testcandlestickchart;

public class CandleItem {
    private float mHigh;
    private float mLow;
    private float mOpen;
    private float mClose;

    public CandleItem(float mHigh, float mLow, float mOpen, float mClose) {
        this.mHigh = mHigh;
        this.mLow = mLow;
        this.mOpen = mOpen;
        this.mClose = mClose;
    }

    public float getHigh() {
        return mHigh;
    }

    public float getLow() {
        return mLow;
    }

    public float getOpen() {
        return mOpen;
    }

    public float getClose() {
        return mClose;
    }
}
