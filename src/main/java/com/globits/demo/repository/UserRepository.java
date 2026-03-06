package com.globits.demo.repository;

import com.globits.demo.model.User;

import java.util.List;

public interface UserRepository {

    User create(User user);

    List<User> getAll(int page, int pageSize);
    User get(int id);

    User save(User user);
    void delete(int id);

}
