package com.example.simonblomsterlund.dalstation.model;

/**
 * Created by simonblomsterlund on 16/06/2017.
 */

public class Incident {


    private String message;
    private String address;
    private String posLan;
    private String posLon;
    private String type;
    private String area;
    private String details;
    private String time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosLan() {
        return posLan;
    }

    public void setPosLan(String posLan) {
        this.posLan = posLan;
    }

    public String getPosLon() {
        return posLon;
    }

    public void setPosLon(String posLon) {
        this.posLon = posLon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
