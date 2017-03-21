package com.hr.dao.interf;

import com.hr.bean.Resume;

import java.util.List;

public interface ResumeDao {
    Resume queryByUserId(Integer userId);

    void insertOrUpdate(Resume resume);

    List<Resume> queryResumeBySate(int state);

    void updateStateOfResumeById(int id, int state);

    Resume queryById(Integer id);

    List<Resume> queryResumeByInterviewState();

}
