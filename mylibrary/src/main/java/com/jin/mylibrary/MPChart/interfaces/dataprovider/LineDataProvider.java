package com.jin.mylibrary.MPChart.interfaces.dataprovider;

import com.jin.mylibrary.MPChart.components.YAxis;
import com.jin.mylibrary.MPChart.data.LineData;

public interface LineDataProvider extends BarLineScatterCandleBubbleDataProvider {

    LineData getLineData();

    YAxis getAxis(YAxis.AxisDependency dependency);
}
