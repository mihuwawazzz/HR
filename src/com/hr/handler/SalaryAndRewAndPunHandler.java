package com.hr.handler;

import com.hr.bean.Attendance;
import com.hr.bean.RewardAndPunishment;
import com.hr.bean.Salary;
import com.hr.bean.User;
import com.hr.bean.multibean.RewardAndPunishments;
import com.hr.service.interf.AttendanceService;
import com.hr.service.interf.SalaryAndRewAndPunService;
import com.hr.service.interf.UserService;
import com.hr.util.AttendanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RequestMapping("/salaryAndRewAndPun")
@Controller
public class SalaryAndRewAndPunHandler {
    @Autowired
    @Qualifier("salaryAndRewAndPunService")
    private SalaryAndRewAndPunService salaryAndRewAndPunService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("attendanceService")
    private AttendanceService attendanceService;

    @RequestMapping("/query")
    public String query() {
        return "/manager/manager-salary";
    }

    @RequestMapping("/insertRewardAndPunishment")
    public String insertRewardAndPunishment(Integer userId, RewardAndPunishments rewardAndPunishments) {
        for (RewardAndPunishment rewardAndPunishment : rewardAndPunishments.getRewardAndPunishments()) {
            rewardAndPunishment.setUserId(userId);
            rewardAndPunishment.setDate(new Date());
            rewardAndPunishment.setState(RewardAndPunishment.TAKE_EFFECT);
            salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(rewardAndPunishment);
        }
        return "/manager/manager-salary";
    }

