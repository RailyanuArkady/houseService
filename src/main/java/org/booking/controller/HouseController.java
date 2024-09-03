package org.booking.controller;

import lombok.RequiredArgsConstructor;
import org.booking.dto.request.RequestHouseDto;
import org.booking.dto.response.ResponseHouseDto;
import org.booking.entity.House;
import org.booking.service.HouseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houses")
public class HouseController {

    private final HouseService houseService;

    @PostMapping("/create")
    public ResponseHouseDto create(@RequestBody RequestHouseDto requestHouseDto,
                                   @RequestParam("extUserId") UUID externalUserId) {
        return houseService.createHouse(requestHouseDto, externalUserId);
    }

    @PostMapping("/update")
    public House update(@RequestParam("houseId") Long id,
                        @RequestBody() House house) {
        return houseService.updateHouseById(id, house);
    }

    @GetMapping
    public List<ResponseHouseDto> getAll() {
        return houseService.getAllHouses();
    }
}
