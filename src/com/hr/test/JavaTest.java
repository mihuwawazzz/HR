package com.hr.test;


import com.hr.bean.Training;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class JavaTest {

    @Test
    public void dateTest() {
        String trainingDay = "3/7/2017";
        String trainingHour = "9";
        String trainingMinute = "0";
        String[] day = trainingDay.split("/");
        int year = Integer.parseInt(day[2]);
        int month = Integer.parseInt(day[1])-1;
        int days = Integer.parseInt(day[0]);
        int hour = Integer.parseInt(trainingHour);
        int minute = Integer.parseInt(trainingMinute);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, days, hour, minute, 0);
        Date date = calendar.getTime();
        System.out.println(date);
    }


}
