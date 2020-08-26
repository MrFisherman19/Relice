package com.mrfisherman.relice.Entity.Property;

import javax.persistence.*;

@Embeddable
public class Localization {

    @OneToOne(targetEntity = Floor.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "FLOOR_ID")
    private Floor floor;

    private int xAxis;
    private int yAxis;

    public Localization() { }

    public Localization(Floor floor, int xAxis, int yAxis) {
        this.floor = floor;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
    }

    public int getxAxis() {
        return xAxis;
    }

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public int getYAxis() {
        return yAxis;
    }

    public void setYAxis(int y) {
        this.yAxis = y;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Localization{" +
                "floor=" + floor +
                ", x=" + xAxis +
                ", y=" + yAxis +
                '}';
    }
}
