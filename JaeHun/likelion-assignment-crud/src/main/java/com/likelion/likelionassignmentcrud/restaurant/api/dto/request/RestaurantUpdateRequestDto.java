package com.likelion.likelionassignmentcrud.restaurant.api.dto.request;


import lombok.Builder;

@Builder
public record RestaurantUpdateRequestDto(

        String name,
        String location,
        String address
) {
}
