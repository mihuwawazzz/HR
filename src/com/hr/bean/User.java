package com.hr.bean;

import java.util.Date;

/**
 * 用户类
 */
public class User {
    private Integer id;             //用户id
    private String email;           //邮箱
    private String username;        //用户名
    private String password;        //密码
    private Integer level;          //等级    0：游客    1：员工    2：管理员
    private Date birthday;          //出生日期
    private Integer positionId;     //职位id
    private Integer gender;         //性别    0：女    1：男
    private Double basicSalary;     //基本工资
    private Integer state;          //状态    0：离职    1：在职    2：试用期   3：退休

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
