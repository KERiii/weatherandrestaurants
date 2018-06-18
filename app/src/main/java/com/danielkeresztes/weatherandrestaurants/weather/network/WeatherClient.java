package com.danielkeresztes.weatherandrestaurants.weather.network;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {

    public static final String API_URL = "http://api.openweathermap.org/data/2.5/";

    private static Retrofit retrofit;

    private WeatherClient() {

    }

    public static WeatherApi getWeatherApi(){
        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(API_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(WeatherApi.class);
    }
}
