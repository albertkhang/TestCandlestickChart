package com.albertkhang.testcandlestickchart.render;

import android.graphics.Canvas;

import com.albertkhang.testcandlestickchart.dataset.BaseDataSet;
import com.albertkhang.testcandlestickchart.model.IData;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

/**
 * render BaseAxis, grid background, label and data
 */
public abstract class Render<T extends BaseDataSet<? extends IData>> {
    protected LabelRender mLabelRender;
    protected XAxisRender mXAxisRender;
    protected YAxisRender mYAxisRender;
    protected T mDataSet;
    protected ViewPortHandler mViewPortHandler;

    public Render(T mDataSet, ViewPortHandler viewPortHandler) {
        this.mDataSet = mDataSet;
        this.mViewPortHandler = viewPortHandler;
    }

    public abstract void render(Canvas c, float posX, float posY);
}
