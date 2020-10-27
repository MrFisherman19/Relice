package com.mrfisherman.relice.Service.Reports.Charts;

import java.util.Map;

public class PieChart<N extends Number> extends Chart {

    public PieChart(Map<String, N> data, int width, int height) {
        super.setChartType(ChartType.PIE);
        super.setWidth(width);
        super.setHeight(height);
        super.setLabels(data.keySet());
        super.setData(data.values());
    }

}
