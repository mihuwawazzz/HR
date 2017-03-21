package com.hr.dao;

import com.hr.bean.User;
import com.hr.dao.interf.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    @Qualifier("hibernateTemplate")
    private HibernateTemplate hibernateTemplate;

    @Override
    public void insertOrUpdate(User user) {
        hibernateTemplate.saveOrUpdate(user);
    }

    @Override
    public User queryByUser(User user) {
        List<User> users = (List<User>) hibernateTemplate.find
                ("from User where email = ? and password = ?", user.getEmail(), user.getPassword());
        if (users.size() != 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User queryByEmail(String email) {
        List<User> users = (List<User>) hibernateTemplate.find("from User where email = ?", email);
        if (users.size() != 0) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public List<User> queryByPositionId(Integer positionId) {
        return (List<User>) hibernateTemplate.find("from User where positionId=?", positionId);
    }

    @Override
    public User queryById(Integer id) {
        return hibernateTemplate.get(User.class, id);
    }

    @Override
    public List<User> queryAllEmployees() {
        return (List<User>) hibernateTemplate.find("from User where state =1");
    }

    @Override
    public List<User> queryByUsername(String username) {
        return (List<User>) hibernateTemplate
                .find("from User where username=? and state = 1", username);
    }

    @Override
    public List<User> queryByUsernameAll(String username) {
        return (List<User>) hibernateTemplate
                .find("from User where username=? and state in(0,1,2)", username);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = hibernateTemplate.get(User.class, id);
        hibernateTemplate.delete(user);
    }

    @Override
    public List<User> queryAll() {
        return (List<User>) hibernateTemplate
                .find("from User where state in(0,1,2)");
    }
}
