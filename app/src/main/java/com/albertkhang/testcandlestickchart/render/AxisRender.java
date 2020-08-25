package com.albertkhang.testcandlestickchart.render;

import com.albertkhang.testcandlestickchart.dataset.BaseDataSet;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

public abstract class AxisRender extends Render {
    public AxisRender(BaseDataSet mDataSet, ViewPortHandler viewPortHandler) {
        super(mDataSet, viewPortHandler);
    }
}
