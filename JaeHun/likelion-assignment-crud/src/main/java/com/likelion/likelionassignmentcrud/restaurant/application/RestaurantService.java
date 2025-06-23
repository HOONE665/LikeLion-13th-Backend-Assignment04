package com.likelion.likelionassignmentcrud.restaurant.application;

import com.likelion.likelionassignmentcrud.commom.error.ErrorCode;
import com.likelion.likelionassignmentcrud.commom.exception.BusinessException;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.request.RestaurantSaveRequestDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.request.RestaurantUpdateRequestDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.response.RestaurantInfoResponseDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.response.RestaurantListResponseDto;
import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import com.likelion.likelionassignmentcrud.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public RestaurantListResponseDto restaurantFindAll(Pageable pageable) {
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(pageable);
        Page<RestaurantInfoResponseDto> responseDtos = restaurantPage.map(RestaurantInfoResponseDto::from);
        return RestaurantListResponseDto.from(responseDtos);
    }

    public RestaurantInfoResponseDto restaurantFindOne(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION));
        return RestaurantInfoResponseDto.from(restaurant);

    }

    @Transactional
    public void restaurantUpdate(Long id, RestaurantUpdateRequestDto dto) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION));

        restaurant.update(dto.name(), dto.location(), dto.address());
    }

    @Transactional
    public void restaurantDelete(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION));

        restaurantRepository.delete(restaurant);
    }

}