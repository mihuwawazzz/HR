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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller("userHandler")
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @Autowired
    @Qualifier("positionService")
    private PositionService positionService;

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    public String insertOrUpdate(User user) {
        user.setLevel(0);
        userService.insertOrUpdate(user);
        return "redirect:/login.jsp";
    }

    @RequestMapping(value = "/queryByEmail", method = RequestMethod.POST)
    @ResponseBody
    public User queryByEmail(String email) {
        return userService.queryByEmail(email);
    }

    @RequestMapping(value = "/queryByUser", method = RequestMethod.POST)
    public String queryByUser(User user, Map<String, Object> map, HttpSession session) {
        user = userService.queryByUser(user);
        if (user == null) {
            map.put("error", "!邮箱或者密码错误!");
            return "redirect:/login.jsp";
        } else {
            Integer level = user.getLevel();
            session.setAttribute("user", user);
            if (level == 2) {
                //管理员
                return "manager/manager";
            } else if (level == 1) {
                //普通员工
                return "employee/employee";
            } else {
                //游客
                return "visitor/visitor";
            }
        }
    }

    @RequestMapping(value = "/queryDepAndPosi", method = RequestMethod.GET)
    public String queryDepAndPosi(Map<String, Object> map) {
        List<Department> departments = departmentService.queryAllFetch();
        map.put("departments", departments);
        return "employee/employee-user";
    }


    @RequestMapping(value = "/queryByUsername", method = RequestMethod.GET)
    public String queryByUsername(String username,Map<String, Object> map){
        List<User> users = userService.queryByUsername(username);
        map.put("users",users);
        return "manager/manager-salary";
    }

}
