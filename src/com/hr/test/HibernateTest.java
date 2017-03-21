package com.hr.test;

import com.hr.bean.Department;
import com.hr.bean.User;
import com.hr.dao.DepartmentDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:springApplicationContext.xml"})
public class HibernateTest {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Test
    public void updateDB() {
        System.out.println("更新数据库表结构");
    }

    @Test
    public void hh(){
        List<User> users =  (List<User>) sessionFactory.openSession().
                createQuery("from User where username=? and state=?")
                .setParameter(0,"www")
                .setParameter(1,1).list();
        System.out.println(users);
    }
}
