package org.booking.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.booking.dto.request.RequestHouseDto;
import org.booking.dto.response.ResponseHouseDto;
import org.booking.entity.House;
import org.booking.entity.User;
import org.booking.repository.HouseRepository;
import org.booking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HouseService {

    private final UserRepository userRepository;
    private final HouseRepository houseRepository;


    @Transactional
    public ResponseHouseDto createHouse(RequestHouseDto requestHouseDto, UUID externalUserId) {
        User user = userRepository.findByExternalUserId(externalUserId).orElseThrow(() ->
                new IllegalArgumentException("Could not find user with external id: " + externalUserId));
        House house = House.builder()
                .externalHouseId(UUID.randomUUID())
                .address(requestHouseDto.address())
                .area(requestHouseDto.area())
                .build();

        House addedHouse = user.addHouse(house);
        House savedHouse = houseRepository.save(addedHouse);

        return fromEntityToResponseHouseDto(savedHouse);
    }

    public ResponseHouseDto updateHouseByExternalHouseId(UUID externalHouseId,
                                                         RequestHouseDto requestHouseDto) {

        House houseToBeUpdated = houseRepository.findByExternalHouseId(externalHouseId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find house with id: " + externalHouseId));

        House updatedHouse = House.builder()
                .externalHouseId(externalHouseId)
                .address(requestHouseDto.address())
                .area(requestHouseDto.area())
                .build();

        houseToBeUpdated.setAddress(updatedHouse.getAddress());
        houseToBeUpdated.setArea(updatedHouse.getArea());
        houseRepository.save(updatedHouse);

        return fromEntityToResponseHouseDto(updatedHouse);
    }

    public boolean deleteHouseByExternalHouseId(UUID externalHouseId) {
        House houseToBeDeleted = houseRepository.findByExternalHouseId(externalHouseId)
                .orElseThrow(() -> new IllegalArgumentException("Could not find house with id: " + externalHouseId));
        houseRepository.delete(houseToBeDeleted);
        return true;
    }

    public List<ResponseHouseDto> getAllHousesFilteredByArea() {
        List<House> houses = houseRepository.findAll();
        return houses.stream()
                .sorted(Comparator.comparing(House::getArea))
                .map(this::fromEntityToResponseHouseDto)
                .toList();
    }

    private ResponseHouseDto fromEntityToResponseHouseDto(House house) {
        return new ResponseHouseDto(
                house.getExternalHouseId(),
                house.getUser().getExternalUserId(),
                house.getAddress(),
                house.getArea());
    }
}
