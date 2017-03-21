package com.hr.bean.multibean;

public class MyDay {
    private int year;
    private int month;
    private int day;
    private String userId;

    public MyDay() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MyDay{");
        sb.append("year=").append(year);
        sb.append(", month=").append(month);
        sb.append(", day=").append(day);
        sb.append(", userId=").append(userId);
        sb.append('}');
        return sb.toString();
    }
}
