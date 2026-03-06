package com.globits.demo.service.implement;

import com.globits.demo.dto.DepartmentDTO;
import com.globits.demo.repository.CompanyRepository;
import com.globits.demo.repository.DepartmentRepository;
import com.globits.demo.dto.DepartmentCompanyDTO;
import com.globits.demo.dto.DepartmentParentDTO;
import com.globits.demo.mapper.DepartmentMapper;

import com.globits.demo.model.Department;
import com.globits.demo.model.Company;
import com.globits.demo.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentServiceImplement implements DepartmentService {
    //Gọi  repository
    @Autowired
    private DepartmentRepository departmentRepository;
    //gọi mapper để sử dụng dto cho create, read và update
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    @Override
    public List<DepartmentDTO> getAll(int page, int pageSize) {
        //tạo list entity từ database qua  repository
        List<Department> entities = departmentRepository.getAll(page, pageSize);
        //sau đó, chuyển đổi list entity thành list dto và trả về
        return departmentMapper.toDtoList(entities);
    }
    @Transactional
    @Override
    public DepartmentDTO get(int id) {
        //sử dụng id
        // lấy entity từ database qua  repository
        Department entity = departmentRepository.get(id);

        //nếu không tìm thấy department có id tương ứng, trả về null
        if (entity == null) return null;
        //nếu tìm thấy, chuyển entity thành dto và trả về
        return departmentMapper.toDto(entity);
    }

    @Transactional
    @Override
    public void delete(int id) {
        departmentRepository.delete(id);
    }

    @Transactional
    @Override
    public DepartmentDTO editCompany(int id, DepartmentCompanyDTO dto) {
        //lấy department entity dựa theo id qua  repository
        Department entity = departmentRepository.get(id);
        //nếu không tìm thấy country có id tương ứng, trả về null
        if (entity == null) return null;

        String companyCode = dto.getCode();
        if (companyCode == null || companyCode.isBlank()) {
            entity.setCompany(null);
            return departmentMapper.toDto(departmentRepository.save(entity));
        }

        //nếu tìm thấy, cập nhật các trường
        Company company = companyRepository.get(companyCode);
        if (company == null) return null;
        entity.setCompany(company);

        //lưu entity department đã update về database
        Department saved = departmentRepository.save(entity);
        //trả về nội dung đã lưu của entity dưới dạng dto
        return departmentMapper.toDto(saved);
    }

    @Transactional
    @Override
    public DepartmentDTO editParent(int id, DepartmentParentDTO dto) {
        //lấy department entity dựa theo id qua  repository
        Department entity = departmentRepository.get(id);
        //nếu không tìm thấy country có id tương ứng, trả về null
        if (entity == null) return null;

        Integer parentId = dto.getId();
        if (parentId == null) {
            entity.setParentDepartment(null);
            return departmentMapper.toDto(departmentRepository.save(entity));
        }

        //nếu tìm thấy, cập nhật các trường
        Department parent = departmentRepository.get(parentId);
        if (parent == null) return null;
        entity.setParentDepartment(parent);

        //lưu entity department đã update về database
        Department saved = departmentRepository.save(entity);
        //trả về nội dung đã lưu của entity dưới dạng dto
        return departmentMapper.toDto(saved);
    }

    @Transactional
    @Override
    public DepartmentDTO createOrUpdate(DepartmentDTO departmentDTO) {
        Integer id = departmentDTO.getId();
        if (id == null) {
            //chuyển đổi thông tin người dùng nhập từ dto thành entity
            Department entity = departmentMapper.toEntity(departmentDTO);
            //sau đó, lưu entity vào database thông qua  repository
            Department saved = departmentRepository.create(entity);
            //trả về cho người dùng dữ liệu đã lưu dưới dạng dto
            return departmentMapper.toDto(saved);

        }
        if (id != null) {
            //lấy department entity dựa theo id qua  repository
            Department entity = departmentRepository.get(id);
            //nếu không tìm thấy country có id tương ứng, trả về null
            if (entity == null) return null;

            //nếu tìm thấy, cập nhật các trường
            entity.setName(departmentDTO.getName());
            entity.setCode(departmentDTO.getCode());

            //lưu entity department đã update về database
            Department saved = departmentRepository.save(entity);
            //trả về nội dung đã lưu của entity dưới dạng dto
            return departmentMapper.toDto(saved);
        }
        return null;
    }

}
