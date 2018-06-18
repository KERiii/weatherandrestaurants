package com.danielkeresztes.weatherandrestaurants.weather.network;


import com.danielkeresztes.weatherandrestaurants.weather.data.CurrentWeather;

import java.util.Map;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface WeatherApi {

    @GET("weather")
    Single<CurrentWeather> getCurrentWeatherByGeoCoordinates(@QueryMap Map<String, String> optionse);
}
