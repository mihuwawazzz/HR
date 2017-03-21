package com.hr.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TrainingNotice {
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
