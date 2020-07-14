package com.albertkhang.testcandlestickchart.interfaces.dataprovider;

import com.albertkhang.testcandlestickchart.components.YAxis.AxisDependency;
import com.albertkhang.testcandlestickchart.data.BarLineScatterCandleBubbleData;
import com.albertkhang.testcandlestickchart.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);
    boolean isInverted(AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
