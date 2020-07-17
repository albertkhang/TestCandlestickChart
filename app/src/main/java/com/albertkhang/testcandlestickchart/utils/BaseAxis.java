package com.albertkhang.testcandlestickchart.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public abstract class BaseAxis {
    private static final String TAG = "BaseAxis";
    protected Context mContext;

    /**
     * Vị trí tọa độ 0,0 của trục X,Y
     */
    protected float mBaseCoordinate = 0f;

    /**
     * Margin của trục X,Y
     */
    protected int mMargin = 0;

    protected DisplayMetrics mMetrics;

    protected Utils mUtils;

    public BaseAxis(Context mContext) {
        this.mContext = mContext;
        mMetrics = mContext.getResources().getDisplayMetrics();
        mUtils = new Utils(mContext);
    }

    /**
     * @return Trả về tọa độ 0,0 của trục X,Y
     */
    public float getBaseCoordinate() {
        Log.d(TAG, "BaseCoordinate: " + mBaseCoordinate);
        return mBaseCoordinate;
    }
}
