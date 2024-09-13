package org.booking.repository;

import org.booking.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HouseRepository extends JpaRepository<House, Long> {

    Optional<House> findByExternalHouseId(UUID externalHouseId);
}
