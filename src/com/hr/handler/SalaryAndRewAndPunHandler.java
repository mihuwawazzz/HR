package com.hr.handler;

import com.hr.bean.Attendance;
import com.hr.bean.RewardAndPunishment;
import com.hr.bean.User;
import com.hr.bean.multibean.RewardAndPunishments;
import com.hr.service.interf.SalaryAndRewAndPunService;
import com.hr.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequestMapping("/salaryAndRewAndPun")
@Controller
public class SalaryAndRewAndPunHandler {
    @Autowired
    @Qualifier("salaryAndRewAndPunService")
    private SalaryAndRewAndPunService salaryAndRewAndPunService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping("/query")
    public String query() {
        return "/manager/manager-salary";
    }

    @RequestMapping("/insertRewardAndPunishment")
    public String insertRewardAndPunishment(Integer userId, RewardAndPunishments rewardAndPunishments) {
        for (RewardAndPunishment rewardAndPunishment : rewardAndPunishments.getRewardAndPunishments()) {
            rewardAndPunishment.setUserId(userId);
            rewardAndPunishment.setDate(new Date());
            rewardAndPunishment.setMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
            rewardAndPunishment.setState(0);
            salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(rewardAndPunishment);
        }
        return "/manager/manager-salary";
    }

    @RequestMapping("/insertSalary")
    public String insertSalary() {
        List<User> users = userService.queryAllEmployees();
//        List<Attendance> attendances =
        return "/manager/manager-salary";
    }

}
