package com.hr.dao.interf;

import com.hr.bean.Attendance;

import java.util.List;

public interface AttendanceDao {
    Attendance queryByDate(Integer userId,int year,int month,int day);

    List<Attendance> queryByMonth(Integer userId, int year, int month);

    void insertOrUpdateAttendance(Attendance attendance);

    List<Attendance> queryByMonthAll(int year,int month);
}
