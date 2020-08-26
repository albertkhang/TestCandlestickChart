package com.albertkhang.testcandlestickchart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.albertkhang.testcandlestickchart.chart.CandlestickChart;
import com.albertkhang.testcandlestickchart.dataset.CandleDataSet;
import com.albertkhang.testcandlestickchart.model.ICandleData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private int mSize = 20;

    private FrameLayout chart_frame;
    private Button btnCreateNew;

    private CandlestickChart mChart;
    private CandleDataSet mDataSet;
    private ArrayList<ICandleData> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();
    }

    private void addControl() {
        chart_frame = findViewById(R.id.chart_frame);
        btnCreateNew = findViewById(R.id.btnCreateNew);

        mData = new ArrayList<>();
        createData();

        mDataSet = new CandleDataSet(mData);
        mChart = new CandlestickChart(this, mDataSet);

        chart_frame.addView(mChart);
    }

    private void createData() {
        mData.clear();

        for (int i = 0; i < mSize; i++) {
            float val = (float) (Math.random() * 40);

            float high = (float) (Math.random() * 9) + 8f;
            float low = (float) (Math.random() * 9) + 8f;

            float open = (float) (Math.random() * 6) + 1f;
            float close = (float) (Math.random() * 6) + 1f;

            boolean even = i % 2 == 0;

            mData.add(new ICandleData(
                    val + high,
                    val - low,
                    even ? val + open : val - open,
                    even ? val - close : val + close
            ));
        }
    }

    private void addEvent() {
        btnCreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chart_frame.removeView(mChart);

                mData.clear();
                createData();

                mDataSet = new CandleDataSet(mData);
                mChart = new CandlestickChart(getBaseContext(), mDataSet);

                chart_frame.addView(mChart);
            }
        });
    }
}