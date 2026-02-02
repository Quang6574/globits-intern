package com.globits.demo.service;

import com.globits.demo.dao.CountryDAO;
import com.globits.demo.dto.CountryCreateDTO;
import com.globits.demo.mapper.CountryMapper;
import com.globits.demo.model.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImplement implements CountryService {

    //Gọi country repository
    @Autowired
    private CountryDAO countryDAO;
    //gọi mapper để sử dụng dto cho create, read và update
    @Autowired
    private CountryMapper countryMapper;

    @Transactional
    @Override
    public CountryCreateDTO create(CountryCreateDTO countryCreateDTO) {
        //chuyển đổi thông tin người dùng nhập (countryDTO) từ dto thành entity
        Country entity = countryMapper.toEntity(countryCreateDTO);
        //sau đó, lưu entity vào database thông qua country repository (countryDAO)
        Country saved = countryDAO.create(entity);
        //trả về cho người dùng dữ liệu đã lưu dưới dạng dto
        return countryMapper.toDto(saved);
    }

    @Transactional
    @Override
    public List<CountryCreateDTO> getAll() {
        //tạo list entity từ database qua country repository (countryDAO)
        List<Country> entities = countryDAO.getAll();
        //sau đó, chuyển đổi list entity thành list dto và trả về
        return countryMapper.toDtoList(entities);
    }

    @Transactional
    @Override
    public CountryCreateDTO get(int id) {
        //sử dụng id của country
        // lấy entity từ database qua country repository (countryDAO)
        Country entity = countryDAO.get(id);
        //nếu không tìm thấy country có id tương ứng, trả về null
        if (entity == null) return null;

        //nếu tìm thấy, chuyển entity thành dto và trả về
        return countryMapper.toDto(entity);
    }

    @Transactional
    @Override
    public CountryCreateDTO save(int id, CountryCreateDTO countryCreateDTO) {
        //lấy country entity dựa theo id bằng qua country repository (countryDAO)
        Country entity = countryDAO.get(id);
        //nếu không tìm thấy country có id tương ứng, trả về null
        if (entity == null) return null;

        //nếu tìm thấy, cập nhật các trường
        entity.setName(countryCreateDTO.getName());
        entity.setCode(countryCreateDTO.getCode());
        entity.setDescription(countryCreateDTO.getDescription());

        //lưu entity country đã update về database
        Country saved = countryDAO.save(entity);
        //trả về nội dung đã lưu của entity dưới dạng dto
        return countryMapper.toDto(saved);
    }

    @Transactional
    @Override
    public void delete(int id) {
        countryDAO.delete(id);
    }
}
