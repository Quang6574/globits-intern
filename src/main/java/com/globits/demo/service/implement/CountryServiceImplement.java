package com.globits.demo.service.implement;

import com.globits.demo.dto.CountryDTO;
import com.globits.demo.repository.CountryRepository;
import com.globits.demo.mapper.CountryMapper;
import com.globits.demo.model.Country;

import com.globits.demo.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImplement implements CountryService {

    //Gọi country repository
    @Autowired
    private CountryRepository countryRepository;
    //gọi mapper để sử dụng dto cho create, read và update
    @Autowired
    private CountryMapper countryMapper;

    @Transactional
    @Override
    public CountryDTO create(CountryDTO countryDTO) {
        //chuyển đổi thông tin người dùng nhập (countryDTO) từ dto thành entity
        Country entity = countryMapper.toEntity(countryDTO);
        //sau đó, lưu entity vào database thông qua country repository (countryRepository)
        Country saved = countryRepository.create(entity);
        //trả về cho người dùng dữ liệu đã lưu dưới dạng dto
        return countryMapper.toDto(saved);
    }

    @Transactional
    @Override
    public List<CountryDTO> getAll(int page, int pageSize) {
        //tạo list entity từ database qua country repository (countryRepository)
        List<Country> entities = countryRepository.getAll(page, pageSize);
        //sau đó, chuyển đổi list entity thành list dto và trả về
        return countryMapper.toDtoList(entities);
    }

    @Transactional
    @Override
    public CountryDTO get(int id) {
        //sử dụng id của country
        // lấy entity từ database qua country repository (countryRepository)
        Country entity = countryRepository.get(id);
        //nếu không tìm thấy country có id tương ứng, trả về null
        if (entity == null) return null;

        //nếu tìm thấy, chuyển entity thành dto và trả về
        return countryMapper.toDto(entity);
    }

    @Transactional
    @Override
    public CountryDTO save(int id, CountryDTO countryDTO) {
        //lấy country entity dựa theo id bằng qua country repository (countryRepository)
        Country entity = countryRepository.get(id);
        //nếu không tìm thấy country có id tương ứng, trả về null
        if (entity == null) return null;

        //nếu tìm thấy, cập nhật các trường
        entity.setName(countryDTO.getName());
        entity.setCode(countryDTO.getCode());
        entity.setDescription(countryDTO.getDescription());

        //lưu entity country đã update về database
        Country saved = countryRepository.save(entity);
        //trả về nội dung đã lưu của entity dưới dạng dto
        return countryMapper.toDto(saved);
    }

    @Transactional
    @Override
    public void delete(int id) {
        countryRepository.delete(id);
    }

    @Transactional
    @Override
    public CountryDTO createOrUpdate(CountryDTO countryDTO) {
        Integer id = countryDTO.getId();
        if (id == null) {
            //chuyển đổi thông tin người dùng nhập (countryDTO) từ dto thành entity
            Country entity = countryMapper.toEntity(countryDTO);
            //sau đó, lưu entity vào database thông qua country repository (countryRepository)
            Country saved = countryRepository.create(entity);
            //trả về cho người dùng dữ liệu đã lưu dưới dạng dto
            return countryMapper.toDto(saved);

        } else if (id != null) {
            //lấy country entity dựa theo id bằng qua country repository (countryRepository)
            Country entity = countryRepository.get(id);
            //nếu không tìm thấy country có id tương ứng, trả về null
            if (entity == null) return null;

            //nếu tìm thấy, cập nhật các trường
            entity.setName(countryDTO.getName());
            entity.setCode(countryDTO.getCode());
            entity.setDescription(countryDTO.getDescription());

            //lưu entity country đã update về database
            Country saved = countryRepository.save(entity);
            //trả về nội dung đã lưu của entity dưới dạng dto
            return countryMapper.toDto(saved);
        }

        return null;
    }


}
