package org.booking.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.booking.dto.request.RequestHouseDto;
import org.booking.dto.response.ResponseHouseDto;
import org.booking.entity.House;
import org.booking.service.HouseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houses")
@Tag(name = "House", description = "Контроллер для взаимодействия с сущностью дом")
public class HouseController {

    private final HouseService houseService;

    @Operation(summary = "Создание сущности дом",
            responses = @ApiResponse(responseCode = "200", description = "success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseHouseDto.class))}))
    @PostMapping("/create")
    public ResponseHouseDto createHouse(@Parameter(name = "RequestHouseDto", description = "DTO для создания дома")
                                        @RequestBody RequestHouseDto requestHouseDto,
                                        @Parameter(name = "externalUserId", description = "Внешний id хозяина дома")
                                        @RequestParam("externalUserId") UUID externalUserId) {
        return houseService.createHouse(requestHouseDto, externalUserId);
    }

    @Operation(summary = "Обновление сущности дома",
            responses = @ApiResponse(responseCode = "200", description = "Успешное обновление дома",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = House.class))}))
    @PostMapping("/update")
    public ResponseHouseDto updateHouseByExternalId(@Parameter(name = "externalHouseId",
            description = "внешний ID дома для обновления")
                                                    @RequestParam("externalHouseId") UUID externalHouseId,
                                                    @Parameter(name = "requestHouseDto",
                                                            description = "dto с обновлёнными данными для дома")
                                                    @RequestBody RequestHouseDto requestHouseDto) {
        return houseService.updateHouseByExternalHouseId(externalHouseId, requestHouseDto);
    }

    @Operation(summary = "Получение всех домов",
            responses = @ApiResponse(responseCode = "200", description = "Успешное получение всех домов",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseHouseDto.class))}))

    @GetMapping
    public List<ResponseHouseDto> getAllHousesFilteredByArea() {
        return houseService.getAllHousesFilteredByArea();
    }

    @DeleteMapping
    public boolean deleteHouseByExternalHouseId(
            @RequestParam("externalHouseId") UUID externalHouseId) {
        houseService.deleteHouseByExternalHouseId(externalHouseId);
        return true;
    }

}
