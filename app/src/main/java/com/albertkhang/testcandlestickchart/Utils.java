package com.albertkhang.testcandlestickchart;

import android.content.Context;

import androidx.core.content.ContextCompat;

public class Utils {
    private Context mContext;
    private float mDensity = 0;

    public Utils(Context mContext) {
        this.mContext = mContext;
        mDensity = mContext.getResources().getDisplayMetrics().density;
    }

    public float getDensity() {
        return mDensity;
    }

    public void convertDpToPixel(float[] buffer) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = buffer[i] * mDensity;
        }
    }

    public int getColorInt(int colorResId) {
        return ContextCompat.getColor(mContext, colorResId);
    }
}
