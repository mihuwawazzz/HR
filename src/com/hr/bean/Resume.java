package com.hr.bean;

import java.util.Date;

/**
 * ������
 */
public class Resume {
    private Integer id;                    //����id
    private Integer userId;                //�û�id
    private String email;                  //����
    private String username;               //�û���
    private Date birthday;                 //��������
    private Integer gender;                //�Ա�    0��Ů    1����
    private Integer positionId;            //ӦƸְλ
    private String workExperience;         //��������
    private String introduction;           //���ҽ���
    private Integer state;                 //-1����δͶ��   0��δ��(��Ͷ��)    1������ͨ��    -2������δͨ��
    private Integer interviewState;        //0������δ����    1:�����ѽ���     2������ͨ��    -1������δͨ���򲻽���
    private String interviewNote;          //���Ե�ַ

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

