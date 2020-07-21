package com.albertkhang.testcandlestickchart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<CandleItem> mItems;
    private CandlestickChart mChart;
    private CandlestickSettings mSettings;
    private int mSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = new ArrayList<>();
        mChart = new CandlestickChart(this);
        mSettings = new CandlestickSettings(this);
        mSettings.setGridMargin(50);
        mSettings.setAxisMargin(20);
        mSettings.setXAxisLabelMargin(16);
        mSettings.setYAxisLabelMargin(10);
        mSettings.setAxisLabelColorId(R.color.colorAxisLabel);
        mSettings.setAxisColorId(R.color.colorBaseAxis);
        mSettings.setGridColorId(R.color.colorGridBackground);
        mSettings.setGridWidth(4);

//        for (int i = 0; i < mSize; i++) {
//            float val = (float) (Math.random() * 40);
//
//            float high = (float) (Math.random() * 9) + 8f;
//            float low = (float) (Math.random() * 9) + 8f;
//
//            float open = (float) (Math.random() * 6) + 1f;
//            float close = (float) (Math.random() * 6) + 1f;
//
//            boolean even = i % 2 == 0;
//
//            mItems.add(new CandleItem(
//                    val + high,
//                    val - low,
//                    even ? val + open : val - open,
//                    even ? val - close : val + close
//            ));
//        }

        mChart.setSettings(mSettings);
        mChart.setItems(mItems);
        setContentView(mChart);
    }
}