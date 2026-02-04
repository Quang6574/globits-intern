package com.globits.demo.dao;

import com.globits.demo.model.User;

import java.util.List;

public interface UserDAO {

    User create(User user);

    List<User> getAll();
    User get(int id);

    User save(User user);
    void delete(int id);

}
