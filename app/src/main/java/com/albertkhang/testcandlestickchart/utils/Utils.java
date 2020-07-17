package com.albertkhang.testcandlestickchart.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {
    private Context mContext;
    private static DisplayMetrics mMetrics;

    public Utils(Context context) {
        this.mContext = context;
        mMetrics = context.getResources().getDisplayMetrics();
    }

    public float convertDpToPixel(float dp) {
        return dp * mMetrics.density;
    }
}
