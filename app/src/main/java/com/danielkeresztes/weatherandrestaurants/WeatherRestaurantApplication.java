package com.danielkeresztes.weatherandrestaurants;


import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import timber.log.Timber;

public class WeatherRestaurantApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        Timber.plant(new Timber.DebugTree());
    }
}
