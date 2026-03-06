package com.globits.demo.service.implement;

import com.globits.demo.repository.UserRepository;
import com.globits.demo.dto.UserCreateDTO;
import com.globits.demo.model.User;
import com.globits.demo.mapper.UserMapper;
import com.globits.demo.dto.UserViewDTO;

import com.globits.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public UserViewDTO create(UserCreateDTO user) {
        User entity = userMapper.toCreateDTO(user);
        User saved = userRepository.create(entity);
        return userMapper.toViewDto(saved);
    }

    @Transactional
    @Override
    public List<UserViewDTO> getAll(int page, int pageSize) {
        List<User> userList = userRepository.getAll(page, pageSize);
        return userMapper.toViewDtoList(userList);
    }

    @Transactional
    @Override
    public UserViewDTO get(int id) {
        User user = userRepository.get(id);
        return userMapper.toViewDto(user);
    }

    @Transactional
    @Override
    public UserViewDTO save(int id, UserCreateDTO viewDTO) {
        User existing = userRepository.get(id);
        if (existing == null) return null;

        //update the info
        existing.setEmail(viewDTO.getEmail());
        existing.setIsActive(viewDTO.getIsActive());
        existing.setPassword(viewDTO.getPassword());

        User saved = userRepository.save(existing);
        return userMapper.toViewDto(saved);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.delete(id);
    }
}
