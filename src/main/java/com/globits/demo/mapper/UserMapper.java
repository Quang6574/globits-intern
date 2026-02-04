package com.globits.demo.mapper;

import com.globits.demo.dto.UserCreateDTO;
import com.globits.demo.dto.UserViewDTO;
import com.globits.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // map nested Person fields into flat DTO
    @Mapping(source = "person.fullName", target = "fullName")
    @Mapping(source = "person.phoneNum", target = "phoneNum")
    @Mapping(source = "isActive", target = "isActive") // explicit
    UserViewDTO toViewDto(User user);

    List<UserViewDTO> toViewDtoList(List<User> users);


    @Mapping(target = "id", ignore = true)
    @Mapping(source = "isActive", target = "isActive") // explicit
    //@Mapping(target = "person", ignore = true)
    User toCreateDTO(UserCreateDTO dto);
}
