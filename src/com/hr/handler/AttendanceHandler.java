package com.hr.handler;

import com.hr.bean.Attendance;
import com.hr.bean.multibean.MyDay;
import com.hr.service.interf.AttendanceService;
import com.hr.util.AttendanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@RequestMapping("/attendance")
@Controller("attendanceHandler")
public class AttendanceHandler {
    @Autowired
    @Qualifier("attendanceService")
    private AttendanceService attendanceService;

    public Attendance initAttendance(Integer userId) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return attendanceService.queryByDate(userId, year, month, day);
    }

    @RequestMapping(value = "/insertClockIn/{userId}", method = RequestMethod.GET)
    public String insertClockIn(@PathVariable("userId") Integer userId, Map<String, Object> map) {
        Attendance attendance = initAttendance(userId);
        Date date = new Date();
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (hour > AttendanceUtil.clock_in_hour - 1) {
            if (hour > 11) {
                attendance.setState(4);
            } else {
                attendance.setState(1);
            }
        }
        attendance.setClockInTime(date);
        attendanceService.insertOrUpdateAttendance(attendance);
        queryByCurrentMonth(userId, map);
        return "employee/employee-attendance";
    }

    @RequestMapping(value = "/insertClockOut/{userId}", method = RequestMethod.GET)
    public String insertClockOut(@PathVariable("userId") Integer userId, Map<String, Object> map) {
        Attendance attendance = initAttendance(userId);
        Date date = new Date();
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if(attendance.getState() == null){
            attendance.setState(4);
        }else {
            if (hour < AttendanceUtil.clock_out_hour) {
                if (hour < 14) {
                    attendance.setState(4);
                } else {
                    if (attendance.getState() == 1) {
                        attendance.setState(3);
                    } else {
                        attendance.setState(2);
                    }
                }
            } else {
                attendance.setState(0);
            }
        }
        attendance.setClockOutTime(date);
        attendanceService.insertOrUpdateAttendance(attendance);
        queryByCurrentMonth(userId, map);
        return "employee/employee-attendance";
    }

    @RequestMapping(value = "/queryByCurrentMonth/{userId}", method = RequestMethod.GET)
    public String queryByCurrentMonth(@PathVariable("userId") Integer userId, Map<String, Object> map) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        List<Attendance> attendances = attendanceService.queryByMonth(userId, year, month);
        for (Attendance attendance : attendances) {
            if (attendance.getDay() == day) {
                map.put("todayAttendance", attendance);
            }
        }
        map.put("attendances", attendances);
        return "employee/employee-attendance";
    }

    @RequestMapping(value = "/queryByDate", method = RequestMethod.POST)
    public String queryByDate(MyDay myDay, Map<String, Object> map) {
        Integer userId = Integer.parseInt(myDay.getUserId());
        Attendance attendance = attendanceService.queryByDate(userId, myDay.getYear(), myDay.getMonth(), myDay.getDay());
        queryByCurrentMonth(userId, map);
        map.put("attendance", attendance);
        return "employee/employee-attendance";
    }


    @RequestMapping(value = "/queryByMonthByUserId", method = RequestMethod.POST)
    public String queryByMonthByUserId(MyDay myDay, Map<String, Object> map) {
        List<Attendance> attendances = new ArrayList<>();
        if (myDay.getUserId().equals("")) {
            attendances = attendanceService.queryByMonthAll(myDay.getYear(), myDay.getMonth());
        } else {
            Integer userId = Integer.parseInt(myDay.getUserId());
            attendances = attendanceService.queryByMonth(userId, myDay.getYear(), myDay.getMonth());
        }
        map.put("attendances", attendances);
        return "manager/manager-attendance";
    }


    @RequestMapping(value = "/queryByMonthAll", method = RequestMethod.GET)
    public String queryByMonthAll(Map<String, Object> map) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        List<Attendance> attendances = attendanceService.queryByMonthAll(year, month);
        map.put("attendances", attendances);
        return "manager/manager-attendance";
    }

}
