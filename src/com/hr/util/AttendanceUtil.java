package com.hr.util;

public class AttendanceUtil {
    public final static int clock_in_hour = 9;
    public final static int clock_in_minute = 0;
    public final static int clock_out_hour = 17;
    public final static int clock_out_minute = 0;

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
}
