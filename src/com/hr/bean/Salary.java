package com.hr.bean;

import java.util.Date;

/**
 * 工资类
 */
public class Salary {
    public static final Double SOCIAL_INSURANCE_LEVEL1 = 3500.0;                //缴纳社保基数等级1
    public static final Double SOCIAL_INSURANCE_LEVEL2 = 8000.0;                //缴纳社保基数等级2
    public static final Double PROPORTION_OF_SOCIAL_INSURANCE_LEVEL1 = 0.08;    //缴纳社保基数等级1比例
    public static final Double PROPORTION_OF_SOCIAL_INSURANCE_LEVEL2 = 0.15;    //缴纳社保基数等级2比例

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

}
