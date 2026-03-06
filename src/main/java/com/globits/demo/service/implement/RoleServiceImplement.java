package com.globits.demo.service.implement;

import com.globits.demo.repository.RoleRepository;
import com.globits.demo.dto.RoleCreateDTO;
import com.globits.demo.dto.RoleViewDTO;
import com.globits.demo.mapper.RoleMapper;
import com.globits.demo.model.Role;
import com.globits.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImplement implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public RoleCreateDTO create(RoleCreateDTO roleDTO) {
        // dto -> entity
        Role entity = roleMapper.toEntity(roleDTO);
        // save entity
        Role saved = roleRepository.create(entity);
        // entity -> dto
        return roleMapper.toCreateDto(saved);
    }

    @Transactional(readOnly = true)
    @Override
    public List<RoleCreateDTO> getAll(int page, int pageSize) {
        List<Role> entities = roleRepository.getAll(page,  pageSize);
        // map to view DTOs (with persons list)
        return roleMapper.toCreateDtoList(entities);
    }
    @Transactional(readOnly = true)
    @Override
    public RoleViewDTO get(String role) {
        Role entity = roleRepository.get(role);
        if (entity == null) {
            return null;
        }
        return roleMapper.toViewDto(entity);
    }

    @Transactional
    @Override
    public RoleCreateDTO save(String role, RoleCreateDTO roleDTO) {
        Role entity = roleRepository.get(role);
        if (entity == null) return null;

        entity.setRole(roleDTO.getRole());//this is primary key
        entity.setDescription(roleDTO.getDescription());

        Role saved = roleRepository.save(entity);
        return roleMapper.toCreateDto(saved);
    }

    @Transactional
    @Override
    public void delete(String role) {
        roleRepository.delete(role);
    }
}
