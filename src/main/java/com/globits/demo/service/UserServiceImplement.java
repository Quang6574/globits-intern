package com.globits.demo.service;

import com.globits.demo.dao.UserDAO;
import com.globits.demo.dto.UserCreateDTO;
import com.globits.demo.model.User;
import com.globits.demo.mapper.UserMapper;
import com.globits.demo.dto.UserViewDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public UserViewDTO create(UserCreateDTO user) {
        User entity = userMapper.toCreateDTO(user);
        User saved = userDAO.create(entity);
        return userMapper.toViewDto(saved);
    }

    @Transactional
    @Override
    public List<UserViewDTO> getAll() {
        List<User> userList = userDAO.getAll();
        return userMapper.toViewDtoList(userList);
    }

    @Transactional
    @Override
    public UserViewDTO get(int id) {
        User user = userDAO.get(id);
        return userMapper.toViewDto(user);
    }

    @Transactional
    @Override
    public UserViewDTO save(int id, UserCreateDTO viewDTO) {
        User existing = userDAO.get(id);
        if (existing == null) return null;

        //update the info
        existing.setEmail(viewDTO.getEmail());
        existing.setIsActive(viewDTO.getIsActive());
        existing.setPassword(viewDTO.getPassword());

        User saved = userDAO.save(existing);
        return userMapper.toViewDto(saved);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }
}
