package com.danielkeresztes.weatherandrestaurants.weather;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danielkeresztes.weatherandrestaurants.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ForecastDetailFragment extends Fragment {

    public static final String ARG_DETAIL_ONE = "ARG_DETAIL_ONE";
    public static final String ARG_DETAIL_ONE_CAPTION = "ARG_DETAIL_ONE_CAPTION";
    public static final String ARG_DETAIL_TWO = "ARG_DETAIL_TWO";
    public static final String ARG_DETAIL_TWO_CAPTION = "ARG_DETAIL_TWO_CAPTION";
    public static final String ARG_DETAIL_THREE = "ARG_DETAIL_THREE";
    public static final String ARG_DETAIL_THREE_CAPTION = "ARG_DETAIL_THREE_CAPTION";

    @BindView(R.id.forecastDetailOne)
    TextView detailOne;
    @BindView(R.id.forecastDetailOneCaption)
    TextView detailOneCaption;
    @BindView(R.id.forecastDetailTwo)
    TextView detailTwo;
    @BindView(R.id.forecastDetailTwoCaption)
    TextView detailTwoCaption;
    @BindView(R.id.forecastDetailThree)
    TextView detailThree;
    @BindView(R.id.forecastDetailThreeCaption)
    TextView detailThreeCaption;

    private Unbinder unbinder;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            detailOne.setText(args.getString(ARG_DETAIL_ONE));
            detailOneCaption.setText(args.getString(ARG_DETAIL_ONE_CAPTION));
            detailTwo.setText(args.getString(ARG_DETAIL_TWO));
            detailTwoCaption.setText(args.getString(ARG_DETAIL_TWO_CAPTION));
            detailThree.setText(args.getString(ARG_DETAIL_THREE));
            detailThreeCaption.setText(args.getString(ARG_DETAIL_THREE_CAPTION));
        }
    }
}
