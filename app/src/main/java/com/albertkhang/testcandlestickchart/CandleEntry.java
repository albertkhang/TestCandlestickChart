package com.albertkhang.testcandlestickchart;

public class CandleEntry {
    private int mPos = 0;

    /**
     * shadow-high value
     */
    private float mShadowHigh = 0f;

    /**
     * shadow-low value
     */
    private float mShadowLow = 0f;

    /**
     * close value
     */
    private float mClose = 0f;

    /**
     * open value
     */
    private float mOpen = 0f;

    public CandleEntry(int mPos, float mShadowHigh, float mShadowLow, float mClose, float mOpen) {
        this.mPos = mPos;
        this.mShadowHigh = mShadowHigh;
        this.mShadowLow = mShadowLow;
        this.mClose = mClose;
        this.mOpen = mOpen;
    }

    public int getmPos() {
        return mPos;
    }

    public float getmShadowHigh() {
        return mShadowHigh;
    }

    public float getmShadowLow() {
        return mShadowLow;
    }

    public float getmClose() {
        return mClose;
    }

    public float getmOpen() {
        return mOpen;
    }
}
