package com.hr.test;

import com.hr.bean.RewardAndPunishment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springApplicationContext.xml"})
public class HibernateTest {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Test
    public void updateDB() {
        System.out.println("更新数据库表结构");
    }

    @Test
    public void hh(){
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
        System.out.println("after = " + after);
        System.out.println(before);
        List<RewardAndPunishment> rewardAndPunishments = (List<RewardAndPunishment>) hibernateTemplate
                .find("from RewardAndPunishment where date>? and date<? and userId = ?", before, after,1);
        System.out.println(rewardAndPunishments);
    }
}
