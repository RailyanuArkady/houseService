package org.booking.service;


import org.booking.dto.request.RequestUserDto;
import org.booking.dto.response.ResponseUserDto;
import org.booking.entity.User;
import org.booking.mapper.UserMapper;
import org.booking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseUserDto createUser(RequestUserDto requestUserDto) {

        User savedUser = userRepository.save(userMapper.fromRequestToUser(requestUserDto));

        return userMapper.toResponseUserDto(userRepository.save(savedUser));
    }
}
