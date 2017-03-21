package com.hr.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TrainingNotice {
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TrainingNotice{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
