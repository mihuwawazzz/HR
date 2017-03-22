package com.hr.dao;

import com.hr.bean.RewardAndPunishment;
import com.hr.bean.Salary;
import com.hr.dao.interf.SalaryAndRewAndPunDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository("salaryAndRewAndPunDao")
public class SalaryAndRewAndPunDaoImpl implements SalaryAndRewAndPunDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Override
    public void saveOrUpdateRewardAndPunishment(RewardAndPunishment rewardAndPunishment) {
        hibernateTemplate.saveOrUpdate(rewardAndPunishment);
    }

    @Override
    public List<RewardAndPunishment> queryByUserIdLastMonth(Integer userId) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int month2 = calendar.get(Calendar.MONTH) + 1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date before = new Date();
        Date after = new Date();
        try {
            before = simpleDateFormat.parse(year + "-" + month + "-" + 1);
            after = simpleDateFormat.parse(year + "-" + month2 + "-" + 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (List<RewardAndPunishment>) hibernateTemplate.find
                ("from RewardAndPunishment where date>? and date<? and userId = ?", before, after, userId);
    }

    @Override
    public void insertOpUpdateSalary(Salary salary) {
        hibernateTemplate.saveOrUpdate(salary);
    }

    @Override
    public List<Salary> querySalaryForMakeSalary() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int month2 = calendar.get(Calendar.MONTH) + 2;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date before = new Date();
        Date after = new Date();
        try {
            before = simpleDateFormat.parse(year + "-" + month + "-" + 1);
            after = simpleDateFormat.parse(year + "-" + month2 + "-" + 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (List<Salary>) hibernateTemplate.find
                ("from Salary where settlementDate>? and settlementDate<?", before, after);
    }

    @Override
    public Salary queryLastSalaryByUserId(Integer userId) {
        List<Salary> salaries = (List<Salary>) hibernateTemplate
                .find("from Salary where userId = ? ORDER BY id DESC", userId);
        if (salaries.size() != 0) {
            return salaries.get(0);
        }
        return null;
    }

    @Override
    public List<RewardAndPunishment> queryLastRewardAndPunishmentsByUserId(Integer userId) {
        Salary salary = queryLastSalaryByUserId(userId);
        if (salary != null) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            calendar.setTime(salary.getSettlementDate());
            int month = calendar.get(Calendar.MONTH);
            int month1 = month + 1;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date before = new Date();
            Date after = new Date();
            try {
                before = simpleDateFormat.parse(year + "-" + month + "-" + 1);
                after = simpleDateFormat.parse(year + "-" + month1 + "-" + 1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return (List<RewardAndPunishment>) hibernateTemplate.find
                    ("from RewardAndPunishment where date>? and date<? and userId = ?", before, after, userId);
        }
        return new ArrayList<>();
    }

    @Override
    public RewardAndPunishment queryByID(Integer id) {
        return hibernateTemplate.get(RewardAndPunishment.class, id);
    }

    @Override
    public List<RewardAndPunishment> queryRPByState() {
        return (List<RewardAndPunishment>) hibernateTemplate.find("from RewardAndPunishment where state = 1");
    }

    @Override
    public List<Salary> querySalariesByUserId(Integer userId) {
        return (List<Salary>) hibernateTemplate.find("from Salary where userId=?", userId);
    }

    @Override
    public List<RewardAndPunishment> queryRPsByUseId(Integer userId) {
        return (List<RewardAndPunishment>) hibernateTemplate.find("from RewardAndPunishment where userId=?", userId);
    }
}
