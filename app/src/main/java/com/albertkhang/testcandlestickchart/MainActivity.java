package com.albertkhang.testcandlestickchart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;

import com.albertkhang.testcandlestickchart.charts.BarLineChartBase;
import com.albertkhang.testcandlestickchart.charts.CandleStickChart;
import com.albertkhang.testcandlestickchart.components.LimitLine;
import com.albertkhang.testcandlestickchart.components.XAxis;
import com.albertkhang.testcandlestickchart.components.YAxis;
import com.albertkhang.testcandlestickchart.data.CandleData;
import com.albertkhang.testcandlestickchart.data.CandleDataSet;
import com.albertkhang.testcandlestickchart.data.CandleEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private CandleStickChart chart;
    private int mSize = 100;
    public static final String FLOW_TAG = "flowLog";
    public static final String VALUE_TAG = "valueLog";
    public static final String TOUCH_TAG = "touchLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(FLOW_TAG, "MainActivity onCreate");
        chart = new CandleStickChart(this);
//        setContentView(R.layout.activity_main);

        setContentView(chart);
        chart.setBackgroundColor(Color.WHITE);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);
        chart.setAutoScaleMinMaxEnabled(true);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(true);

        chart.setDrawGridBackground(false);
        if (mSize <= 10) {
            chart.setScaleMinima(1, 1);
        } else {
            chart.setScaleMinima(mSize / 10, 1);
            Log.d("scale", "scaleX: " + mSize / 10);
        }

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);

        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setEnabled(false);
        leftAxis.setLabelCount(10, false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(true);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
//        rightAxis.setStartAtZero(false);

        //Bật tắt chú thích của chart. Ví dụ: màu xanh là gì, màu đỏ là gì
        chart.getLegend().setEnabled(false);

        ArrayList<CandleEntry> values = new ArrayList<>();

        for (int i = 0; i < mSize; i++) {
            float val = (float) (Math.random() * 40);

            float high = (float) (Math.random() * 9) + 8f;
            float low = (float) (Math.random() * 9) + 8f;

            float open = (float) (Math.random() * 6) + 1f;
            float close = (float) (Math.random() * 6) + 1f;

            boolean even = i % 2 == 0;

            values.add(new CandleEntry(
                    i, val + high,
                    val - low,
                    even ? val + open : val - open,
                    even ? val - close : val + close
            ));
        }

        CandleDataSet set1 = new CandleDataSet(values, "Data Set");

        set1.setDrawIcons(false);
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set1.setColor(Color.rgb(80, 80, 80));
        set1.setShadowColor(Color.DKGRAY);
        set1.setShadowWidth(1f);
        set1.setDecreasingColor(getResources().getColor(R.color.colorDecreasing));
        set1.setDecreasingPaintStyle(Paint.Style.FILL);
        set1.setIncreasingColor(getResources().getColor(R.color.colorIncreasing));
        set1.setIncreasingPaintStyle(Paint.Style.FILL);
        set1.setNeutralColor(Color.BLUE);
        set1.setShowCandleBar(true);
        set1.setShadowColorSameAsCandle(true);
//        set1.setBarSpace(1f);
        set1.setHighlightLineWidth(1f);

        CandleData data = new CandleData(set1);

        chart.setAutoScaleMinMaxEnabled(false);
        chart.setDrawBorders(false);
        chart.setDrawGridBackground(false);

        chart.setData(data);
        chart.invalidate();
    }
}