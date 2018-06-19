package com.danielkeresztes.weatherandrestaurants.weather;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.danielkeresztes.weatherandrestaurants.R;
import com.danielkeresztes.weatherandrestaurants.weather.domain.CurrentWeatherModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherPagerAdapter extends FragmentPagerAdapter {

    private CurrentWeatherModel currentWeatherModel;
    private Context context;

    public WeatherPagerAdapter(Context context, FragmentManager fm, CurrentWeatherModel currentWeatherModel) {
        super(fm);
        this.currentWeatherModel = currentWeatherModel;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            default:
            case 0:
                ForecastDetailFragment detailFragmentOne = new ForecastDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString(ForecastDetailFragment.ARG_DETAIL_ONE, String.valueOf(Math.round(currentWeatherModel.getWind()))
                        + context.getString(R.string.kmh));
                bundle.putString(ForecastDetailFragment.ARG_DETAIL_ONE_CAPTION, context.getString(R.string.wind));
                bundle.putString(ForecastDetailFragment.ARG_DETAIL_TWO, String.valueOf(Math.round(currentWeatherModel.getPressure()))
                        + context.getString(R.string.hpa));
                bundle.putString(ForecastDetailFragment.ARG_DETAIL_TWO_CAPTION, context.getString(R.string.pressure));
                bundle.putString(ForecastDetailFragment.ARG_DETAIL_THREE, String.valueOf(Math.round(currentWeatherModel.getHumidity()))
                        + context.getString(R.string.percent));
                bundle.putString(ForecastDetailFragment.ARG_DETAIL_THREE_CAPTION, context.getString(R.string.humidity));
                detailFragmentOne.setArguments(bundle);
                return detailFragmentOne;
            case 1:
                ForecastDetailFragment detailFragmentTwo = new ForecastDetailFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString(ForecastDetailFragment.ARG_DETAIL_ONE, createDate(currentWeatherModel.getSunRise()));
                bundle2.putString(ForecastDetailFragment.ARG_DETAIL_ONE_CAPTION, context.getString(R.string.sunrise));
                bundle2.putString(ForecastDetailFragment.ARG_DETAIL_TWO, createDate(currentWeatherModel.getSunSet()));
                bundle2.putString(ForecastDetailFragment.ARG_DETAIL_TWO_CAPTION, context.getString(R.string.sunset));
                bundle2.putString(ForecastDetailFragment.ARG_DETAIL_THREE, String.valueOf(Math.round(currentWeatherModel.getVisibility()))
                        + context.getString(R.string.meter));
                bundle2.putString(ForecastDetailFragment.ARG_DETAIL_THREE_CAPTION, context.getString(R.string.visibility));
                detailFragmentTwo.setArguments(bundle2);
                return detailFragmentTwo;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    private String createDate(long timeStamp) {
        Date dt = new Date(timeStamp * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        return sdf.format(dt);
    }
}
