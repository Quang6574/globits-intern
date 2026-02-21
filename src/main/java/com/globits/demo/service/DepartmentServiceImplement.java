package com.globits.demo.service;

import com.globits.demo.dao.CompanyDAO;
import com.globits.demo.dao.DepartmentDAO;
import com.globits.demo.dto.DepartmentCompanyDTO;
import com.globits.demo.dto.DepartmentCreateDTO;
import com.globits.demo.dto.DepartmentParentDTO;
import com.globits.demo.mapper.DepartmentMapper;

import com.globits.demo.model.Department;
import com.globits.demo.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImplement implements DepartmentService {
    //Gọi  repository
    @Autowired
    private DepartmentDAO departmentDAO;
    //gọi mapper để sử dụng dto cho create, read và update
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private CompanyDAO companyDAO;

    @Transactional
    @Override
    public DepartmentCreateDTO create(DepartmentCreateDTO department) {
        //chuyển đổi thông tin người dùng nhập từ dto thành entity
        Department entity = departmentMapper.toEntity(department);
        //sau đó, lưu entity vào database thông qua  repository
        Department saved = departmentDAO.create(entity);
        //trả về cho người dùng dữ liệu đã lưu dưới dạng dto
        return departmentMapper.toDto(saved);
    }

    @Transactional
    @Override
    public List<DepartmentCreateDTO> getAll() {
        //tạo list entity từ database qua  repository
        List<Department> entities = departmentDAO.getAll();
        //sau đó, chuyển đổi list entity thành list dto và trả về
        return departmentMapper.toDtoList(entities);
    }
    @Transactional
    @Override
    public DepartmentCreateDTO get(int id) {
        //sử dụng id
        // lấy entity từ database qua  repository
        Department entity = departmentDAO.get(id);

        //nếu không tìm thấy department có id tương ứng, trả về null
        if (entity == null) return null;
        //nếu tìm thấy, chuyển entity thành dto và trả về
        return departmentMapper.toDto(entity);
    }

    @Transactional
    @Override
    public DepartmentCreateDTO save(int id, DepartmentCreateDTO department) {
        //lấy department entity dựa theo id qua  repository
        Department entity = departmentDAO.get(id);
        //nếu không tìm thấy country có id tương ứng, trả về null
        if (entity == null) return null;

        //nếu tìm thấy, cập nhật các trường
        entity.setName(department.getName());
        entity.setCode(department.getCode());

        //lưu entity country đã update về database
        Department saved = departmentDAO.save(entity);
        //trả về nội dung đã lưu của entity dưới dạng dto
        return departmentMapper.toDto(saved);
    }

    @Transactional
    @Override
    public void delete(int id) {
        departmentDAO.delete(id);
    }

    @Transactional
    @Override
    public DepartmentCreateDTO editCompany(int id, DepartmentCompanyDTO dto) {
        //lấy department entity dựa theo id qua  repository
        Department entity = departmentDAO.get(id);
        //nếu không tìm thấy country có id tương ứng, trả về null
        if (entity == null) return null;

        String companyCode = dto.getCode();
        if (companyCode == null || companyCode.isBlank()) {
            entity.setCompany(null);
            return departmentMapper.toDto(departmentDAO.save(entity));
        }

        //nếu tìm thấy, cập nhật các trường
        Company company = companyDAO.get(companyCode);
        if (company == null) return null;
        entity.setCompany(company);

        //lưu entity department đã update về database
        Department saved = departmentDAO.save(entity);
        //trả về nội dung đã lưu của entity dưới dạng dto
        return departmentMapper.toDto(saved);
    }
    @Transactional
    @Override
    public DepartmentCreateDTO editParent(int id, DepartmentParentDTO dto) {
        //lấy department entity dựa theo id qua  repository
        Department entity = departmentDAO.get(id);
        //nếu không tìm thấy country có id tương ứng, trả về null
        if (entity == null) return null;

        Integer parentId = dto.getId();
        if (parentId == null) {
            entity.setParentDepartment(null);
            return departmentMapper.toDto(departmentDAO.save(entity));
        }

        //nếu tìm thấy, cập nhật các trường
        Department parent = departmentDAO.get(parentId);
        if (parent == null) return null;
        entity.setParentDepartment(parent);

        //lưu entity department đã update về database
        Department saved = departmentDAO.save(entity);
        //trả về nội dung đã lưu của entity dưới dạng dto
        return departmentMapper.toDto(saved);
    }

}
