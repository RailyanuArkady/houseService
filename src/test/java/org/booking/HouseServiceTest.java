package org.booking;

import org.booking.dto.request.RequestHouseDto;
import org.booking.dto.response.ResponseHouseDto;
import org.booking.entity.House;
import org.booking.entity.User;
import org.booking.repository.HouseRepository;
import org.booking.repository.UserRepository;
import org.booking.service.HouseService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HouseServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private HouseRepository houseRepository;

    @InjectMocks
    private HouseService houseService;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testCreatingHouse() {
        UUID externalUserId = UUID.randomUUID();
        UUID externalHouseId = UUID.randomUUID();

        User user = new User();
        user.setExternalUserId(externalUserId);

        RequestHouseDto requestHouseDto = new RequestHouseDto("Address", new BigDecimal("100.0"));

        House house = House.builder()
                .externalHouseId(externalHouseId)
                .address(requestHouseDto.address())
                .area(requestHouseDto.area())
                .user(user)
                .build();

        when(userRepository.findByExternalUserId(externalUserId)).thenReturn(Optional.of(user));
        when(houseRepository.save(any(House.class))).thenReturn(house);

        ResponseHouseDto responseHouseDto = houseService.createHouse(requestHouseDto, externalUserId);

        assertNotNull(responseHouseDto);
        assertEquals(externalHouseId, responseHouseDto.externalHouseId());
        assertEquals(requestHouseDto.address(), responseHouseDto.address());
        assertEquals(requestHouseDto.area(), responseHouseDto.area());
        assertEquals(externalUserId, responseHouseDto.externalUserId());
    }

    @Test
    public void testUpdatingHouse() {

        UUID externalUserId = UUID.randomUUID();
        UUID externalHouseId = UUID.randomUUID();

        User user = new User();
        user.setExternalUserId(externalUserId);

        House existingHouse = House.builder()
                .externalHouseId(externalHouseId)
                .user(user)
                .address("Address")
                .area(new BigDecimal("100.0"))
                .build();

        RequestHouseDto requestHouseDto = new RequestHouseDto("New Address", new BigDecimal("150.0"));

        when(houseRepository.findByExternalHouseId(externalHouseId)).thenReturn(Optional.of(existingHouse));
        when(houseRepository.save(any(House.class))).thenReturn(existingHouse);

        ResponseHouseDto responseHouseDto = houseService.updateHouseByExternalHouseId(externalHouseId, requestHouseDto);

        assertNotNull(responseHouseDto);
        assertEquals(externalUserId, responseHouseDto.externalUserId());
        assertEquals(requestHouseDto.address(), responseHouseDto.address());
        assertEquals(requestHouseDto.area(), responseHouseDto.area());
    }

    @Test
    public void testDeletingHouse() {

        UUID externalUserId = UUID.randomUUID();
        UUID externalHouseId = UUID.randomUUID();

        User user = new User();
        user.setExternalUserId(externalUserId);

        RequestHouseDto requestHouseDto = new RequestHouseDto("Address", new BigDecimal("100.0"));

        House houseToBeDeleted = House.builder()
                .externalHouseId(externalHouseId)
                .address(requestHouseDto.address())
                .area(requestHouseDto.area())
                .user(user)
                .build();

        when(houseRepository.findByExternalHouseId(externalHouseId)).thenReturn(Optional.of(houseToBeDeleted));
        when(houseRepository.save(any(House.class))).thenReturn(houseToBeDeleted);

        boolean result = houseService.deleteHouseByExternalHouseId(externalHouseId);

        assertTrue(result);
    }

    @Test
    public void testGettingAllHousesFilteredByArea() {

        UUID externalUserId = UUID.randomUUID();
        UUID externalHouseId = UUID.randomUUID();

        User user = new User();
        user.setExternalUserId(externalUserId);

        RequestHouseDto requestHouseDto1 = new RequestHouseDto("Address", new BigDecimal("100.0"));
        RequestHouseDto requestHouseDto2 = new RequestHouseDto("Address", new BigDecimal("50.0"));

        House house1 = House.builder()
                .externalHouseId(externalHouseId)
                .address(requestHouseDto1.address())
                .area(requestHouseDto1.area())
                .user(user)
                .build();

        House house2 = House.builder()
                .externalHouseId(externalHouseId)
                .address(requestHouseDto2.address())
                .area(requestHouseDto2.area())
                .user(user)
                .build();

        when(houseRepository.findAll()).thenReturn(List.of(house1, house2));

        List<ResponseHouseDto> responseHouseDto = houseService.getAllHousesFilteredByArea();

        assertEquals(2, responseHouseDto.size());
        assertEquals(house2.getExternalHouseId(), responseHouseDto.get(0).externalHouseId());
        assertEquals(house1.getExternalHouseId(), responseHouseDto.get(1).externalHouseId());
    }
}
