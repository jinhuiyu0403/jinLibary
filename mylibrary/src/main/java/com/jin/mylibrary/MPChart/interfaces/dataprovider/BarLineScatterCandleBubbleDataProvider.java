package com.jin.mylibrary.MPChart.interfaces.dataprovider;

import com.jin.mylibrary.MPChart.components.YAxis.AxisDependency;
import com.jin.mylibrary.MPChart.data.BarLineScatterCandleBubbleData;
import com.jin.mylibrary.MPChart.utils.Transformer;

public interface BarLineScatterCandleBubbleDataProvider extends ChartInterface {

    Transformer getTransformer(AxisDependency axis);
    boolean isInverted(AxisDependency axis);
    
    float getLowestVisibleX();
    float getHighestVisibleX();

    BarLineScatterCandleBubbleData getData();
}
