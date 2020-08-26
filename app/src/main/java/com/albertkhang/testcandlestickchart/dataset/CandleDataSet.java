package com.albertkhang.testcandlestickchart.dataset;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

import com.albertkhang.testcandlestickchart.model.ICandleData;
import com.albertkhang.testcandlestickchart.model.ILabelData;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

import java.util.ArrayList;

import static com.albertkhang.testcandlestickchart.util.Log.flowLOG;
import static com.albertkhang.testcandlestickchart.util.Log.infoLOG;
import static com.albertkhang.testcandlestickchart.util.Log.showFlowLog;
import static com.albertkhang.testcandlestickchart.util.Log.showInfoLog;

/**
 * contain special settings for CandlestickDataSet
 */
public class CandleDataSet extends BaseDataSet<ICandleData> {
    /**
     * decide shadow color, width. Default shadow width is 1
     */
    private Paint mShadowPaint;

    /**
     * decide stroke color, width. Default shadow width is 1
     */
    private Paint mStrokePaint;

    /**
     * decide body color
     */
    private Paint mBodyPaint;

    /**
     * decide shadow increasing color
     */
    private int mShadowIncreasingColorId;

    /**
     * decide shadow decreasing color
     */
    private int mShadowDecreasingColorId;

    /**
     * decide body stroke increasing color
     */
    private int mStrokeIncreasingColorId;

    /**
     * decide body stroke decreasing color
     */
    private int mStrokeDecreasingColorId;

    /**
     * decide body increasing color
     */
    private int mBodyIncreasingColorId;

    /**
     * decide body decreasing color
     */
    private int mBodyDecreasingColorId;

    /**
     * decide shadow has same color with stroke
     */
    private boolean isShadowSameStrokeColor = false;

    public CandleDataSet(ArrayList<ICandleData> data) {
        super();
        this.mData = data;
        init();
    }

    @Override
    public void updateData(ArrayList<ICandleData> data) {
        this.mData = data;
        calculateMinMax();
        calculateOffset();

        mViewportHandler = new ViewPortHandler(new RectF(mOffsetLeft, mOffsetTop, mOffsetRight, mOffsetBottom));

        calculateChartRange();
        calculateLabelRange();
    }

    private void init() {
        this.mShadowPaint = new Paint();
        this.mStrokePaint = new Paint();
        this.mBodyPaint = new Paint();

        this.mShadowPaint.setStyle(Paint.Style.STROKE);
        this.mStrokePaint.setStyle(Paint.Style.STROKE);
        this.mBodyPaint.setStyle(Paint.Style.FILL);

        this.mShadowPaint.setStrokeWidth(1);
        this.mStrokePaint.setStrokeWidth(1);

        updateData(mData);

        if (showInfoLog)
            Log.i(infoLOG, "init mOffsetLeft: " + mOffsetLeft + ", mOffsetTop: " + mOffsetTop + ", mOffsetRight: " + mOffsetRight + ", mOffsetBottom: " + mOffsetBottom);

        if (showFlowLog)
            Log.d(flowLOG, "CandleDataSet init");
    }

    public void calculateXLabel() {
        mXLabelData.clear();

        float left = mViewportHandler.getContentRect().left;
        float bottom = mViewportHandler.getContentRect().bottom;
        float right = mViewportHandler.getContentRect().right;

        //tính kích thước của một shell
        float chartRange = Math.abs(left - right);
        float shellRange = (float) Math.floor(chartRange / mMaxXLabel);
        shellRange = (float) Math.floor((chartRange - shellRange / 2) / mMaxXLabel);
        float startX = left;

        for (int i = 0; i < mMaxXLabel + 1; i++) {
            mXLabelData.add(new ILabelData(i, startX + shellRange * i));
        }
    }

    public void calculateYLabel() {
        mYLabelData.clear();

        float top = mViewportHandler.getContentRect().top;
        float bottom = mViewportHandler.getContentRect().bottom;
        float chartRangeValue = Math.abs(mMaxChartHeightValue - mMinChartHeightValue);
        float dataRangeValue = Math.abs(mMaxHeight - mMinHeight);
//        Log.i("testValue", "mMaxChartHeightValue: " + mMaxChartHeightValue + ", mMinChartHeightValue: " + mMinChartHeightValue);
//        Log.i("testValue", "chartRangeValue: " + chartRangeValue + ", dataRangeValue: " + dataRangeValue);

        //tìm giá trị bắt đầu của label
        int startDataValue = (int) Math.floor(mMinHeight / 10f) * 10;
//        Log.i("testValue", "startDataValue: " + startDataValue);

        //tìm giá trị bắt đầu của label trong chart
        float chartRange = Math.abs(top - bottom);
        float dataRange = chartRange * 0.8f;
        float padding = Math.abs(chartRange - dataRange) / 2;
        float startChart = bottom - padding;
//        Log.i("testValue", "startChart: " + startChart + ", dataRange: " + dataRange);

        float oneDataUnitInChart = dataRange / dataRangeValue;
//        Log.i("testValue", "oneDataUnitInChart: " + oneDataUnitInChart);

        startChart = startChart + Math.abs(mMinChartHeightValue - startDataValue) * oneDataUnitInChart;
//        Log.i("testValue", "startChart: " + startChart + ", mYRange: " + mYRange);

        int value;
        float y;
        for (int i = 0; i < mMaxYLabel; i++) {
            value = (int) (startDataValue + mYRange * i);
            y = startChart - mYRange * oneDataUnitInChart * i;

            if (y <= top) {
                break;
            } else {
                if (y >= top + 30 && y <= bottom - 30) {
                    mYLabelData.add(new ILabelData(value, y));
                }
            }
        }
    }

