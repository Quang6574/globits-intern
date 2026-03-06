package com.globits.demo.service.implement;

import com.globits.demo.mapper.CompanyMapper;
import com.globits.demo.mapper.DepartmentMapper;
import com.globits.demo.model.Company;

import com.globits.demo.repository.CompanyRepository;
import com.globits.demo.dto.CompanyDTO;

import com.globits.demo.repository.DepartmentRepository;
import com.globits.demo.dto.DepartmentDTO;

import com.globits.demo.model.Department;
import com.globits.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImplement implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentMapper departmentMapper;

    @Transactional
    @Override
    public List<CompanyDTO> getAll(int page, int pageSize) {
        List<Company> entities = companyRepository.getAll(page, pageSize);
        return companyMapper.toDtoList(entities);
    }

    @Transactional
    @Override
    public CompanyDTO get(String code) {
        Company entity = companyRepository.get(code);
        if (entity == null) return null;

        return companyMapper.toDto(entity);
    }

    @Transactional
    @Override
    public void delete(String code) {
        companyRepository.delete(code);
    }

    @Transactional
    @Override
    public List<DepartmentDTO> getAllDepartment(String code){
        List<Department> entities = departmentRepository.getByCompanyCode(code);
        if (entities == null) return null;

        return departmentMapper.toDtoList(entities);
    }

    @Transactional
    @Override
    public CompanyDTO createOrUpdate(CompanyDTO companyDTO) {
        String code = companyDTO.getCode();
        if (code == null) {
            // map DTO -> entity
            Company entity = companyMapper.toEntity(companyDTO);
            // save entity
            Company saved = companyRepository.create(entity);
            // map back to DTO
            return companyMapper.toDto(saved);
        }
        if (code != null) {
            //get company entity with id
            Company entity = companyRepository.get(code);
            //check if entity exists
            if (entity == null) return null;

            // update basic fields of Company
            entity.setCode(companyDTO.getCode());
            entity.setName(companyDTO.getName());
            entity.setAddress(companyDTO.getAddress());

            // save updated entity
            Company updated = companyRepository.save(entity);
            //convert to dto before return info to user
            return companyMapper.toDto(updated);
        }
        return null;
    }


}
