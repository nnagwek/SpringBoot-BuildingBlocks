package com.restServices.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.restServices.dtos.UserMsDTO;
import com.restServices.entities.User;

@Mapper(componentModel = "Spring")
public interface UserMapper {

	UserMapper mapper = Mappers.getMapper(UserMapper.class);

	// User To UserMsDto
	@Mappings({ @Mapping(source = "email", target = "emailaddress"), @Mapping(source = "role", target = "rolename") })
	UserMsDTO userToUserMsDto(User user);

	// List<User> to List<UserMsDto>
	List<UserMsDTO> usersToUsersDtos(List<User> users);

}

