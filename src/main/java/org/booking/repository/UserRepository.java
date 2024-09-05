package org.booking.repository;


import org.booking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u LEFT JOIN FETCH u.houses")
    Optional<User> findByExternalUserId(UUID externalUserId);

}
