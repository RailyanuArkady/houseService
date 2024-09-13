package org.booking.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ResponseUserDto(UUID externalUserId,
                              String name,
                              List<UUID> houses,
                              LocalDateTime createdAt,
                              LocalDateTime updatedAt) {
}
