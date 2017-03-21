package com.hr.dao;

import com.hr.bean.Department;
import com.hr.bean.Page;
import com.hr.bean.Position;
import com.hr.dao.interf.PositionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("positionDao")
public class PositionDaoImpl implements PositionDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Override
    public List<Position> queryByDepartmentId(Integer departmentId) {
        Department department = new Department();
        department.setId(departmentId);
        return (List<Position>) hibernateTemplate.
                find("from Position where department = ?", department);
    }

    @Override
    public Position queryById(Integer positionId) {
        return hibernateTemplate.get(Position.class, positionId);
    }

    @Override
    public void deleteById(Integer id) {
        Position position = new Position();
        position.setId(id);
        hibernateTemplate.delete(position);
    }

    @Override
    public List<Position> queryAll() {
        return (List<Position>) hibernateTemplate.find("from Position");
    }

    @Override
    public Position queryByName(String name) {
        List<Position> positions = (List<Position>) hibernateTemplate.
                find("from Position where name = ?", name);
        if (positions.size() != 0) {
            return positions.get(0);
        }
        return null;
    }

    @Override
    public void insertOrUpdate(Position position) {
        hibernateTemplate.saveOrUpdate(position);
    }

    @Override
    public List<Position> queryByPage(Page<Position> positionPage) {
        int offset = (positionPage.getCurrentPage() - 1) * positionPage.getPageSize();
        return hibernateTemplate.getSessionFactory().getCurrentSession().createQuery("from Position ").
                setMaxResults(positionPage.getPageSize()).setFirstResult(offset).list();
    }

}
