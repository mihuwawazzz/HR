package com.hr.bean;

import java.util.Date;

/**
 * �û���
 */
public class User {
    private Integer id;             //�û�id
    private String email;           //����
    private String username;        //�û���
    private String password;        //����
    private Integer level;          //�ȼ�    0���ο�    1��Ա��    2������Ա
    private Date birthday;          //��������
    private Integer positionId;     //ְλid
    private Integer gender;         //�Ա�    0��Ů    1����
    private Double basicSalary;     //��������
    private Integer state;          //״̬    0����ְ    1����ְ    2��������   3������

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", email='").append(email).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", level=").append(level);
        sb.append(", birthday=").append(birthday);
        sb.append(", positionId=").append(positionId);
        sb.append(", gender=").append(gender);
        sb.append(", state=").append(state);
        sb.append('}');
        return sb.toString();
    }
}
