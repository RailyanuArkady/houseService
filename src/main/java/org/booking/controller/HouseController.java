package org.booking.controller;

import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/houses")
public class HouseController {

    private final HouseService houseService;

    @PostMapping("/create")
    public House create(@RequestParam("user_id") Long id, @RequestParam("address") String address,
                        @RequestParam("area") BigDecimal area) {
        return houseService.createHouse(id, address, area);
    }

    @PostMapping("/update")
    public House update(@RequestParam("house_id") Long id,
                        @RequestBody() House house) {
        return houseService.updateHouseById(id, house);
    }

    @GetMapping
    public List<House> getAll() {
        return houseService.getAllHouses();
    }
}
