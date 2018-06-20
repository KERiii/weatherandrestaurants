package com.danielkeresztes.weatherandrestaurants.weather;


import com.danielkeresztes.weatherandrestaurants.weather.data.CurrentWeather;
import com.danielkeresztes.weatherandrestaurants.weather.data.ThreeHourForecast;
import com.danielkeresztes.weatherandrestaurants.weather.data.ThreeHourWeather;
import com.danielkeresztes.weatherandrestaurants.weather.data.Weather;
import com.danielkeresztes.weatherandrestaurants.weather.domain.CurrentWeatherModel;
import com.danielkeresztes.weatherandrestaurants.weather.domain.ForecastModel;
import com.danielkeresztes.weatherandrestaurants.weather.domain.LocationModel;
import com.danielkeresztes.weatherandrestaurants.weather.network.WeatherClient;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepository {

    private static final String APPID = "b08d62bfa01067ec8fa7358869da5f55";

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
        map.put("appid", APPID);

        return WeatherClient.getWeatherApi().getCurrentWeatherByGeoCoordinates(map)
                .map(this::mapCurrentWeather)
                .subscribeOn(Schedulers.io());
    }

    public Single<List<ForecastModel>> getForecastModels() {
        Map<String, String> map = new HashMap<>();
        map.put("lat", String.valueOf(getLocationModel().getLatitude()));
        map.put("lon", String.valueOf(getLocationModel().getLongitude()));
        map.put("units", "metric");
        map.put("appid", APPID);

        return WeatherClient.getWeatherApi().getForecastByGeoCoordinates(map)
                .map(this::mapThreeHourForecast)
                .subscribeOn(Schedulers.io());
    }

    private CurrentWeatherModel mapCurrentWeather(CurrentWeather currentWeather) {
        CurrentWeatherModel model = new CurrentWeatherModel();
        model.setCity(currentWeather.getName());
        model.setForecast(currentWeather.getWeatherArray().get(0).getMain());
        model.setTemperature(currentWeather.getMain().getTemp());
        model.setWeatherId(currentWeather.getWeatherArray().get(0).getId());
        model.setHumidity(currentWeather.getMain().getHumidity());
        model.setPressure(currentWeather.getMain().getPressure());
        model.setWind(currentWeather.getWind().getSpeed());
        model.setSunRise(currentWeather.getSys().getSunrise());
        model.setSunSet(currentWeather.getSys().getSunset());
        model.setVisibility(currentWeather.getVisibility());
        return model;
    }

    private List<ForecastModel> mapThreeHourForecast(ThreeHourForecast threeHourForecast) {
        List<ForecastModel> forecastModels = new ArrayList<>();
        int prevDay = new DateTime().getDayOfWeek();
        int currDay;
        DateTime dateTime;
        for (ThreeHourWeather threeHourWeather : threeHourForecast.getThreeHourWeatherArray()) {
            Weather weather = threeHourWeather.getWeatherArray().get(0);
            dateTime = new DateTime(threeHourWeather.getDt() * 1000);
            currDay = dateTime.getDayOfWeek();
            if (currDay != prevDay) {
                ForecastModel model = new ForecastModel();
                model.setWeatherId(weather.getId());
                model.setDate(threeHourWeather.getDt());
                model.setTemperature(threeHourWeather.getMain().getTemp());
                forecastModels.add(model);
                prevDay = currDay;
            }
        }
        return forecastModels;

    }
}
