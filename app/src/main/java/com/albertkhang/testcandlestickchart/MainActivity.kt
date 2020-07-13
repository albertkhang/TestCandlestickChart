package com.albertkhang.testcandlestickchart

import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.Description
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


        val candleData = CandleData(initialDataSet())
        chart.data = candleData

        //Sets the background color that will cover the whole chart-view.
        chart.setBackgroundColor(resources.getColor(R.color.colorBackgroundChart))

        //Set a description text that appears in the bottom right corner of the chart.
        val des = Description()
        //setText
        des.text = "This is description"
        //setColor
        des.textColor = resources.getColor(R.color.colorText)
        chart.description = des

        //If enabled, the background rectangle behind the chart drawing-area will be drawn.
        chart.setDrawGridBackground(false)

        chart.invalidate()
    }

    private fun initialDataSet(): CandleDataSet {
        val candleDataSet = CandleDataSet(mEntries, "BTC Market")
        candleDataSet.axisDependency = YAxis.AxisDependency.LEFT
        candleDataSet.shadowColorSameAsCandle = true
        candleDataSet.shadowWidth = 2f
        candleDataSet.decreasingColor = resources.getColor(R.color.colorDecrease)
        candleDataSet.decreasingPaintStyle = Paint.Style.FILL_AND_STROKE
        candleDataSet.increasingColor = resources.getColor(R.color.colorIncrease)
        candleDataSet.increasingPaintStyle = Paint.Style.FILL
        candleDataSet.neutralColor = Color.BLUE
        candleDataSet.color = resources.getColor(R.color.colorText)

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
    }

    private fun addEvent() {
//        TODO("Not yet implemented")
    }
}