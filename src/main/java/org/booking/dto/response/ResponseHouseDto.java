package org.booking.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ResponseHouseDto(UUID externalHouseId,
                               UUID externalUserId,
                               String address,
                               BigDecimal area) {
}
