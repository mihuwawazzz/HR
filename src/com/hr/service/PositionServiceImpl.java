package com.hr.service;

import com.hr.bean.Page;
import com.hr.bean.Position;
import com.hr.dao.interf.PositionDao;
import com.hr.service.interf.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("positionService")
public class PositionServiceImpl implements PositionService {
    @Autowired
    @Qualifier("positionDao")
    private PositionDao positionDao;

    @Override
    public List<Position> queryByDepartmentId(Integer departmentId) {
        return positionDao.queryByDepartmentId(departmentId);
    }

    @Override
    public Position queryById(Integer positionId) {
        return positionDao.queryById(positionId);
    }

    @Override
    public void deleteById(Integer id) {
        positionDao.deleteById(id);
    }

    @Override
    public List<Position> queryAll() {
        return positionDao.queryAll();
    }

    @Override
    public Position queryByName(String name) {
        return positionDao.queryByName(name);
    }

    @Override
    public void insertOrUpdate(Position position) {
        positionDao.insertOrUpdate(position);
    }

    @Override
    public List<Position> queryByPage(Page<Position> positionPage) {
        return positionDao.queryByPage(positionPage);
    }
}
