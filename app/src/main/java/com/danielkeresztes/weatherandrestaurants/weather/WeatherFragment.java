package com.danielkeresztes.weatherandrestaurants.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielkeresztes.weatherandrestaurants.R;
import com.danielkeresztes.weatherandrestaurants.weather.domain.CurrentWeatherModel;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class WeatherFragment extends Fragment implements WeatherContract.View {

    @BindView(R.id.weatherLocation)
    TextView location;
    @BindView(R.id.weatherDay)
    TextView day;
    @BindView(R.id.weatherForecast)
    TextView forecast;
    @BindView(R.id.weatherTemperature)
    TextView temperature;
    @BindView(R.id.weatherDegree)
    TextView degree;
    @BindView(R.id.weatherImage)
    ImageView image;

    private Unbinder unbinder;
    private WeatherContract.Presenter presenter;

    public WeatherFragment() {

    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        day.setText(Calendar.getInstance().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onWeatherLoaded(CurrentWeatherModel currentWeatherModel) {
        location.setText(currentWeatherModel.getCity());
        forecast.setText(currentWeatherModel.getForecast());
        temperature.setText(String.valueOf(Math.round(currentWeatherModel.getTemperature())));
        image.setImageResource(R.drawable.ic_clear);
    }

    @Override
    public void onForecastLoaded() {

    }
}
