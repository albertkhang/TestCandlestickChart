package com.albertkhang.testcandlestickchart.model;

/**
 * Contain candlestick data is used in CandleDataSet
 */
public class ICandleData extends IData {
    private float high;
    private float low;
    private float open;
    private float close;

    public ICandleData(float high, float low, float open, float close) {
        this.high = high;
        this.low = low;
        this.open = open;
        this.close = close;
    }

    public ICandleData(ICandleData data) {
        this.high = data.high;
        this.low = data.low;
        this.open = data.open;
        this.close = data.close;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public float getOpen() {
        return open;
    }

    public float getClose() {
        return close;
    }
}
