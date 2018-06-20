package com.danielkeresztes.weatherandrestaurants.weather;


import com.danielkeresztes.weatherandrestaurants.weather.domain.LocationModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class WeatherPresenter implements WeatherContract.Presenter {

    WeatherRepository repository;
    WeatherContract.View view;

    public WeatherPresenter(WeatherRepository repository, WeatherContract.View view) {
        this.repository = repository;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void start() {
        getWeather();
        getForecast();
    }

    private void getWeather() {
        repository.getCurrentWeather()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(currentWeather -> view.onWeatherLoaded(currentWeather));

    }

    private void getForecast() {
        repository.getForecastModels()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(forecastModels -> view.onForecastLoaded(forecastModels));
    }

    @Override
    public void onLocationLoaded(LocationModel locationModel) {
        repository.setLocationModel(locationModel);
    }
}
