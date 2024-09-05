package org.booking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.booking.dto.request.RequestUserDto;
import org.booking.dto.response.ResponseUserDto;
import org.booking.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User", description = "Контроллер для взаимодействия с сущностью пользователь")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Создание пользователя",
            responses = @ApiResponse(responseCode = "200", description = "Успешное создание пользователя",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseUserDto.class))}))
    @PostMapping
    public ResponseUserDto addUser(
            @Parameter(name = "RequestUserDto", description = "DTO для создания пользователя")
            @RequestBody RequestUserDto requestUserDto) {
        return userService.createUser(requestUserDto);
    }

    @Operation(summary = "Получение пользователя по внешнему ID",
            responses = @ApiResponse(responseCode = "200", description = "Успешное получение пользователя",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseUserDto.class))}))
    @GetMapping
    public List<ResponseUserDto> getAllUsers() {
        return userService.getAllUsers();
    }
}