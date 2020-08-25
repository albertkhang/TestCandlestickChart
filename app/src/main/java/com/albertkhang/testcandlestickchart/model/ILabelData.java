package com.albertkhang.testcandlestickchart.model;

public class ILabelData {
    private int value;
    private float px;

    public ILabelData(int value, float px) {
        this.value = value;
        this.px = px;
    }

    public int getValue() {
        return value;
    }

    public float getPx() {
        return px;
    }

    @Override
    public String toString() {
        return "ILabelData{" +
                "value=" + value +
                ", px=" + px +
                '}';
    }
}
