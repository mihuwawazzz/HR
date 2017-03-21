package com.hr.dao.interf;

import com.hr.bean.Page;
import com.hr.bean.Position;

import java.util.List;

public interface PositionDao {
    List<Position> queryByDepartmentId(Integer departmentId);

    Position queryById(Integer positionId);

    void deleteById(Integer id);

    List<Position> queryAll();

    Position queryByName(String name);

    void insertOrUpdate(Position position);

    List<Position> queryByPage(Page<Position> positionPage);
}
