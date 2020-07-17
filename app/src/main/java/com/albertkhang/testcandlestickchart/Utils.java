package com.albertkhang.testcandlestickchart;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class Utils {
    private Context mContext;
    private Resources mRes;

    public Utils(Context mContext) {
        this.mContext = mContext;
        this.mRes = mContext.getResources();
    }

    public float convertDpToPx(float dip) {
        if (mRes != null) {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, mRes.getDisplayMetrics());
        }

        return 0;
    }

    public void convertDpToPx(float[] buffers) {
        for (int i = 0; i < buffers.length; i++) {
            buffers[i] = convertDpToPx(buffers[i]);
        }
    }

    public float getMaxHeightPx() {
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

//    public float getMaxHeightDp() {
////        return ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
//        return getViewHeight(this)
//    }

    public float getMaxWidthDp() {
        Configuration configuration = mContext.getResources().getConfiguration();
        return configuration.screenWidthDp;
    }

    public float getViewHeight(final View view) {
        final float[] heigth = new float[1];

        ViewTreeObserver observer = view.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("measure", "height: " + view.getMeasuredHeightAndState() + ", width: " + view.getWidth());
                heigth[0] = view.getMeasuredHeight() / 3;
            }
        });

        Log.d("measure", "return height: " + view.getMeasuredHeight() / 3 + ", width: " + view.getWidth());
        return heigth[0];
    }
}
