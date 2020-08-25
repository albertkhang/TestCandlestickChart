package com.albertkhang.testcandlestickchart.model;

/**
 * contain a axis. Using in Render classes
 */
public class Axis {
    /**
     * contain value of start X axis
     */
    private float startX;

    /**
     * contain value of start Y axis
     */
    private float startY;

    /**
     * contain value of end X axis
     */
    private float endX;

    /**
     * contain value of end Y axis
     */
    private float endY;

    public Axis(float startX, float startY, float endX, float endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }
}
