package com.github.danielrodj.models;

/**
 *
 * @author Angel Daniel Rodríguez Juárez
 */

public class Area {

    private int areaId;
    private String areaName;
    private String areaLocation;

    public Area() {
    }

    public Area(int areaId, String areaName, String areaLocation) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.areaLocation = areaLocation;
    }

    public int getAreaId() {
        return areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getAreaLocation() {
        return areaLocation;
    }
    
}
