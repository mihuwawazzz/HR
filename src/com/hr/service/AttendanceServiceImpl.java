package com.hr.service;

import com.hr.bean.Attendance;
import com.hr.dao.interf.AttendanceDao;
import com.hr.service.interf.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    @Qualifier("attendanceDao")
    private AttendanceDao attendanceDao;

    @Override
    public Attendance queryByDate(Integer userId,int year,int month,int day) {
        return attendanceDao.queryByDate(userId,year,month,day);
    }

    @Override
    public List<Attendance> queryByMonth(Integer userId, int year, int month) {
        return attendanceDao.queryByMonth(userId,year,month);
    }

    @Override
    public void insertOrUpdateAttendance(Attendance attendance) {
        attendanceDao.insertOrUpdateAttendance(attendance);
    }

    @Override
    public List<Attendance> queryByMonthAll(int year,int month) {
        return attendanceDao.queryByMonthAll(year,month);
    }
}
