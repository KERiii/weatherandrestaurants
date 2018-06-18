package com.danielkeresztes.weatherandrestaurants;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.danielkeresztes.weatherandrestaurants.weather.WeatherFragment;
import com.danielkeresztes.weatherandrestaurants.weather.WeatherPresenter;
import com.danielkeresztes.weatherandrestaurants.weather.WeatherRepository;
import com.danielkeresztes.weatherandrestaurants.weather.domain.LocationModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.tbruyelle.rxpermissions2.RxPermissions;


public class WeatherActivity extends AppCompatActivity {

    private FusedLocationProviderClient locationProviderClient;
    private RxPermissions rxPermissions;
    WeatherPresenter weatherPresenter;
    WeatherFragment weatherFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getLocation();
    }

    private void init() {
        weatherFragment = WeatherFragment.newInstance();
        weatherPresenter = new WeatherPresenter(WeatherRepository.getInstance(), weatherFragment);
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        rxPermissions = new RxPermissions(this);
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(provided -> locationProviderClient.getLastLocation()
                        .addOnSuccessListener(this, location -> {
                            if (location != null) {
                                weatherPresenter.onLocationLoaded(new LocationModel(location.getLatitude(), location.getLongitude()));
                                getSupportFragmentManager().beginTransaction()
                                        .add(R.id.content, weatherFragment)
                                        .commit();
                            }
                        }));

    }
}
