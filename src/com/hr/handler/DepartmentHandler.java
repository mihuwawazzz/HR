package com.hr.handler;

import com.hr.bean.Department;
import com.hr.bean.Position;
import com.hr.bean.User;
import com.hr.service.interf.DepartmentService;
import com.hr.service.interf.PositionService;
import com.hr.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller("departmentHandler")
@RequestMapping("/department")
public class DepartmentHandler {
    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @Autowired
    @Qualifier("positionService")
    private PositionService positionService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = "/queryAll")
    public String queryAll(Map<String, Object> map){
        List<Department> departments = departmentService.queryAll();
        map.put("departments",departments);
        return "manager/manager-department";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteById")
    public String deleteById(String id){
        Integer departmentId = Integer.parseInt(id);
        List<Position> positions = positionService.queryByDepartmentId(departmentId);
        boolean flag = false;
        for(Position position:positions){
            List<User> users = userService.queryByPositionId(position.getId());
            if(users.size()!=0){
                flag = true;
                break;
            }
        }
        if(flag){
            return "1";             //不能删除，有员工
        }else{
            for(Position position:positions){
                positionService.deleteById(position.getId());
            }
            departmentService.deleteById(departmentId);
            return "0";                 //可以删除
        }
    }

    @RequestMapping(value = "/insertOrUpdate")
    public String insertOrUpdate(Department department,Map<String, Object> map){
        department.setCreateTime(new Date());
        Department department1 = departmentService.queryByName(department.getName());
        if(department1==null){
            departmentService.insertOrUpdate(department);
        }else{
            if(department.getId()!=null){
                map.put("errorUpdate","部门名重复！更新失败！");
            }else{
                map.put("errorInsert","部门名重复！更新失败！");
            }
        }
        queryAll(map);
        return "manager/manager-department";
    }

}
