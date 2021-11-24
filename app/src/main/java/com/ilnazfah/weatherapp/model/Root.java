package com.ilnazfah.weatherapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Root {
    private Coord coord;
    private List<Weather> weather = new ArrayList<>();
    private String base;
    private Main main;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Integer dt;
    private Sys sys;
    private Integer timezone;
    private Integer id;
    private String name;
    private Integer cod;
    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }
    public String getBase() {
        return base;
    }
    public Main getMain() {
        return main;
    }
    public Integer getVisibility() {
        return visibility;
    }
    public Wind getWind() {
        return wind;
    }
    public Clouds getClouds() {
        return clouds;
    }
    public Integer getDt() {
        return dt;
    }
    public Sys getSys() {
        return sys;
    }
    public Integer getTimezone() {
        return timezone;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Integer getCod() {
        return cod;
    }

    @Override
    public String toString() {
        return "Root{" +
                "coord=" + coord +
                ", weather=" + weather +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                '}';
    }
}