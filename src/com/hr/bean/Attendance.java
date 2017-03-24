package com.hr.bean;

import java.util.Date;

/**
 * ���ڱ�
 */
public class Attendance {
    public static final Integer NORMAL = 0;                     //��������
    public static final Integer ARRIVE_LATE = 1;                //�ٵ�
    public static final Integer LEAVE_EARLY = 2;                //����
    public static final Integer ARRIVE_LATE_LEAVE_EARLY= 3;     //�ٵ���������
    public static final Integer ABSENT = 4;                     //����
    public final static Integer CLOCK_IN_HOUR = 9;              //�ϰ�ʱ�䣬Сʱ
    public final static Integer CLOCK_OUT_HOUR = 17;            //�°�ʱ�䣬Сʱ
    public final static Integer CLOCK_IN_ABSENT_HOUR = 11;      //�ϰ����ʱ�䣬Сʱ
    public final static Integer CLOCK_OUT_ABSENT_HOUR = 14;     //�°����ʱ�䣬Сʱ

    
    private Integer id;             //����id
    private Integer userId;         //�û�id
    private Integer year;           //��
    private Integer month;          //��
    private Integer day;            //��
    private Date clockInTime;       //�ϰ��
    private Date clockOutTime;      //�°��
    private Integer state;          //0:��������    1���ٵ�    2������    3���ٵ���������    null������     4:����

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
