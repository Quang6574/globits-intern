package com.globits.demo.service;

import com.globits.demo.dto.UserCreateDTO;
import com.globits.demo.dto.UserViewDTO;

import java.util.List;

public interface UserService {
    UserViewDTO create(UserCreateDTO user);

    List<UserViewDTO> getAll();
    UserViewDTO get(int id);

    UserViewDTO save(int id, UserCreateDTO user);
    void delete(int id);
}
