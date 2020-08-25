package com.albertkhang.testcandlestickchart.render;

import com.albertkhang.testcandlestickchart.dataset.BaseDataSet;
import com.albertkhang.testcandlestickchart.model.IData;
import com.albertkhang.testcandlestickchart.util.ViewPortHandler;

public abstract class DataRender extends Render {
    public DataRender(BaseDataSet mDataSet, ViewPortHandler viewPortHandler) {
        super(mDataSet, viewPortHandler);
    }
}
