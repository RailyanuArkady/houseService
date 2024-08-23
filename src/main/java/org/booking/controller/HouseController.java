package org.booking.controller;

import lombok.RequiredArgsConstructor;
import org.booking.entity.House;
import org.booking.service.HouseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/houses")
public class HouseController {

    private final HouseService houseService;

    @PostMapping
    public House create(@RequestParam("user_id") Long id, @RequestParam("address") String address,
                        @RequestParam("square") Integer square) {
        return houseService.createHouse(id, address, square);
    }
}
