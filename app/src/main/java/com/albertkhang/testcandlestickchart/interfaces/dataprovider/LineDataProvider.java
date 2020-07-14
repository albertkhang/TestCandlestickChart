package com.albertkhang.testcandlestickchart.interfaces.dataprovider;

import com.albertkhang.testcandlestickchart.components.YAxis;
import com.albertkhang.testcandlestickchart.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
