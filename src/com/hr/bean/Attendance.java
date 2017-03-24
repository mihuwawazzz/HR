package com.hr.bean;

import java.util.Date;

/**
 * 考勤表
 */
public class Attendance {
    public static final Integer NORMAL = 0;                     //考勤正常
    public static final Integer ARRIVE_LATE = 1;                //迟到
    public static final Integer LEAVE_EARLY = 2;                //早退
    public static final Integer ARRIVE_LATE_LEAVE_EARLY= 3;     //迟到并且早退
    public static final Integer ABSENT = 4;                     //旷工
    public final static Integer CLOCK_IN_HOUR = 9;              //上班时间，小时
    public final static Integer CLOCK_OUT_HOUR = 17;            //下班时间，小时
    public final static Integer CLOCK_IN_ABSENT_HOUR = 11;      //上班旷工时间，小时
    public final static Integer CLOCK_OUT_ABSENT_HOUR = 14;     //下班旷工时间，小时

    
    private Integer id;             //考勤id
    private Integer userId;         //用户id
    private Integer year;           //年
    private Integer month;          //月
    private Integer day;            //日
    private Date clockInTime;       //上班打卡
    private Date clockOutTime;      //下班打卡
    private Integer state;          //0:考勤正常    1：迟到    2：早退    3：迟到并且早退    null：旷工     4:旷工

    public Attendance() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Date getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(Date clockInTime) {
        this.clockInTime = clockInTime;
    }

    public Date getClockOutTime() {
        return clockOutTime;
    }

    public void setClockOutTime(Date clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
