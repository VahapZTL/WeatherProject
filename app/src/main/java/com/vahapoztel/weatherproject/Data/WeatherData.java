package com.vahapoztel.weatherproject.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherData {

    @SerializedName("Lon")
    @Expose
    private Double lon;
    @SerializedName("Lat")
    @Expose
    private Double lat;
    @SerializedName("Weather")
    @Expose
    private String weather;
    @SerializedName("Temp")
    @Expose
    private Double temp;
    @SerializedName("City")
    @Expose
    private String city;
    @SerializedName("Update")
    @Expose
    private String update;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

}
