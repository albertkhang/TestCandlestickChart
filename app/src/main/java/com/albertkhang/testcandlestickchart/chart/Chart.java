package com.albertkhang.testcandlestickchart.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

import com.albertkhang.testcandlestickchart.dataset.BaseDataSet;
import com.albertkhang.testcandlestickchart.model.IData;
import com.albertkhang.testcandlestickchart.render.AxisRender;
import com.albertkhang.testcandlestickchart.render.DataRender;
import com.albertkhang.testcandlestickchart.render.LabelRender;
import com.albertkhang.testcandlestickchart.render.Render;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

import static com.albertkhang.testcandlestickchart.util.Log.flowLOG;
import static com.albertkhang.testcandlestickchart.util.Log.infoLOG;
import static com.albertkhang.testcandlestickchart.util.Log.showFlowLog;
import static com.albertkhang.testcandlestickchart.util.Log.showInfoLog;

public abstract class Chart<T extends BaseDataSet<? extends IData>> extends View {
    /**
     * is DataSet of this chart
     */
    protected T mDataSet;

    /**
     * contain viewport of this data set
     */
    protected ViewPortHandler mViewportHandler;

    protected float[] mBaseAxisBuffer = new float[8];
    private Paint baseAxisPaint;

    public Chart(Context context, T dataSet) {
        super(context);
        this.mDataSet = dataSet;
        this.mViewportHandler = mDataSet.getViewportHandler();

        if (showFlowLog)
            Log.d(flowLOG, "Chart");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF contentRect = mViewportHandler.getContentRect();

        //X value
        mBaseAxisBuffer[0] = contentRect.left;
        mBaseAxisBuffer[2] = mBaseAxisBuffer[0];
        mBaseAxisBuffer[4] = mBaseAxisBuffer[0];
        mBaseAxisBuffer[6] = contentRect.right;

        //Y value
        mBaseAxisBuffer[1] = contentRect.top;
        mBaseAxisBuffer[3] = contentRect.bottom;
        mBaseAxisBuffer[5] = mBaseAxisBuffer[3];
        mBaseAxisBuffer[7] = mBaseAxisBuffer[5];

        baseAxisPaint = new Paint();
        baseAxisPaint.setStrokeWidth(4);
        baseAxisPaint.setStyle(Paint.Style.STROKE);
        baseAxisPaint.setColor(Color.BLACK);

        canvas.drawLines(mBaseAxisBuffer, baseAxisPaint);

        if (showFlowLog)
            Log.d(flowLOG, "onDraw");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewportHandler.setChartDimens(w, h);

        if (showInfoLog)
            Log.i(infoLOG, "w: " + w + ", h: " + h);

        if (showFlowLog)
            Log.d(flowLOG, "onSizeChanged");
    }
}
