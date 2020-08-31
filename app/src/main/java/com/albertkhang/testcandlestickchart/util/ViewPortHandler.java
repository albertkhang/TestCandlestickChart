package com.albertkhang.testcandlestickchart.util;

import android.graphics.RectF;
import android.util.Log;

import static com.albertkhang.testcandlestickchart.util.Log.flowLOG;
import static com.albertkhang.testcandlestickchart.util.Log.infoLOG;
import static com.albertkhang.testcandlestickchart.util.Log.showFlowLog;
import static com.albertkhang.testcandlestickchart.util.Log.showInfoLog;

public class ViewPortHandler {
    private RectF mOffsetRect;
    private RectF mContentRect;

    private float mChartHeight;
    private float mChartWidth;

    public ViewPortHandler(RectF offsetRect) {
        this.mOffsetRect = offsetRect;

        if (showFlowLog)
            Log.d(flowLOG, "ViewPortHandler init");
    }

    public RectF getContentRect() {
        return mContentRect;
    }

    public RectF getOffsetRect() {
        return mOffsetRect;
    }

    /**
     * set chart dimens
     * using the dimens to calculate in BaseDataSet
     *
     * @param width  Max width of the chart view
     * @param height Max height of the chart view
     */
    public void setChartDimens(float width, float height) {
        mChartHeight = height;
        mChartWidth = width;

        float offsetLeft = mOffsetRect.left;
        float offsetTop = mOffsetRect.top;
        float offsetRight = mChartWidth - mOffsetRect.right;
        float offsetBottom = mChartHeight - mOffsetRect.bottom;

        if (showInfoLog)
            Log.i(infoLOG, "setChartDimens offsetLeft: " + offsetLeft
                    + ", offsetTop: " + offsetTop
                    + ", offsetRight: " + offsetRight
                    + ", offsetBottom: " + offsetBottom);

        if (showFlowLog)
            Log.d(flowLOG, "setChartDimens");

        mContentRect = new RectF(offsetLeft, offsetTop, offsetRight, offsetBottom);
    }
}
