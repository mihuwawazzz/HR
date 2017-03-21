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
            rewardAndPunishment.setState(0);
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
                RP.setState(0);
                RP.setDate(date);
                if (attendance.getState() == null || attendance.getState() == 4) {
                    RP.setReason("旷工");
                    double money = Math.round(user.getBasicSalary() / days.size());
                    RP.setMoney(-money);
                    salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(RP);
                } else if (attendance.getState() == 1) {
                    RP.setReason("迟到");
                    RP.setMoney(-80.0);
                    salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(RP);
                } else if (attendance.getState() == 2) {
                    RP.setReason("早退");
                    RP.setMoney(-80.0);
                    salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(RP);
                } else if (attendance.getState() == 3) {
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
            Double rewardAndPunishment = 0.0;
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
            if (totalBefore > 3500) {
                if (totalBefore < 8000) {
                    socialInsurance = totalBefore * 0.08;
                } else {
                    socialInsurance = totalBefore * 0.15;
                }
            }
            salary.setSocialInsurance(-socialInsurance);
            salary.setTotal(totalBefore + socialInsurance);
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
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
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

    @RequestMapping("/updateRP/{userId}")
    public String updateRP(HttpServletRequest request, Map<String, Object> map) {
        Integer userId = Integer.parseInt(request.getParameter("userId"));
        Integer id = Integer.parseInt(request.getParameter("id"));
        String comment = request.getParameter("comment");
        RewardAndPunishment rewardAndPunishment = salaryAndRewAndPunService.queryByID(id);
        rewardAndPunishment.setComment(comment);
        rewardAndPunishment.setState(1);
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
        rewardAndPunishment.setState(2);
        RewardAndPunishment rewardAndPunishment2 = new RewardAndPunishment();
        rewardAndPunishment2.setState(2);
        rewardAndPunishment2.setReason("上月退回");
        rewardAndPunishment2.setMoney(-rewardAndPunishment.getMoney());
        rewardAndPunishment2.setComment(rewardAndPunishment.getReason()+rewardAndPunishment.getDate());
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
        rewardAndPunishment.setState(3);
        salaryAndRewAndPunService.saveOrUpdateRewardAndPunishment(rewardAndPunishment);
        queryRPByState(map);
        return "/manager/manager-RP";
    }


}
