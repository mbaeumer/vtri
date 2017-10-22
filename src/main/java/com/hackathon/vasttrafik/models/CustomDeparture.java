package com.hackathon.vasttrafik.models;

/**
 * Created by martinbaumer on 22/10/17.
 */
public class CustomDeparture {
    private String name;
    private String type;
    private String rtTime;
    private String accessibility;
    private String direction;

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRtTime() {
        return rtTime;
    }

    public void setRtTime(String rtTime) {
        this.rtTime = rtTime;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
