package com.hr.dao;

import com.hr.bean.Attendance;
import com.hr.dao.interf.AttendanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("attendanceDao")
public class AttendanceDaoImpl implements AttendanceDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Override
    public Attendance queryByDate(Integer userId, int year, int month, int day) {
        List<Attendance> attendances = (List<Attendance>) hibernateTemplate.find("from Attendance " +
                "where userId= ? and year = ? and month=? and day=?", userId, year, month, day);
        if (attendances.size() != 0) {
            return attendances.get(0);
        }
        return null;
    }

    @Override
    public List<Attendance> queryByMonth(Integer userId, int year, int month) {
        return (List<Attendance>) hibernateTemplate.find("from Attendance " +
                "where userId= ? and year = ? and month=?", userId, year, month);
    }

    @Override
    public void insertOrUpdateAttendance(Attendance attendance) {
        hibernateTemplate.saveOrUpdate(attendance);
    }

    @Override
    public List<Attendance> queryByMonthAll(int year, int month) {
        return (List<Attendance>) hibernateTemplate.find
                ("from Attendance where year = ? and month=?", year, month);
    }
}
