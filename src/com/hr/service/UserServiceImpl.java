package com.hr.service;

import com.hr.bean.User;
import com.hr.dao.interf.UserDao;
import com.hr.service.interf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public void insertOrUpdate(User user) {
        userDao.insertOrUpdate(user);
    }

    @Override
    public User queryByUser(User user) {
        return userDao.queryByUser(user);
    }

    @Override
    public User queryByEmail(String email) {
        return userDao.queryByEmail(email);
    }

    @Override
    public List<User> queryByPositionId(Integer positionId) {
        return userDao.queryByPositionId(positionId);
    }

    @Override
    public User queryById(Integer id) {
        return userDao.queryById(id);
    }

    @Override
    public List<User> queryAllEmployees() {
        return userDao.queryAllEmployees();
    }

    @Override
    public List<User> queryByUsername(String username) {
        return userDao.queryByUsername(username);
    }

}
