package org.booking.controller;

import org.booking.dto.request.RequestUserDto;
import org.booking.dto.response.ResponseUserDto;
import org.booking.entity.User;
import org.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseUserDto addUser(@RequestBody RequestUserDto requestUserDto) {
        return userService.createUser(requestUserDto);
    }
}
