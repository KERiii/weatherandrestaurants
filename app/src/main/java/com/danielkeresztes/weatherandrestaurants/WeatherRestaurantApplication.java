package com.danielkeresztes.weatherandrestaurants;


import android.app.Application;

import timber.log.Timber;

public class WeatherRestaurantApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