    /**
     * calculate range between two label
     */
    private void calculateLabelRange() {
        //Y Axis
        float rawRange = Math.abs(mMaxChartHeightValue - mMinChartHeightValue) / mMaxYLabel;
        float range;
        if (rawRange >= mMaxYLabel) {
            range = (float) Math.round(rawRange / 10f);
            range *= 10;
        } else {
            range = (float) Math.floor(rawRange * 10);
            range /= 10;
        }
//        Log.i("testValue", "rawRange: " + rawRange + ", range: " + range);

        mYRange = range;

        float dataRangeValue = Math.abs(mMaxHeight - mMinHeight);

        //X Axis
        rawRange = Math.abs(mMaxWidth - mMinWidth) / mMaxXLabel;
        range = (float) Math.round(rawRange / 10f);
        if (range < 1) {
            range = 1;
        }

        mXRange = range;
    }

    /**
     * calculate chart range from max, min height in DataSet
     * set new value in mMaxHeight, mMinHeight
     */
    private void calculateChartRange() {
        float oldRange = Math.abs(mMaxHeight - mMinHeight);
        float newRange = oldRange / 0.8f;
        float delta = (newRange - oldRange) / 2;

        mMaxChartHeightValue = mMaxHeight + delta;
        mMinChartHeightValue = mMinHeight - delta;

        if (showInfoLog)
            Log.d(infoLOG, "mMaxChartHeightValue: " + mMaxChartHeightValue + ", mMinChartHeightValue: " + mMinChartHeightValue);
    }

    /**
     * calculate max width label in Y axis
     * calculate max height label in X axis
     */
    private void calculateOffset() {
        calculateMaxWidthLabel();
        calculateMaxHeightLabel();
    }

    /**
     * calculate max height of X axis value in String
     * be used to calculate ViewPortHandler
     */
    private void calculateMaxHeightLabel() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        paint.setTextSize(mLabelPaint.getTextSize());

        Rect bounds = new Rect();
        int size = mData.size();
        int max = 0;
        String tmp;

        for (int i = 0; i < size; i++) {
            tmp = String.valueOf(i + 1);
            paint.getTextBounds(tmp, 0, tmp.length(), bounds);

            if (max < bounds.height())
                max = bounds.height();
        }

        if (max > mOffsetBottom) {
            mOffsetBottom = max + 10;
        }

        if (showInfoLog)
            Log.i("text", "maxHeight: " + max);
    }

    /**
     * calculate max width of Y axis value in String
     * be used to calculate ViewPortHandler
     */
    private void calculateMaxWidthLabel() {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        paint.setTextSize(mLabelPaint.getTextSize());

        Rect bounds = new Rect();
        int size = mData.size();
        int max = 0;
        String tmp;

        for (int i = 0; i < size; i++) {
            tmp = String.valueOf(mMaxHeight);
            paint.getTextBounds(tmp, 0, tmp.length(), bounds);

            if (max < bounds.width())
                max = bounds.width();

            tmp = String.valueOf(mMinHeight);
            paint.getTextBounds(tmp, 0, tmp.length(), bounds);

            if (max < bounds.width())
                max = bounds.width();
        }

        if (max > mOffsetLeft) {
            mOffsetLeft = max + 10;
        }

        if (showInfoLog)
            Log.i("text", "maxWidth: " + max);
    }

    /**
     * calculate min, max of data set
     * be used to draw chart
     */
    private void calculateMinMax() {
        calculateHeight();
        calculateWidth();

        float heightRange = mMaxHeight - mMinHeight;

        if (showInfoLog)
            Log.i(infoLOG, "maxHeight: " + mMaxHeight + ", minHeight: " + mMinHeight + ", heightRange: " + heightRange + ", maxWidth: " + mMaxWidth + ", minWidth: " + mMinWidth);
    }

    /**
     * calculate min, max of data set
     * using to draw width in chart
     */
    private void calculateWidth() {
        int size = mData.size();

        mMinWidth = -0.5f;
        mMaxWidth = size - 0.5f;
    }

    /**
     * calculate min, max of data set
     * using to draw height in chart
     */
    private void calculateHeight() {
        int size = mData.size();
        ICandleData data;

        for (int i = 0; i < size; i++) {
            data = mData.get(i);

            if (mMaxHeight < data.getHigh()) {
                mMaxHeight = data.getHigh();
            }

            if (mMinWidth > data.getLow()) {
                mMinHeight = data.getLow();
            }
        }
    }

    public void setShadowWidth(int width) {
        mShadowPaint.setStrokeWidth(width);
    }

    public void setStrokeWidth(int width) {
        mStrokePaint.setStrokeWidth(width);
    }

    public void setShadowIncreasingColor(int colorId) {
        this.mShadowIncreasingColorId = colorId;
    }

    public void setShadowDecreasingColor(int colorId) {
        this.mShadowDecreasingColorId = colorId;
    }

    public void setStrokeIncreasingColor(int colorId) {
        this.mStrokeIncreasingColorId = colorId;
    }

    public void setStrokeDecreasingColor(int colorId) {
        this.mStrokeDecreasingColorId = colorId;
    }

    public void setBodyIncreasingColor(int colorId) {
        this.mBodyIncreasingColorId = colorId;
    }

    public void setBodyDecreasingColor(int colorId) {
        this.mBodyDecreasingColorId = colorId;
    }

    public void setShadowSameStrokeColor(boolean isSame) {
        this.isShadowSameStrokeColor = isSame;
    }
}
