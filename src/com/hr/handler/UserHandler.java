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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    public String insertOrUpdate(User user, HttpSession session) {
        if(user == null){
            return "redirect:/register.jsp";
        }
        user.setLevel(0);
        userService.insertOrUpdate(user);
        session.setAttribute("user", user);
        return "redirect:/login.jsp";
    }

    @RequestMapping(value = "/queryByEmail", method = RequestMethod.POST)
    @ResponseBody
    public User queryByEmail(String email) {
        return userService.queryByEmail(email);
    }

    @RequestMapping(value = "/queryByUser")
    public String queryByUser(User user, Map<String, Object> map, HttpSession session) {
        user = userService.queryByUser(user);
        if (user == null) {
            map.put("error", "!邮箱或者密码错误!");
            return "forward:/login.jsp";
        } else {
            Integer level = user.getLevel();
            session.setAttribute("user", user);
            if (level.equals(User.MANAGER)) {
                //管理员
                return "manager/manager";
            } else if (level.equals(User.EMPLOYEE)) {
                //普通员工
                return "employee/employee";
            } else {
                //游客
                return "visitor/visitor";
            }
        }
    }

    @RequestMapping(value = "/queryDepAndPosi")
    public String queryDepAndPosi(Map<String, Object> map) {
        List<Department> departments = departmentService.queryAllFetch();
        map.put("departments", departments);
        return "employee/employee-user";
    }


    @RequestMapping(value = "/queryByUsername", method = RequestMethod.GET)
    public String queryByUsername(String username, Map<String, Object> map) {
        List<User> users = userService.queryByUsername(username);
        map.put("users", users);
        return "manager/manager-salary";
    }

    @RequestMapping(value = "/queryByUsernameAll", method = RequestMethod.GET)
    public String queryByUsernameAll(String username, Map<String, Object> map) {
        List<User> users = userService.queryByUsernameAll(username);
        map.put("users", users);
        return "manager/manager-salary";
    }

    @RequestMapping(value = "/queryByUsernameForUpdate", method = RequestMethod.GET)
    public String queryByUsernameForUpdate(String username, Map<String, Object> map) {
        List<User> users = new ArrayList<>();
        if (username.equals("")) {
            users = userService.queryAll();
        } else {
            users = userService.queryByUsernameAll(username);
        }
        queryDepAndPosi(map);
        map.put("users", users);
        return "manager/manager-user";
    }

    @RequestMapping(value = "/goToUser")
    public String goToUser() {
        return "manager/manager-user";
    }

    @RequestMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "manager/manager-user";
    }

    @RequestMapping(value = "/updateUser")
    public String updateUser(User user) {
        System.out.println(user);
        userService.insertOrUpdate(user);
        return "manager/manager-user";
    }

}
