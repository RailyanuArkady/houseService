package org.booking.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.booking.entity.House;
import org.booking.entity.User;
import org.booking.repository.HouseRepository;
import org.booking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    @Transactional
    public House createHouse(Long id, String address, BigDecimal area) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Could not find user with id: " + id));
        House house = House.builder()
                .user(user)
                .address(address)
                .area(area)
                .build();

        House savedHouse = houseRepository.save(house);
        return savedHouse;
    }

    public Optional<House> getHouseById(Long id) {
        return houseRepository.findById(id);
    }

    public House updateHouseById(Long id, House updatedHouse) {
        House house = houseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find house with id: " + id));
        house.setAddress(updatedHouse.getAddress());
        house.setArea(updatedHouse.getArea());
        return houseRepository.save(house);
    }

    public void deleteHouseById(Long id) {
        House houseToBeDeleted = houseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Could not find house with id: " + id));
        houseRepository.delete(houseToBeDeleted);
    }

    public List<House> getAllHouses() {
        List<House> houses = houseRepository.findAll();
        return houses;
    }
}
