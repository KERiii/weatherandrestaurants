package com.danielkeresztes.weatherandrestaurants.weather;


import com.danielkeresztes.weatherandrestaurants.BasePresenter;
import com.danielkeresztes.weatherandrestaurants.BaseView;
import com.danielkeresztes.weatherandrestaurants.weather.domain.CurrentWeatherModel;
import com.danielkeresztes.weatherandrestaurants.weather.domain.ForecastModel;
import com.danielkeresztes.weatherandrestaurants.weather.domain.LocationModel;

import java.util.List;

public interface WeatherContract {

    interface Presenter extends BasePresenter {
        void onLocationLoaded(LocationModel locationModel);
    }

    interface View extends BaseView<Presenter> {
        void onWeatherLoaded(CurrentWeatherModel currentWeatherModel);

        void onForecastLoaded(List<ForecastModel> forecastModels);
    }
}
