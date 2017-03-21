package com.hr.service;

import com.hr.bean.RewardAndPunishment;
import com.hr.bean.Salary;
import com.hr.dao.interf.SalaryAndRewAndPunDao;
import com.hr.service.interf.SalaryAndRewAndPunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("salaryAndRewAndPunService")
public class SalaryAndRewAndPunServiceImpl implements SalaryAndRewAndPunService{
    @Autowired
    @Qualifier("salaryAndRewAndPunDao")
    private SalaryAndRewAndPunDao salaryAndRewAndPunDao;

    @Override
    public void saveOrUpdateRewardAndPunishment(RewardAndPunishment rewardAndPunishment) {
        salaryAndRewAndPunDao.saveOrUpdateRewardAndPunishment(rewardAndPunishment);
    }

    @Override
    public List<RewardAndPunishment> queryByUserIdLastMonth(Integer userId) {
        return salaryAndRewAndPunDao.queryByUserIdLastMonth(userId);
    }

    @Override
    public void insertOpUpdateSalary(Salary salary) {
        salaryAndRewAndPunDao.insertOpUpdateSalary(salary);
    }

    @Override
    public List<Salary> querySalaryForMakeSalary() {
        return salaryAndRewAndPunDao.querySalaryForMakeSalary();
    }

    @Override
    public Salary queryLastSalaryByUserId(Integer userId) {
        return salaryAndRewAndPunDao.queryLastSalaryByUserId(userId);
    }

    @Override
    public List<RewardAndPunishment> queryLastRewardAndPunishmentsByUserId(Integer userId) {
        return salaryAndRewAndPunDao.queryLastRewardAndPunishmentsByUserId(userId);
    }

    @Override
    public RewardAndPunishment queryByID(Integer id) {
        return salaryAndRewAndPunDao.queryByID(id);
    }

    @Override
    public List<RewardAndPunishment> queryRPByState() {
        return salaryAndRewAndPunDao.queryRPByState();
    }
}
