package com.albertkhang.testcandlestickchart.charts;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.albertkhang.testcandlestickchart.utils.XAxis;
import com.albertkhang.testcandlestickchart.utils.YAxis;

public class CandlesStickChart extends View {
    private static final String TAG = "CandlesStickChart";

    private XAxis xAxis;
    private YAxis yAxis;

    public CandlesStickChart(Context context) {
        super(context);

        xAxis = new XAxis(context);
        yAxis = new YAxis(context, getMeasuredHeight());

        Log.d(TAG, "xAxis: " + xAxis.getBaseCoordinate() + ", yAxis: " + yAxis.getBaseCoordinate());
    }
}
