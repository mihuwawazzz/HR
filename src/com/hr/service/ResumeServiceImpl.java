package com.hr.service;

import com.hr.bean.Resume;
import com.hr.dao.interf.ResumeDao;
import com.hr.service.interf.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("resumeService")
@Transactional
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    @Qualifier("resumeDao")
    private ResumeDao resumeDao;

    @Override
    public Resume queryByUserId(Integer userId) {
        return resumeDao.queryByUserId(userId);
    }

    @Override
    public void insertOrUpdate(Resume resume) {
        resumeDao.insertOrUpdate(resume);
    }

    @Override
    public List<Resume> queryResumeBySate(int state) {
        return resumeDao.queryResumeBySate(state);
    }

    @Override
    public void updateStateOfResumeById(int id, int state) {
        resumeDao.updateStateOfResumeById(id, state);
    }

    @Override
    public Resume queryById(Integer id) {
        return resumeDao.queryById(id);
    }

    @Override
    public List<Resume> queryResumeByInterviewState() {
        return resumeDao.queryResumeByInterviewState();
    }
}
