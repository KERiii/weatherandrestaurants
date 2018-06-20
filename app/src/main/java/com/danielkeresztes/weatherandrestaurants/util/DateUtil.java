package com.danielkeresztes.weatherandrestaurants.util;


import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtil {

    private DateUtil() {
    }

    public static String getTimeWithPeriod(long timeStamp) {
        Date dt = new Date(timeStamp * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        return sdf.format(dt);
    }
}
