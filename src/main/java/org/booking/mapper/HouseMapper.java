package org.booking.mapper;

import org.booking.dto.request.RequestHouseDto;
import org.booking.dto.response.ResponseHouseDto;
import org.booking.entity.House;
import org.booking.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HouseMapper {

    @Mapping(target = "user", source = "user")
    @Mapping(target = "externalHouseId", expression = "java(UUID.randomUUID())")
    House fromRequestToHouse(RequestHouseDto requestHouseDto, User user);

    @Mapping(target = "externalUserId", source = "house.user.externalUserId")
    ResponseHouseDto toResponseHouseDto(House house);
}
