package com.hr.bean;

import java.util.Date;

/**
 * ������
 */
public class Salary {
    public static final Double SOCIAL_INSURANCE_LEVEL1 = 3500.0;                //�����籣�����ȼ�1
    public static final Double SOCIAL_INSURANCE_LEVEL2 = 8000.0;                //�����籣�����ȼ�2
    public static final Double PROPORTION_OF_SOCIAL_INSURANCE_LEVEL1 = 0.08;    //�����籣�����ȼ�1����
    public static final Double PROPORTION_OF_SOCIAL_INSURANCE_LEVEL2 = 0.15;    //�����籣�����ȼ�2����

    private Integer id;                         //����id
    private Integer userId;                     //�û�id
    private Double basicSalary;                 //��������
    private Double overtimePay;                 //�Ӱ��
    private Double rewardAndPunishment;         //�����ͷ�
    private Double socialInsurance;             //�籣
    private Double total;                       //�ܹ�
    private Date settlementDate;                //��������

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
