package com.hr.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * ��ѵ֪ͨ��
 */
public class TrainingNotice {
    public static final Integer UNCHECK = 0;        //δ��
    public static final Integer CHECK = 1;          //����
    public static final Integer UPDATE = 2;         //����
    public static final Integer CANCEL = 3;         //ȡ��

    private Integer id;                 //��ѵ֪ͨ��id
    private Integer userId;             //�û�id
    private Integer state;              //״̬��0��δ��       1������    2������        3��ȡ��
    private Training training;          //��ѵ

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
