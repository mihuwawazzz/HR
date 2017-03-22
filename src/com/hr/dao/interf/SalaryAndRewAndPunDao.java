package com.hr.dao.interf;

import com.hr.bean.RewardAndPunishment;
import com.hr.bean.Salary;

import java.util.List;

public interface SalaryAndRewAndPunDao {
    void saveOrUpdateRewardAndPunishment(RewardAndPunishment rewardAndPunishment);

    List<RewardAndPunishment> queryByUserIdLastMonth(Integer userId);

    void insertOpUpdateSalary(Salary salary);

    List<Salary> querySalaryForMakeSalary();

    Salary queryLastSalaryByUserId(Integer userId);

    List<RewardAndPunishment> queryLastRewardAndPunishmentsByUserId(Integer userId);

    RewardAndPunishment queryByID(Integer id);

    List<RewardAndPunishment> queryRPByState();

    List<Salary> querySalariesByUserId(Integer userId);

    List<RewardAndPunishment> queryRPsByUseId(Integer userId);
}
