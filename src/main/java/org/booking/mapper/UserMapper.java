package org.booking.mapper;

import org.booking.dto.request.RequestUserDto;
import org.booking.dto.response.ResponseUserDto;
import org.booking.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "externalUserId", expression = "java(UUID.randomUUID())")
    @Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    User fromRequestToUser(RequestUserDto requestUserDto);

    ResponseUserDto toResponseUserDto(User User);
}
