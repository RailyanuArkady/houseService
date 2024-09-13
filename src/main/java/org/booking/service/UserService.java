package org.booking.service;


import lombok.RequiredArgsConstructor;
import org.booking.dto.request.RequestUserDto;
import org.booking.dto.response.ResponseUserDto;
import org.booking.entity.House;
import org.booking.entity.User;
import org.booking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ResponseUserDto createUser(RequestUserDto requestUserDto) {

        User user = User.builder()
                .externalUserId(UUID.randomUUID())
                .name(requestUserDto.name())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        User savedUser = userRepository.save(user);

        return fromEntityToResponseUserDto(savedUser);
    }

    public ResponseUserDto getUserByExternalUserId(UUID externalUserId) {
        User userToBeShowed = userRepository.findByExternalUserId(externalUserId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find house with id: " + externalUserId));
        return fromEntityToResponseUserDto(userToBeShowed);
    }

    private ResponseUserDto fromEntityToResponseUserDto(User user) {
        return new ResponseUserDto(
                user.getExternalUserId(),
                user.getName(),
                user.getHouses() != null
                        ? user.getHouses().stream().map(House::getExternalHouseId).toList()
                        : Collections.emptyList(),
                user.getCreatedAt(),
                user.getUpdatedAt());
    }
}
