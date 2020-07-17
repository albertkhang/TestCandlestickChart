package com.albertkhang.testcandlestickchart.utils;

import android.content.Context;

public class YAxis extends BaseAxis {
    public YAxis(Context mContext, int measuredHeight) {
        super(mContext);
        mBaseCoordinate = mUtils.convertDpToPixel(measuredHeight / mMetrics.density - mMargin);
    }
}
