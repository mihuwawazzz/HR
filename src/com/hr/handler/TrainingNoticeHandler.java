package com.hr.handler;

import com.hr.bean.TrainingNotice;
import com.hr.service.interf.TrainingNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller("trainingNoticeHandler")
@RequestMapping("trainingNotice")
public class TrainingNoticeHandler {
    @Autowired
    @Qualifier("trainingNoticeService")
    private TrainingNoticeService trainingNoticeService;

    @RequestMapping("/queryByUserIdFetch/{userId}")
    private String queryByUserIdFetch(@PathVariable("userId") Integer userId, Map<String, Object> map) {
        List<TrainingNotice> trainingNotices = trainingNoticeService.queryByUserIdFetch(userId);
        map.put("trainingNotices", trainingNotices);
        return "employee/employee-training";
    }

    @ResponseBody
    @RequestMapping("/queryByUserId")
    private List<TrainingNotice> queryByUserId(Integer userId) {
        return trainingNoticeService.queryByUserIdFetch(userId);
    }

    @ResponseBody
    @RequestMapping("/updateState")
    private void updateState(Integer id) {
        trainingNoticeService.updateState(id);
    }

}
