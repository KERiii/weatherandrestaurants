package com.danielkeresztes.weatherandrestaurants.weather;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielkeresztes.weatherandrestaurants.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class WeatherFragment extends Fragment {

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
        location.setText("Dubai");
        day.setText("Sunday");
        forecast.setText("Sunny with clouds");
        temperature.setText("32");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
