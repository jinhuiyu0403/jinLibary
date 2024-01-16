package com.jin.mylibrary.MPChart.interfaces.dataprovider;

import com.jin.mylibrary.MPChart.data.ScatterData;

public interface ScatterDataProvider extends BarLineScatterCandleBubbleDataProvider {

    ScatterData getScatterData();
}
