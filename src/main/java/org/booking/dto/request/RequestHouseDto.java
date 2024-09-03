package org.booking.dto.request;

import java.math.BigDecimal;

public record RequestHouseDto(String address,
                              BigDecimal area) {
}
