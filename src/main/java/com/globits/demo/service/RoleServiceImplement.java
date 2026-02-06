package com.globits.demo.service;

import com.globits.demo.dao.RoleDAO;
import com.globits.demo.dto.RoleCreateDTO;
import com.globits.demo.dto.RoleViewDTO;
import com.globits.demo.mapper.RoleMapper;
import com.globits.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImplement implements RoleService {

    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public RoleCreateDTO create(RoleCreateDTO roleDTO) {
        // dto -> entity
        Role entity = roleMapper.toEntity(roleDTO);
        // save entity
        Role saved = roleDAO.create(entity);
        // entity -> dto
        return roleMapper.toCreateDto(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoleCreateDTO> getAll() {
        List<Role> entities = roleDAO.getAll();
        // map to view DTOs (with persons list)
        return roleMapper.toCreateDtoList(entities);
    }

    @Transactional(readOnly = true)
    @Override
    public RoleViewDTO get(String role) {
        Role entity = roleDAO.get(role);
        if (entity == null) {
            return null;
        }
        return roleMapper.toViewDto(entity);
    }

    @Transactional
    @Override
    public RoleCreateDTO save(String role, RoleCreateDTO roleDTO) {
        Role entity = roleDAO.get(role);
        if (entity == null) return null;

        entity.setRole(roleDTO.getRole());//this is primary key
        entity.setDescription(roleDTO.getDescription());

        Role saved = roleDAO.save(entity);
        return roleMapper.toCreateDto(saved);
    }

    @Transactional
    @Override
    public void delete(String role) {
        roleDAO.delete(role);
    }
}
