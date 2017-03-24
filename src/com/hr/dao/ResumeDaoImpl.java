package com.hr.dao;

import com.hr.bean.Resume;
import com.hr.dao.interf.ResumeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("resumeDao")
public class ResumeDaoImpl implements ResumeDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Override
    public Resume queryByUserId(Integer userId) {
        List<Resume> resumes = (List<Resume>) hibernateTemplate.
                find("from Resume where userId = ?", userId);
        if (resumes.size() != 0) {
            return resumes.get(0);
        }
        return null;
    }

    @Override
    public void insertOrUpdate(Resume resume) {
        hibernateTemplate.saveOrUpdate(resume);
    }

    @Override
    public List<Resume> queryResumeBySate(int state) {
        return (List<Resume>) hibernateTemplate.find("from Resume where state = ?", state);
    }

    @Override
    public void updateStateOfResumeById(int id, int state) {
        Resume resume = hibernateTemplate.get(Resume.class, id);
        resume.setState(state);
        hibernateTemplate.saveOrUpdate(resume);
    }

    @Override
    public Resume queryById(Integer id) {
        return hibernateTemplate.get(Resume.class,id);
    }

    @Override
    public List<Resume> queryResumeByInterviewState() {
        return (List<Resume>) hibernateTemplate.find("from Resume where interviewState = ?",Resume.INTERVIEW_ACCEPT);
    }
}
