package com.likelion.likelionassignmentcrud.restaurant.api.dto.response;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record RestaurantListResponseDto(
        List<RestaurantInfoResponseDto> restaurants,
        int totalPages,
        long totalElements,
        int currentPage,
        int pageSize
) {
    public static RestaurantListResponseDto from(Page<RestaurantInfoResponseDto> restaurantPage) {
        return RestaurantListResponseDto.builder()
                .restaurants(restaurantPage.getContent())
                .totalPages(restaurantPage.getTotalPages())
                .totalElements(restaurantPage.getTotalElements())
                .currentPage(restaurantPage.getNumber())
                .pageSize(restaurantPage.getSize())
                .build();
    }
}