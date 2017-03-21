package com.hr.handler;

import com.hr.bean.*;
import com.hr.service.interf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller("trainingHandler")
@RequestMapping("training")
public class TrainingHandler {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    @Qualifier("positionService")
    private PositionService positionService;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @Autowired
    @Qualifier("trainingService")
    private TrainingService trainingService;

    @Autowired
    @Qualifier("trainingNoticeService")
    private TrainingNoticeService trainingNoticeService;


    @RequestMapping("/queryAllBeforeCurrentTime")
    public String queryAllBeforeCurrentTime(Map<String, Object> map) {
        List<Training> trainings = trainingService.queryAllBeforeCurrentTime();
        map.put("trainings", trainings);
        return "manager/manager-training";
    }

    private Date converterBeginDate(String trainingDay, String trainingHour, String trainingMinute) {
        Date date = new Date();
        try {
            String[] day = trainingDay.split("/");
            int year = Integer.parseInt(day[2]);
            int month = Integer.parseInt(day[0]) - 1;
            int days = Integer.parseInt(day[1]);
            int hour = Integer.parseInt(trainingHour);
            int minute = Integer.parseInt(trainingMinute);
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, days, hour, minute, 0);
            date = calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    @RequestMapping("/updateDate")
    public String updateDate(HttpServletRequest request, Map<String, Object> map) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        String trainingDay = request.getParameter("trainingDay");
        String trainingHour = request.getParameter("trainingHour");
        String trainingMinute = request.getParameter("trainingMinute");
        Training training = trainingService.queryById(id);
        if (training != null) {
            training.setBeginDate(converterBeginDate(trainingDay, trainingHour, trainingMinute));
            trainingService.update(training);
            List<TrainingNotice> trainingNotices = trainingNoticeService.queryByTraining(training);
            for (TrainingNotice trainingNotice : trainingNotices) {
                trainingNotice.setState(2);
                trainingNoticeService.insertOrUpdate(trainingNotice);
            }
        }
        queryAllBeforeCurrentTime(map);
        return "manager/manager-training";
    }


    @RequestMapping("/updateState")
    public String updateState(HttpServletRequest request, Map<String, Object> map) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Training training = trainingService.queryById(id);
        if (training != null) {
            training.setState(-1);
            trainingService.update(training);
        }
        List<TrainingNotice> trainingNotices = trainingNoticeService.queryByTraining(training);
        for (TrainingNotice trainingNotice : trainingNotices) {
            trainingNotice.setState(3);
            trainingNoticeService.insertOrUpdate(trainingNotice);
        }
        queryAllBeforeCurrentTime(map);
        return "manager/manager-training";
    }

    @RequestMapping("/insertTrainingBefore")
    public String insertTrainingBefore(Map<String, Object> map) {
        List<Department> departments = departmentService.queryAll();
        map.put("departments", departments);
        return "manager/manager-training-insert";
    }

    @RequestMapping("/insertTraining")
    public String insertTraining(Training training, String[] trainingDepartments, HttpServletRequest request, Map<String, Object> map) {
        String trainingDay = request.getParameter("trainingDay");
        String trainingHour = request.getParameter("trainingHour");
        String trainingMinute = request.getParameter("trainingMinute");
        String trainingDes = "";
        training.setBeginDate(converterBeginDate(trainingDay, trainingHour, trainingMinute));
        training.setState(0);
        Integer trainingId = trainingService.insert(training);
        Training training1 = trainingService.queryById(trainingId);
        for (String de : trainingDepartments) {
            Integer departmentId = Integer.parseInt(de);
            Department department = departmentService.queryById(departmentId);
            trainingDes += department.getName() + "  ";
            List<Position> positions = positionService.queryByDepartmentId(departmentId);
            for (Position position : positions) {
                List<User> users = userService.queryByPositionId(position.getId());
                for (User user : users) {
                    TrainingNotice trainingNotice = new TrainingNotice();
                    trainingNotice.setUserId(user.getId());
                    trainingNotice.setState(0);
                    trainingNotice.setTraining(training1);
                    trainingNoticeService.insertOrUpdate(trainingNotice);
                }
            }
        }
        training.setTrainingDes(trainingDes);
        trainingService.update(training);
        queryAllBeforeCurrentTime(map);
        return "manager/manager-training";
    }

}
