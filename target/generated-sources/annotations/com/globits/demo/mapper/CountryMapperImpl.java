package com.globits.demo.mapper;

import com.globits.demo.dto.CountryCreateDTO;
import com.globits.demo.model.Country;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-02T16:17:36+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CountryMapperImpl implements CountryMapper {

    @Override
    public CountryCreateDTO toDto(Country entity) {
        if ( entity == null ) {
            return null;
        }

        CountryCreateDTO countryCreateDTO = new CountryCreateDTO();

        countryCreateDTO.setName( entity.getName() );
        countryCreateDTO.setCode( entity.getCode() );
        countryCreateDTO.setDescription( entity.getDescription() );

        return countryCreateDTO;
    }

    @Override
    public Country toEntity(CountryCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Country country = new Country();

        country.setName( dto.getName() );
        country.setCode( dto.getCode() );
        country.setDescription( dto.getDescription() );

        return country;
    }

    @Override
    public List<CountryCreateDTO> toDtoList(List<Country> entities) {
        if ( entities == null ) {
            return null;
        }

        List<CountryCreateDTO> list = new ArrayList<CountryCreateDTO>( entities.size() );
        for ( Country country : entities ) {
            list.add( toDto( country ) );
        }

        return list;
    }
}
