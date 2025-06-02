package com.likelion.likelionassignmentcrud.restaurant.application;

import com.likelion.likelionassignmentcrud.restaurant.api.dto.request.RestaurantSaveRequestDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.response.RestaurantInfoResponseDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.response.RestaurantListResponseDto;
import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import com.likelion.likelionassignmentcrud.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void restaurantSave(RestaurantSaveRequestDto restaurantSaveRequestDto) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantSaveRequestDto.name())
                .location(restaurantSaveRequestDto.location())
                .address(restaurantSaveRequestDto.address())
                .build();
        restaurantRepository.save(restaurant);
    }

    public RestaurantListResponseDto restaurantFindAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantInfoResponseDto> responseDtos = restaurants.stream()
                .map(RestaurantInfoResponseDto::from)
                .toList();
        return RestaurantListResponseDto.from(responseDtos);
    }

    public RestaurantInfoResponseDto restaurantFindOne(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(IllegalArgumentException::new);
        return RestaurantInfoResponseDto.from(restaurant);
    }
}
