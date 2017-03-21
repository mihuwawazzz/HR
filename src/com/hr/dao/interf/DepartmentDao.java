package com.hr.dao.interf;


import com.hr.bean.Department;

import java.util.List;

public interface DepartmentDao {
    List<Department> queryAll();

    void deleteById(Integer departmentId);

    void insertOrUpdate(Department department);

    Department queryByName(String name);

    List<Department> queryAllFetch();

    Department queryById(Integer departmentId);
}
