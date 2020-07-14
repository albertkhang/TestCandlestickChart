package com.albertkhang.testcandlestickchart.interfaces.dataprovider;

import com.albertkhang.testcandlestickchart.interfaces.dataprovider.BarLineScatterCandleBubbleDataProvider;
import com.albertkhang.testcandlestickchart.data.BarData;

public interface BarDataProvider extends BarLineScatterCandleBubbleDataProvider {

    BarData getBarData();
    boolean isDrawBarShadowEnabled();
    boolean isDrawValueAboveBarEnabled();
    boolean isHighlightFullBarEnabled();
}
