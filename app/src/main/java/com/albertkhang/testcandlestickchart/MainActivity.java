package com.albertkhang.testcandlestickchart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;

import com.albertkhang.testcandlestickchart.charts.CandlesStickChart;
import com.albertkhang.testcandlestickchart.utils.XAxis;
import com.albertkhang.testcandlestickchart.utils.YAxis;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    public static ArrayList<CandleEntry> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setContentView(new CandlesStickChart(this));
//        final CandlesStickChart view = new CandlesStickChart(this);
//
//        ViewTreeObserver observer = view.getViewTreeObserver();
//        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                Log.d("measure", "height: " + view.getHeight() + ", width: " + view.getWidth());
//            }
//        });

//        setContentView(view);
//
//        values = new ArrayList<>();
//
//        for (int i = 0; i < 7; i++) {
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
//            values.add(new CandleEntry(
//                    i, val + high,
//                    val - low,
//                    even ? val + open : val - open,
//                    even ? val - close : val + close
//            ));
//        }

    }
}