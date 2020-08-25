package com.albertkhang.testcandlestickchart.dataset;

import android.graphics.Paint;

import com.albertkhang.testcandlestickchart.model.ICandleData;
import com.albertkhang.testcandlestickchart.model.IData;
import com.albertkhang.testcandlestickchart.model.ILabelData;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

import java.util.ArrayList;

/**
 * contain a data list and other settings for that data list
 *
 * @param <T>
 */
public abstract class BaseDataSet<T extends IData> {
    /**
     * decide label size, color. Default label size is 16. Default label color is BLACK.
     */
    protected Paint mLabelPaint;

    /**
     * decide base axis color, stroke width
     */
    protected Paint mBaseAxisPaint;

    /**
     * decide background grid color, stroke width
     */
    protected Paint mGridPaint;

    /**
     * decide max X axis
     */
    protected int mMaxXAxis = 6;

    /**
     * decide min X axis
     */
    protected int mMinXAxis = 6;

    /**
     * decide max Y axis
     */
    protected int mMaxYAxis = 8;

    /**
     * decide min Y axis
     */
    protected int mMinYAxis = 6;

    /**
     * contain data list
     */
    protected ArrayList<T> mData;

    /**
     * contain viewport of this data set
     */
    protected ViewPortHandler mViewportHandler;

    /**
     * contain max value of Y axis
     */
    protected float mMaxHeight = Float.MIN_VALUE;

    /**
     * contain min value of Y axis
     */
    protected float mMinHeight = Float.MAX_VALUE;

    /**
     * contain max value of X axis
     */
    protected float mMaxWidth = Float.MIN_VALUE;

    /**
     * contain min value of X axis
     */
    protected float mMinWidth = Float.MAX_VALUE;

    /**
     * contain X range between 2 label
     */
    protected float mXRange;

    /**
     * contain Y range between 2 label
     */
    protected float mYRange;

    /**
     * be used to calculate content offset in ViewPortHandler
     */
    protected int mOffsetLeft = 75;
    protected int mOffsetTop = 45;
    protected int mOffsetRight = 45;
    protected int mOffsetBottom = 45;

    /**
     * value show at the top of chart
     */
    protected float mMaxChartHeightValue;

    /**
     * value show at the bottom of chart
     */
    protected float mMinChartHeightValue;

    /**
     * contain label data
     */
    protected ArrayList<ILabelData> labelData;

    public BaseDataSet() {
        this.mLabelPaint = new Paint();
        this.mBaseAxisPaint = new Paint();
        this.mGridPaint = new Paint();
    }

    public ViewPortHandler getViewportHandler() {
        return mViewportHandler;
    }

    public ArrayList<ILabelData> getLabelData() {
        return labelData;
    }

    public void setLabelSize(float size) {
        this.mLabelPaint.setTextSize(size);
    }

    public void setLabelColor(int colorId) {
        this.mLabelPaint.setColor(colorId);
    }

    public void setBaseAxisColor(int colorId) {
        this.mBaseAxisPaint.setColor(colorId);
    }

    public void setBaseAxisStrokeWidth(float width) {
        this.mBaseAxisPaint.setStrokeWidth(width);
    }

    public void setGridColor(int colorId) {
        this.mGridPaint.setColor(colorId);
    }

    public void setGridStrokeWidth(float width) {
        this.mGridPaint.setStrokeWidth(width);
    }

    public void setMaxXAxis(int max) {
        this.mMaxXAxis = max;
    }

    public void setMaxYAxis(int max) {
        this.mMaxYAxis = max;
    }

    public void setMinXAxis(int min) {
        this.mMinXAxis = min;
    }

    public void setMinYAxis(int min) {
        this.mMinYAxis = min;
    }
}
