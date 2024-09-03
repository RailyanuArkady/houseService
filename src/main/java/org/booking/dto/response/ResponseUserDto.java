package org.booking.dto.response;

import org.booking.entity.House;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ResponseUserDto(UUID externalUserId,
                              String name,
                              List<ResponseHouseDto> houses,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt) {
}
