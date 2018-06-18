package com.danielkeresztes.weatherandrestaurants.weather;


import com.danielkeresztes.weatherandrestaurants.weather.data.CurrentWeather;
import com.danielkeresztes.weatherandrestaurants.weather.domain.CurrentWeatherModel;
import com.danielkeresztes.weatherandrestaurants.weather.domain.LocationModel;
import com.danielkeresztes.weatherandrestaurants.weather.network.WeatherClient;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepository {

    private static WeatherRepository INSTANCE = null;

    private LocationModel locationModel;

    private WeatherRepository() {

    }

    public static WeatherRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WeatherRepository();
        }
        return INSTANCE;
    }

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public void setLocationModel(LocationModel locationModel) {
        this.locationModel = locationModel;
    }

    public Single<CurrentWeatherModel> getCurrentWeather() {
        Map<String, String> map = new HashMap<>();
        map.put("lat", String.valueOf(getLocationModel().getLatitude()));
        map.put("lon", String.valueOf(getLocationModel().getLongitude()));
        map.put("units", "metric");
        map.put("appid", "b08d62bfa01067ec8fa7358869da5f55");

        return WeatherClient.getWeatherApi().getCurrentWeatherByGeoCoordinates(map)
                .map(this::mapCurrentWeather)
                .subscribeOn(Schedulers.io());
    }

    private CurrentWeatherModel mapCurrentWeather(CurrentWeather currentWeather) {
        CurrentWeatherModel model = new CurrentWeatherModel();
        model.setCity(currentWeather.getName());
        model.setForecast(currentWeather.getWeatherArray().get(0).getMain());
        model.setTemperature(currentWeather.getMain().getTemp());
        model.setWeatherId(currentWeather.getWeatherArray().get(0).getId());
        return model;
    }
}
