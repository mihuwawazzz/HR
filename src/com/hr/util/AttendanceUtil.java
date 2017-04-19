package com.hr.util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AttendanceUtil {

    public static boolean isRun(int year) {
        return (0 == year % 4 && (year % 100 != 0 || year % 400 == 0));
    }

    public static int getDay(int year, int month) {
        int day;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            day = 31;
        } else if (month == 2) {
            if (isRun(year)) {
                day = 29;
            } else {
                day = 28;
            }
        } else {
            day = 30;
        }
        return day;
    }

    public static List<Integer> noWeekendDays(int year, int month) {
        int ds = getDay(year, month) + 1;
        List<Integer> days = new ArrayList<>();
        for (int i = 1; i < ds; i++) {
            String date = year + "-" + month + "-" + i;
            Calendar ca = Calendar.getInstance();
            try {
                ca.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int week = ca.get(Calendar.DAY_OF_WEEK);
            if (week != 1 && week != 7) {
                days.add(i);
            }
        }
        return days;

    }
}
