package com.hr.dao;

import com.hr.bean.Department;
import com.hr.dao.interf.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Department> queryAll() {
        return (List<Department>) hibernateTemplate.find("from Department");
    }

    @Override
    public void deleteById(Integer departmentId) {
        Department department = new Department();
        department.setId(departmentId);
        hibernateTemplate.delete(department);
    }

    @Override
    public void insertOrUpdate(Department department) {
        hibernateTemplate.saveOrUpdate(department);
    }

    @Override
    public Department queryByName(String name) {
        List<Department> departments = (List<Department>) hibernateTemplate.
                find("from Department where name = ?", name);
        if (departments.size() != 0) {
            return departments.get(0);
        }
        return null;
    }

    @Override
    public List<Department> queryAllFetch() {
        return (List<Department>) hibernateTemplate
                .find("select distinct d from Department d inner join fetch d.positions");
    }

    @Override
    public Department queryById(Integer departmentId) {
        return hibernateTemplate.get(Department.class, departmentId);
    }
}
