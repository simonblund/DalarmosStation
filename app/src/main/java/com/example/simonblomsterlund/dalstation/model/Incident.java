package com.example.simonblomsterlund.dalstation.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by simonblomsterlund on 16/06/2017.
 */

public class Incident {


    public Incident(String message, String address, String type, String area, String details, String time) {
        this.message = message;
        this.address = address;
        this.type = type;
        this.area = area;
        this.details = details;
        this.time = time;
    }

    private String message;
    private String address;
    private String posLan;
    private String posLon;
    private String type;
    private String area;
    private String details;
    private String time;
    private int id;


}
