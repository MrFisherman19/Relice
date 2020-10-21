package com.mrfisherman.relice.Service.Reports.Charts;

import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
public abstract class Chart {

    private ChartType chartType;
    private int width;
    private int height;
    private Set<String> labels;
    private Collection<?> data;

}
