package com.hr.service.interf;

import com.hr.bean.User;

import java.util.List;

public interface UserService {
    void insertOrUpdate(User user);
    User queryByUser(User user);
    User queryByEmail(String email);

    List<User> queryByPositionId(Integer positionId);

    User queryById(Integer id);

    List<User> queryAllEmployees();

    List<User> queryByUsername(String username);

    List<User> queryByUsernameAll(String username);

    void deleteUser(Integer id);

    List<User> queryAll();
}
