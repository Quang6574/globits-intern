package com.globits.demo.service;

import com.globits.demo.mapper.CompanyMapper;
import com.globits.demo.mapper.DepartmentMapper;
import com.globits.demo.model.Company;

import com.globits.demo.dao.CompanyDAO;
import com.globits.demo.dto.CompanyDTO;

import com.globits.demo.dao.DepartmentDAO;
import com.globits.demo.dto.DepartmentCreateDTO;

import com.globits.demo.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImplement implements CompanyService {

    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private DepartmentDAO departmentDAO;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Transactional
    @Override
    public CompanyDTO create(CompanyDTO companyDTO) {
        // map DTO -> entity
        Company entity = companyMapper.toEntity(companyDTO);
        // save entity
        Company saved = companyDAO.create(entity);
        // map back to DTO
        return companyMapper.toDto(saved);
    }

    @Transactional
    @Override
    public List<CompanyDTO> getAll() {
        List<Company> entities = companyDAO.getAll();
        return companyMapper.toDtoList(entities);
    }
    @Transactional
    @Override
    public CompanyDTO get(String code) {
        Company entity = companyDAO.get(code);
        if (entity == null) return null;

        return companyMapper.toDto(entity);
    }

    @Transactional
    @Override
    public CompanyDTO save(String code, CompanyDTO companyDTO) {
        //get company entity with id
        Company entity = companyDAO.get(code);
        //check if entity exists
        if (entity == null) return null;

        // update basic fields of Company
        entity.setCode(companyDTO.getCode());
        entity.setName(companyDTO.getName());
        entity.setAddress(companyDTO.getAddress());

        // save updated entity
        Company updated = companyDAO.save(entity);
        //convert to dto before return info to user
        return companyMapper.toDto(updated);
    }

    @Transactional
    @Override
    public void delete(String code) {
        companyDAO.delete(code);
    }

    @Transactional
    @Override
    public List<DepartmentCreateDTO> getAllDepartment(String code){
        List<Department> entities = departmentDAO.getByCompanyCode(code);
        if (entities == null) return null;

        return departmentMapper.toDtoList(entities);

    }
}
