package com.albertkhang.testcandlestickchart.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;

import com.albertkhang.testcandlestickchart.dataset.CandleDataSet;
import com.albertkhang.testcandlestickchart.render.CandlestickDataRender;
import com.albertkhang.testcandlestickchart.render.LabelRender;
import com.albertkhang.testcandlestickchart.render.XAxisRender;
import com.albertkhang.testcandlestickchart.render.YAxisRender;

import static com.albertkhang.testcandlestickchart.util.Log.flowLOG;
import static com.albertkhang.testcandlestickchart.util.Log.showFlowLog;

public class CandlestickChart extends Chart<CandleDataSet> {
    private LabelRender mLabelRender;
    private YAxisRender mYAxisRender;
    private XAxisRender mXAxisRender;
    private CandlestickDataRender mDataRender;

    private boolean isDragX = true;
    private boolean isDragY = true;

    private float mPosX, mPosY;
    private float mLastTouchX, mLastTouchY;

    public CandlestickChart(Context context, CandleDataSet dataSet) {
        super(context, dataSet);
        mLabelRender = new LabelRender(mDataSet, mViewportHandler);
        mYAxisRender = new YAxisRender(mDataSet, mViewportHandler);
        mXAxisRender = new XAxisRender(mDataSet, mViewportHandler);
        mDataRender = new CandlestickDataRender(mDataSet, mViewportHandler);

        if (showFlowLog)
            Log.d(flowLOG, "CandlestickChart");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x, y, dX, dY;

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();

                mLastTouchX = x;
                mLastTouchY = y;

                break;

            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();

                dX = x - mLastTouchX;
                dY = y - mLastTouchY;

                if (isDragX) {
                    mPosX += dX;
                    if (mPosX >= mViewportHandler.getContentRect().left) {
                        mPosX = mViewportHandler.getContentRect().left;
                    }

                    if (mPosX < -1875) {
                        mPosX = -1875;
                    }
                }

                if (isDragY)
                    mPosY += dY;

                invalidate();

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;

                break;
        }

        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDataSet.calculateYLabel();
        mDataSet.calculateXLabel();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mLabelRender.render(canvas, mPosX, mPosY);
        mYAxisRender.render(canvas, mPosX, mPosY);
        mXAxisRender.render(canvas, mPosX, mPosY);
        mDataRender.render(canvas, mPosX, mPosY);
    }
}