    @RequestMapping("/insertSalary")
    public String insertSalary() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        List<Integer> days = AttendanceUtil.noWeekendDays(year, month);
        List<User> users = userService.queryAllEmployees();
        List<Attendance> attendances;
        for (User user : users) {
            attendances = attendanceService.queryByMonth(user.getId(), year, month);
            for (Attendance attendance : attendances) {
                RewardAndPunishment RP = new RewardAndPunishment();
                Date date = new Date();
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-" + attendance.getDay());
                } catch (Exception ignored) {
                }
                RP.setUserId(user.getId());
                RP.setState(RewardAndPunishment.TAKE_EFFECT);
                RP.setDate(date);
                if (attendance.getState() == null || attendance.getState().equals(Attendance.ABSENT)) {
                    RP.setReason("旷工");
                    double money = Math.round(user.getBasicSalary() / days.size());
                    RP.setMoney(-money);
                    salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(RP);
                } else if (attendance.getState().equals(Attendance.ARRIVE_LATE)) {
                    RP.setReason("迟到");
                    RP.setMoney(-80.0);
                    salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(RP);
                } else if (attendance.getState().equals(Attendance.LEAVE_EARLY)) {
                    RP.setReason("早退");
                    RP.setMoney(-80.0);
                    salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(RP);
                } else if (attendance.getState().equals(Attendance.ARRIVE_LATE_LEAVE_EARLY)) {
                    RP.setReason("迟到并且早退");
                    RP.setMoney(-160.0);
                    salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(RP);
                }
            }
        }
        insertSalaryT(users);
        return "/manager/manager-salary";
    }

    private void insertSalaryT(List<User> users) {
        for (User user : users) {
            Salary salary = new Salary();
            double rewardAndPunishment = 0;
            List<RewardAndPunishment> rewardAndPunishments = salaryAndRewAndPunService.queryByUserIdLastMonth(user.getId());
            System.out.println(rewardAndPunishments);
            for (RewardAndPunishment r : rewardAndPunishments) {
                rewardAndPunishment += r.getMoney();
            }
            salary.setUserId(user.getId());
            salary.setBasicSalary(user.getBasicSalary());
            salary.setRewardAndPunishment(rewardAndPunishment);
            double totalBefore = user.getBasicSalary() + rewardAndPunishment;
            double socialInsurance = 0;
            if (totalBefore > Salary.SOCIAL_INSURANCE_LEVEL1) {
                if (totalBefore < Salary.SOCIAL_INSURANCE_LEVEL2) {
                    socialInsurance = totalBefore * Salary.PROPORTION_OF_SOCIAL_INSURANCE_LEVEL1;
                } else {
                    socialInsurance = totalBefore * Salary.PROPORTION_OF_SOCIAL_INSURANCE_LEVEL2;
                }
            }
            salary.setSocialInsurance(-socialInsurance);
            if (totalBefore < 0) {
                totalBefore = 0;
            }
            salary.setTotal(totalBefore - socialInsurance);
            salary.setSettlementDate(new Date());
            salaryAndRewAndPunService.insertOpUpdateSalary(salary);
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 2;
        List<Integer> days = AttendanceUtil.noWeekendDays(year, month);
        for (User user : users) {
            for (Integer i : days) {
                Attendance attendance = new Attendance();
                attendance.setUserId(user.getId());
                attendance.setYear(year);
                attendance.setMonth(month);
                attendance.setDay(i);
                attendanceService.insertOrUpdateAttendance(attendance);
            }
        }
    }

    @ResponseBody
    @RequestMapping("/querySalaryForMakeSalary")
    public List<Salary> querySalaryForMakeSalary() {
        return salaryAndRewAndPunService.querySalaryForMakeSalary();
    }

    @RequestMapping("/queryLastSalaryAndRPByUserId/{userId}")
    public String queryLastSalaryAndRPByUserId(@PathVariable("userId") Integer userId, Map<String, Object> map) {
        Salary salary = salaryAndRewAndPunService.queryLastSalaryByUserId(userId);
        List<RewardAndPunishment> rewardAndPunishments = salaryAndRewAndPunService.queryLastRewardAndPunishmentsByUserId(userId);
        map.put("rewardAndPunishments", rewardAndPunishments);
        map.put("salary", salary);
        return "/employee/employee-salary";
    }

    @RequestMapping("/updateRP")
    public String updateRP(HttpServletRequest request, Map<String, Object> map) {
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Integer id = Integer.parseInt(request.getParameter("id"));
        String comment = request.getParameter("comment");
        RewardAndPunishment rewardAndPunishment = salaryAndRewAndPunService.queryByID(id);
        rewardAndPunishment.setComment(comment);
        rewardAndPunishment.setState(RewardAndPunishment.COMPLIANT);
        salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(rewardAndPunishment);
        queryLastSalaryAndRPByUserId(userId, map);
        return "/employee/employee-salary";
    }


    @RequestMapping("/queryRPByState")
    public String queryRPByState(Map<String, Object> map) {
        List<User> users = userService.queryAllEmployees();
        map.put("users", users);
        List<RewardAndPunishment> rewardAndPunishments = salaryAndRewAndPunService.queryRPByState();
        map.put("rewardAndPunishments", rewardAndPunishments);
        return "/manager/manager-RP";
    }

    @RequestMapping("/backRP/{id}")
    public String backRP(@PathVariable("id") Integer id, Map<String, Object> map) {
        RewardAndPunishment rewardAndPunishment = salaryAndRewAndPunService.queryByID(id);
        rewardAndPunishment.setState(RewardAndPunishment.TURN_BACK);
        RewardAndPunishment rewardAndPunishment2 = new RewardAndPunishment();
        rewardAndPunishment2.setState(RewardAndPunishment.TURN_BACK);
        rewardAndPunishment2.setReason("上月退回");
        rewardAndPunishment2.setMoney(-rewardAndPunishment.getMoney());
        rewardAndPunishment2.setComment(rewardAndPunishment.getDate() + ":" + rewardAndPunishment.getReason());
        rewardAndPunishment2.setDate(new Date());
        rewardAndPunishment2.setUserId(rewardAndPunishment.getUserId());
        salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(rewardAndPunishment);
        salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(rewardAndPunishment2);
        queryRPByState(map);
        return "/manager/manager-RP";
    }

    @RequestMapping("/unBackRP/{id}")
    public String unBackRP(@PathVariable("id") Integer id, Map<String, Object> map) {
        RewardAndPunishment rewardAndPunishment = salaryAndRewAndPunService.queryByID(id);
        rewardAndPunishment.setState(RewardAndPunishment.UNTURN_BACK);
        salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(rewardAndPunishment);
        queryRPByState(map);
        return "/manager/manager-RP";
    }

    @RequestMapping("/queryAllEmployees")
    public String queryAllEmployees(Map<String, Object> map) {
        List<User> users = userService.queryAllEmployees();
        map.put("users", users);
        return "/manager/manager-salaryAndRP";
    }

    @RequestMapping("/querySalariesByUserId")
    public String querySalariesByUserId(Integer userId, Map<String, Object> map) {
        List<Salary> salaries = salaryAndRewAndPunService.querySalariesByUserId(userId);
        map.put("salaries", salaries);
        queryAllEmployees(map);
        return "/manager/manager-salaryAndRP";
    }

    @RequestMapping("/queryRPsByUserId")
    public String queryRPsByUserId(Integer userId, Map<String, Object> map) {
        List<RewardAndPunishment> rewardAndPunishments = salaryAndRewAndPunService.queryRPsByUseId(userId);
        map.put("rewardAndPunishments", rewardAndPunishments);
        queryAllEmployees(map);
        return "/manager/manager-salaryAndRP";
    }

}
