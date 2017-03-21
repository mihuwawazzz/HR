package com.hr.service;

import com.hr.bean.Department;
import com.hr.dao.interf.DepartmentDao;
import com.hr.service.interf.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    @Qualifier("departmentDao")
    private DepartmentDao departmentDao;

    @Override
    public List<Department> queryAll() {
        return departmentDao.queryAll();
    }

    @Override
    public void deleteById(Integer departmentId) {
        departmentDao.deleteById(departmentId);
    }

    @Override
    public void insertOrUpdate(Department department) {
        departmentDao.insertOrUpdate(department);
    }

    @Override
    public Department queryByName(String name) {
        return departmentDao.queryByName(name);
    }

    @Override
    public List<Department> queryAllFetch() {
        return departmentDao.queryAllFetch();
    }

    @Override
    public Department queryById(Integer departmentId) {
        return departmentDao.queryById(departmentId);
    }
}
