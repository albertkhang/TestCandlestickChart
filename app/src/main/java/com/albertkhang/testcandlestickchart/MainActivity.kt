package com.albertkhang.testcandlestickchart

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private lateinit var mEntries: MutableList<CandleEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addControl()
        addEvent()
    }

    private fun addControl() {
        initialEntries()

        chart.isHighlightPerDragEnabled = true
        chart.setDrawBorders(true)

        chart.setBorderColor(R.color.colorText)
        val yAxis: YAxis = chart.getAxisLeft()
        val rightAxis: YAxis = chart.getAxisRight()
        yAxis.setDrawGridLines(true)
        rightAxis.setDrawGridLines(false)
        rightAxis.setDrawLabels(false)
        chart.requestDisallowInterceptTouchEvent(true)

        val xAxis: XAxis = chart.getXAxis()

        xAxis.setDrawGridLines(false) // disable x axis grid lines

        xAxis.setDrawLabels(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        rightAxis.textColor = Color.BLUE
        yAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true
        xAxis.setAvoidFirstLastClipping(true)

        val l: Legend = chart.getLegend()
        l.isEnabled = false

        val set1 = initialDataSet()
//        set1.color = Color.rgb(80, 80, 80)
//        set1.shadowColor = resources.getColor(R.color.colorBackgroundChart)
//        set1.shadowWidth = 1f
//        set1.decreasingColor = resources.getColor(R.color.colorDecrease)
//        set1.decreasingPaintStyle = Paint.Style.FILL
//        set1.increasingColor = resources.getColor(R.color.colorIncrease)
//        set1.increasingPaintStyle = Paint.Style.FILL
//        set1.neutralColor = Color.RED
//        set1.setDrawValues(false)

// create a data object with the datasets
        val data = CandleData(set1)

// set data
        chart.setData(data)
        chart.invalidate()
    }

    private fun initialDataSet(): CandleDataSet {
        val candleDataSet = CandleDataSet(mEntries, "BTC Market")
        candleDataSet.axisDependency = YAxis.AxisDependency.LEFT
        candleDataSet.shadowColorSameAsCandle = true
        candleDataSet.shadowWidth = 1f
        candleDataSet.decreasingColor = resources.getColor(R.color.colorDecrease)
        candleDataSet.decreasingPaintStyle = Paint.Style.FILL
        candleDataSet.increasingColor = resources.getColor(R.color.colorIncrease)
        candleDataSet.increasingPaintStyle = Paint.Style.FILL
        candleDataSet.neutralColor = Color.BLUE
        candleDataSet.valueTextColor = resources.getColor(R.color.colorText)
        candleDataSet.setColors(R.color.colorText)

        return candleDataSet
    }

    private fun initialEntries() {
        mEntries = arrayListOf()

        mEntries.add(
            CandleEntry(
                1f,
                9220.00000000f,
                9206.10000000f,
                9216.24000000f,
                9216.83000000f
            )
        )

        mEntries.add(
            CandleEntry(
                2f,
                9223.56000000f,
                9209.47000000f,
                9216.85000000f,
                9218.54000000f
            )
        )

        mEntries.add(
            CandleEntry(
                3f,
                9235.00000000f,
                9157.50000000f,
                9218.54000000f,
                9220.28000000f
            )
        )

        mEntries.add(
            CandleEntry(
                4f,
                9232.87000000f,
                9212.83000000f,
                9220.28000000f,
                9230.87000000f
            )
        )

        mEntries.add(
            CandleEntry(
                5f,
                9272.72000000f,
                9220.39000000f,
                9230.86000000f,
                9260.60000000f
            )
        )

        mEntries.add(
            CandleEntry(
                6f,
                9327.00000000f,
                9260.62000000f,
                9260.62000000f,
                9298.46000000f
            )
        )

        mEntries.add(
            CandleEntry(
                7f,
                9305.00000000f,
                9272.00000000f,
                9298.47000000f,
                9302.75000000f
            )
        )

        mEntries.add(
            CandleEntry(
                8f,
                9321.34000000f,
                9265.53000000f,
                9303.31000000f,
                9275.81000000f
            )
        )

        mEntries.add(
            CandleEntry(
                9f,
                9307.76000000f,
                9271.87000000f,
                9275.82000000f,
                9284.56000000f
            )
        )

        mEntries.add(
            CandleEntry(
                10f,
                9290.82000000f,
                9274.99000000f,
                9284.56000000f,
                9281.70000000f
            )
        )

        mEntries.add(
            CandleEntry(
                11f,
                9220.00000000f,
                9206.10000000f,
                9216.24000000f,
                9216.83000000f
            )
        )

        mEntries.add(
            CandleEntry(
                12f,
                9223.56000000f,
                9209.47000000f,
                9216.85000000f,
                9218.54000000f
            )
        )

        mEntries.add(
            CandleEntry(
                13f,
                9235.00000000f,
                9157.50000000f,
                9218.54000000f,
                9220.28000000f
            )
        )

        mEntries.add(
            CandleEntry(
                14f,
                9232.87000000f,
                9212.83000000f,
                9220.28000000f,
                9230.87000000f
            )
        )

        mEntries.add(
            CandleEntry(
                15f,
                9272.72000000f,
                9220.39000000f,
                9230.86000000f,
                9260.60000000f
            )
        )

        mEntries.add(
            CandleEntry(
                16f,
                9327.00000000f,
                9260.62000000f,
                9260.62000000f,
                9298.46000000f
            )
        )

        mEntries.add(
            CandleEntry(
                17f,
                9305.00000000f,
                9272.00000000f,
                9298.47000000f,
                9302.75000000f
            )
        )

        mEntries.add(
            CandleEntry(
                18f,
                9321.34000000f,
                9265.53000000f,
                9303.31000000f,
                9275.81000000f
            )
        )

        mEntries.add(
            CandleEntry(
                19f,
                9307.76000000f,
                9271.87000000f,
                9275.82000000f,
                9284.56000000f
            )
        )

        mEntries.add(
            CandleEntry(
                20f,
                9290.82000000f,
                9274.99000000f,
                9284.56000000f,
                9281.70000000f
            )
        )
    }

    private fun addEvent() {
//        TODO("Not yet implemented")
    }
}