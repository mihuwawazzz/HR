package com.hr.handler;

import com.hr.bean.Department;
import com.hr.bean.Page;
import com.hr.bean.Position;
import com.hr.bean.User;
import com.hr.bean.multibean.DepartmentAndPosition;
import com.hr.service.interf.DepartmentService;
import com.hr.service.interf.PositionService;
import com.hr.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller("positionHandler")
@RequestMapping("/position")
public class PositionHandler {

    @Autowired
    @Qualifier("positionService")
    private PositionService positionService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;


    @RequestMapping(value = "/queryByDepartmentId", method = RequestMethod.GET)
    @ResponseBody
    public List<Position> queryByDepartmentId(String departmentId) {
        Integer departmentId1 = Integer.parseInt(departmentId);
        return positionService.queryByDepartmentId(departmentId1);
    }

    @RequestMapping(value = "/queryByPage", method = RequestMethod.GET)
    public String queryByPage(Integer currentPage, Map<String, Object> map) {
        if (currentPage == null) {
            currentPage = 1;
        }
        long maxRows = positionService.queryAll().size();
        Page<Position> positionPage = new Page<>(currentPage, null, 4, maxRows);
        positionPage.setCurrentPage(positionPage.getCurrentPage());
        List<Position> positions = positionService.queryByPage(positionPage);
        map.put("page", positionPage);
        map.put("positions", positions);
        List<Department> departments = departmentService.queryAll();
        map.put("departments", departments);
        return "manager/manager-position";
    }

    @RequestMapping(value = "/insertOrUpdate")
    public String insertOrUpdate(DepartmentAndPosition departmentAndPosition, Map<String, Object> map) {
        Position position = departmentAndPosition.getPosition();
        position.setCreateTime(new Date());
        position.setDepartment(departmentAndPosition.getDepartment());
        Position position1 = positionService.queryByName(position.getName());
        if (position1 == null) {
            positionService.insertOrUpdate(position);
        } else {
            if (position.getId() != null) {
                map.put("errorUpdate", "职位名重复！更新失败！");
            } else {
                map.put("errorInsert", "职位名重复！更新失败！");
            }
        }
        queryByPage(null, map);
        return "manager/manager-position";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById")
    public String deleteById(String id) {
        Integer positionId = Integer.parseInt(id);
        List<User> users = userService.queryByPositionId(positionId);
        if (users.size() != 0) {
            return "1";             //不能删除，有员工
        } else {
            positionService.deleteById(positionId);
            return "0";             //可以删除
        }
    }
}
