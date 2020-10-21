package com.mrfisherman.relice.Service.Reports.Charts;

public enum ChartType {
    PIE("p"), DOUGHNUT("pd"), BAR_VERTICAL("bvg"), BAR_HORIZONTAL("bhg");

    private final String type;

    ChartType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
