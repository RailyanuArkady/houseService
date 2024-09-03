package org.booking.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.booking.dto.request.RequestHouseDto;
import org.booking.dto.response.ResponseHouseDto;
import org.booking.entity.House;
import org.booking.entity.User;
import org.booking.mapper.HouseMapper;
import org.booking.repository.HouseRepository;
import org.booking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final HouseMapper houseMapper;
    private final UserRepository userRepository;
    private final HouseRepository houseRepository;


    @Transactional
    public ResponseHouseDto createHouse(RequestHouseDto requestHouseDto, UUID externalUserId) {
        User user = userRepository.findByExternalUserId(externalUserId).orElseThrow(() ->
                new IllegalArgumentException("Could not find user with external id: " + externalUserId));

        House savedHouse = houseRepository.save(houseMapper.fromRequestToHouse(requestHouseDto, user));
        return houseMapper.toResponseHouseDto(savedHouse);
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

    public List<ResponseHouseDto> getAllHouses() {
        List<House> houses = houseRepository.findAll();
        return houses.stream()
                .map(houseMapper::toResponseHouseDto)
                .toList();
    }
}
