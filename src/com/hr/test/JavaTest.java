package com.hr.test;

import com.hr.util.AttendanceUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.List;

public class JavaTest {

    @Test
    public void tt(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        List<Integer> days = AttendanceUtil.noWeekendDays(year,month);
        System.out.println(days);
    }

}
