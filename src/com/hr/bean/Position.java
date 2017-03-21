package com.hr.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * 职位表
 */
public class Position {
    private Integer id;             //职位id
    private String name;            //职位名
    private Date createTime;        //创建时间
    private Department department;  //所属部门

    public Position() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonIgnore
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
