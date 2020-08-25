package com.albertkhang.testcandlestickchart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.albertkhang.testcandlestickchart.chart.CandlestickChart;
import com.albertkhang.testcandlestickchart.dataset.CandleDataSet;
import com.albertkhang.testcandlestickchart.model.ICandleData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int mSize = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        ArrayList<ICandleData> data = new ArrayList<>();

        for (int i = 0; i < mSize; i++) {
            float val = (float) (Math.random() * 40);

            float high = (float) (Math.random() * 9) + 8f;
            float low = (float) (Math.random() * 9) + 8f;

            float open = (float) (Math.random() * 6) + 1f;
            float close = (float) (Math.random() * 6) + 1f;

            boolean even = i % 2 == 0;

            data.add(new ICandleData(
                    val + high,
                    val - low,
                    even ? val + open : val - open,
                    even ? val - close : val + close
            ));
        }

        CandleDataSet set = new CandleDataSet(data);

        CandlestickChart chart = new CandlestickChart(this, set);
        setContentView(chart);
    }
}