package com.hr.bean;

import java.util.Date;

/**
 * 简历类
 */
public class Resume {
    public static final Integer GENDER_MALE = 1;                //男
    public static final Integer GENDER_FEMALE = 0;              //女

    public static final Integer RESUME_UNDELIVER = -1;          //简历还未投递
    public static final Integer RESUME_DELIVER = 0;             //简历已经投递
    public static final Integer RESUME_PASS = 1;                //简历通过
    public static final Integer RESUME_UNPASS = -2;             //简历未通过

    public static final Integer INTERVIEW_UNACCEPT = 0;         //面试未接受
    public static final Integer INTERVIEW_ACCEPT = 1;           //面试已接受
    public static final Integer INTERVIEW_PASS = 2;             //面试通过
    public static final Integer INTERVIEW_UNPASS = -1;          //面试未通过或者不接受


    private Integer id;                    //简历id
    private Integer userId;                //用户id
    private String email;                  //邮箱
    private String username;               //用户名
    private Date birthday;                 //出生日期
    private Integer gender;                //性别    0：女    1：男
    private Integer positionId;            //应聘职位
    private String workExperience;         //工作经历
    private String introduction;           //自我介绍
    private Integer state;                 //-1：还未投递    0：已投递    1：简历通过    -2：简历未通过
    private Integer interviewState;        //0：面试未接受    1:面试已接受     2：面试通过    -1：面试未通过或不接受
    private String interviewNote;          //面试地址

    public Resume() {
    }

    public Integer getInterviewState() {
        return interviewState;
    }

    public void setInterviewState(Integer interviewState) {
        this.interviewState = interviewState;
    }

    public String getInterviewNote() {
        return interviewNote;
    }

    public void setInterviewNote(String interviewNote) {
        this.interviewNote = interviewNote;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

}

