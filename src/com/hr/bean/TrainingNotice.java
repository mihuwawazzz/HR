package com.hr.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 培训通知类
 */
public class TrainingNotice {
    public static final Integer UNCHECK = 0;        //未阅
    public static final Integer CHECK = 1;          //已阅
    public static final Integer UPDATE = 2;         //更新
    public static final Integer CANCEL = 3;         //取消

    private Integer id;                 //培训通知的id
    private Integer userId;             //用户id
    private Integer state;              //状态：0：未阅       1：已阅    2：更新        3：取消
    private Training training;          //培训

    public TrainingNotice() {
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    @JsonIgnore
    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
