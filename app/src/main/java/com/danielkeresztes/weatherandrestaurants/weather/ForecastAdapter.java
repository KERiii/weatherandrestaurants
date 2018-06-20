package com.danielkeresztes.weatherandrestaurants.weather;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danielkeresztes.weatherandrestaurants.R;
import com.danielkeresztes.weatherandrestaurants.weather.domain.ForecastModel;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder>{

    private List<ForecastModel> forecastModels = new ArrayList<>();

    public ForecastAdapter(List<ForecastModel> forecastModels) {
        this.forecastModels = forecastModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_forecast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ForecastModel forecastModel = forecastModels.get(position);
        setWeatherIcon(holder.forecastIcon, forecastModel.getWeatherId());
        holder.forecastTemp.setText(String.valueOf(Math.round(forecastModel.getTemperature())));
        DateTime dateTime = new DateTime(forecastModel.getDate() * 1000);
        DateTimeFormatter fmt = DateTimeFormat.forPattern("E");
        holder.forecastDay.setText(fmt.withLocale(Locale.getDefault()).print(dateTime));
    }

    @Override
    public int getItemCount() {
        return forecastModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.itemForecastDay)
        TextView forecastDay;
        @BindView(R.id.itemForecastIcon)
        ImageView forecastIcon;
        @BindView(R.id.itemForecastTemperature)
        TextView forecastTemp;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void setWeatherIcon(ImageView imageView, int weatherid) {
        if (weatherid == 800) {
            imageView.setImageResource(R.drawable.ic_clear);
        } else if (weatherid < 800) {
            imageView.setImageResource(R.drawable.ic_rain);
        } else {
            imageView.setImageResource(R.drawable.ic_clouds);
        }
    }
}
