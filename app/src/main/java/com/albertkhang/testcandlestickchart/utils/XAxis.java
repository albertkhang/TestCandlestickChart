package com.albertkhang.testcandlestickchart.utils;

import android.content.Context;

public class XAxis extends BaseAxis {
    public XAxis(Context mContext) {
        super(mContext);
        mBaseCoordinate = mUtils.convertDpToPixel(mMargin);
    }
}
