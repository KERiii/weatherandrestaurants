package com.danielkeresztes.weatherandrestaurants;

import com.danielkeresztes.weatherandrestaurants.util.DateUtil;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateUtilUnitTest {
    @Test
    public void getTime_isCorrect() throws Exception {
        assertEquals(DateUtil.getTimeWithPeriod(1529523989L), "09:46 DU");
    }
}