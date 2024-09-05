package org.booking.controller;

import lombok.RequiredArgsConstructor;
import org.booking.dto.request.RequestUserDto;
import org.booking.dto.response.ResponseUserDto;
import org.booking.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseUserDto addUser(@RequestBody RequestUserDto requestUserDto) {
        return userService.createUser(requestUserDto);
    }

    @GetMapping
    public ResponseUserDto getUser(@RequestParam("externalUserId") UUID externalUserId) {
        return userService.getUserByExternalUserId(externalUserId);
    }
}
