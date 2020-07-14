package com.albertkhang.testcandlestickchart.interfaces.dataprovider;

import com.albertkhang.testcandlestickchart.data.CandleData;

public interface CandleDataProvider extends BarLineScatterCandleBubbleDataProvider {

    CandleData getCandleData();
}
