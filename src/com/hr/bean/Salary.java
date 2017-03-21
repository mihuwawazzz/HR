package com.hr.bean;

import java.util.Date;

public class Salary {
    private Integer id;                         //工资id
    private Integer userId;                     //用户id
    private Double basicSalary;                 //基本工资
    private Double overtimePay;                 //加班费
    private Double rewardAndPunishment;         //奖励惩罚
    private Double socialInsurance;             //社保
    private Double total;                       //总共
    private Date settlementDate;                //结算日期

    public Salary() {
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

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Double getOvertimePay() {
        return overtimePay;
    }

    public void setOvertimePay(Double overtimePay) {
        this.overtimePay = overtimePay;
    }

    public Double getRewardAndPunishment() {
        return rewardAndPunishment;
    }

    public void setRewardAndPunishment(Double rewardAndPunishment) {
        this.rewardAndPunishment = rewardAndPunishment;
    }

    public Double getSocialInsurance() {
        return socialInsurance;
    }

    public void setSocialInsurance(Double socialInsurance) {
        this.socialInsurance = socialInsurance;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Salary{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", basicSalary=").append(basicSalary);
        sb.append(", overtimePay=").append(overtimePay);
        sb.append(", rewardAndPunishment=").append(rewardAndPunishment);
        sb.append(", socialInsurance=").append(socialInsurance);
        sb.append(", total=").append(total);
        sb.append(", settlementDate=").append(settlementDate);
        sb.append('}');
        return sb.toString();
    }
}
