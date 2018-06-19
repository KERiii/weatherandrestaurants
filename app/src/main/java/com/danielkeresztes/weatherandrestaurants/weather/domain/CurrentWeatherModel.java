package com.danielkeresztes.weatherandrestaurants.weather.domain;


public class CurrentWeatherModel {

    private String city;
    private String forecast;
    private double temperature;
    private int weatherId;
    private double pressure;
    private double humidity;
    private double wind;
    private long sunRise;
    private long sunSet;
    private double visibility;

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWind() {
        return wind;
    }

    public void setWind(double wind) {
        this.wind = wind;
    }

    public long getSunRise() {
        return sunRise;
    }

    public void setSunRise(long sunRise) {
        this.sunRise = sunRise;
    }

    public long getSunSet() {
        return sunSet;
    }

    public void setSunSet(long sunSet) {
        this.sunSet = sunSet;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getForecast() {
        return forecast;
    }

    public void setForecast(String forecast) {
        this.forecast = forecast;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
