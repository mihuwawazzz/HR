package com.hr.handler;

import com.hr.bean.Department;
import com.hr.bean.Position;
import com.hr.bean.Resume;
import com.hr.bean.User;
import com.hr.service.interf.DepartmentService;
import com.hr.service.interf.PositionService;
import com.hr.service.interf.ResumeService;
import com.hr.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/resume")
@Controller("resumeHandler")
public class ResumeHandler {
    @Autowired
    @Qualifier("resumeService")
    private ResumeService resumeService;

    @Autowired
    @Qualifier("departmentService")
    private DepartmentService departmentService;

    @Autowired
    @Qualifier("positionService")
    private PositionService positionService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping(value = "/insertResumeByUserId/{userId}", method = RequestMethod.GET)
    public String insertResumeByUserId(@PathVariable("userId") Integer userId, Map<String, Object> map) {
        Resume resume = resumeService.queryByUserId(userId);
        List<Department> departments = departmentService.queryAll();
        map.put("resume", resume);
        map.put("departments", departments);
        return "visitor/visitor-insertResume";
    }

    @RequestMapping(value = "/updateResumeByUserId/{userId}", method = RequestMethod.GET)
    public String updateResumeByUserId(@PathVariable("userId") Integer userId, Map<String, Object> map) {
        insertResumeByUserId(userId, map);
        return "visitor/visitor-updateResume";
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    public String insertOrUpdate(Resume resume) {
        resume.setState(-1);
        resumeService.insertOrUpdate(resume);
        return "visitor/visitor";
    }

    @RequestMapping("/updateState")
    public String updateState(HttpServletRequest request) {
        int state = Integer.parseInt(request.getParameter("state"));
        int id = Integer.parseInt(request.getParameter("id"));
        resumeService.updateStateOfResumeById(id, state);
        return "visitor/visitor";
    }

    @RequestMapping(value = "/queryResume/{userId}", method = RequestMethod.GET)
    public String queryResume(@PathVariable("userId") Integer userId, Map<String, Object> map) {
        Resume resume = resumeService.queryByUserId(userId);
        if (resume != null) {
            Position position = positionService.queryById(resume.getPositionId());
            map.put("position", position);
        }
        map.put("resume", resume);
        return "visitor/visitor-queryResume";
    }

    @RequestMapping(value = "/queryResumeByState", method = RequestMethod.GET)
    public String queryResumeByState(Map<String, Object> map) {
        List<Resume> resumes = resumeService.queryResumeBySate(0);
        List<Position> positions = positionService.queryAll();
        map.put("positions", positions);
        map.put("resumes", resumes);
        return "manager/manager-resume";
    }


    @RequestMapping(value = "/updateStateOfResumeById", method = RequestMethod.GET)
    public String updateStateOfResumeById(HttpServletRequest request, Map<String, Object> map) {
        int state = Integer.parseInt(request.getParameter("state"));
        int id = Integer.parseInt(request.getParameter("id"));
        resumeService.updateStateOfResumeById(id, state);
        queryResumeByState(map);
        return "manager/manager-resume";
    }

    @RequestMapping(value = "/updatePassResume", method = RequestMethod.POST)
    public String updatePassResume(Resume resume, Map<String, Object> map) {
//        请于2017-03-30上午9点，到浦东新区松涛路489号B座进行面试！
        Resume resume1 = resumeService.queryById(resume.getId());
        resume1.setState(1);
        resume1.setInterviewState(0);
        resume1.setInterviewNote(resume.getInterviewNote());
        resumeService.insertOrUpdate(resume1);
        queryResumeByState(map);
        return "manager/manager-resume";
    }

    @RequestMapping(value = "/queryStateOfResume/{userId}", method = RequestMethod.GET)
    public String queryInterviewOfResume(@PathVariable("userId") Integer userId, Map<String, Object> map) {
        Resume resume = resumeService.queryByUserId(userId);
        map.put("resume", resume);
        return "visitor/visitor-resultResume";
    }

    @ResponseBody
    @RequestMapping(value = "/queryResumeForInterview", method = RequestMethod.GET)
    public Resume queryResumeForInterview(Integer userId, Map<String, Object> map) {
        return resumeService.queryByUserId(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/updateInterviewState", method = RequestMethod.POST)
    public void updateInterviewState(Integer id, Integer state) {
        Resume resume = resumeService.queryById(id);
        resume.setInterviewState(state);
        resumeService.insertOrUpdate(resume);
    }

    @RequestMapping(value = "/queryResumeByInterviewState", method = RequestMethod.GET)
    public String queryResumeByInterviewState(Map<String, Object> map) {
        List<Resume> resumes = resumeService.queryResumeByInterviewState();
        map.put("resumes", resumes);
        return "manager/manager-interview";
    }

    @RequestMapping(value = "/passInterviewById", method = RequestMethod.GET)
    public String passInterviewById(HttpServletRequest request, Map<String, Object> map) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        double basicSalary = Double.parseDouble(request.getParameter("basicSalary"));
        Resume resume = resumeService.queryById(id);
        resume.setInterviewState(2);
        resumeService.insertOrUpdate(resume);
        User user = userService.queryById(resume.getUserId());
        user.setUsername(resume.getUsername());
        user.setLevel(1);
        user.setBirthday(resume.getBirthday());
        user.setPositionId(resume.getPositionId());
        user.setState(1);
        user.setGender(resume.getGender());
        user.setBasicSalary(basicSalary);
        userService.insertOrUpdate(user);
        queryResumeByInterviewState(map);
        return "manager/manager-interview";
    }

    @RequestMapping(value = "/unPassInterviewById", method = RequestMethod.GET)
    public String unPassInterviewById(HttpServletRequest request, Map<String, Object> map) {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Resume resume = resumeService.queryById(id);
        resume.setInterviewState(-1);
        queryResumeByInterviewState(map);
        return "manager/manager-interview";
    }
}
