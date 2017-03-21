package com.hr.bean;

import java.util.Date;

public class RewardAndPunishment {
    private Integer id;             //奖惩id
    private Integer userId;         //用户id
    private String reason;          //原因
    private Date date;              //时间
    private String comment;         //备注
    private Double money;           //金额
    private Integer state;          //状态：0：生效    1：投诉    2：退回   3：不退回

    public RewardAndPunishment() {
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RewardAndPunishment{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", reason='").append(reason).append('\'');
        sb.append(", date=").append(date);
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", money=").append(money);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
