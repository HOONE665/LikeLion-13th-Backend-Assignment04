package com.likelion.likelionassignmentcrud.restaurant.api.dto.request;

public record RestaurantSaveRequestDto(
        String name,
        String location,
        String address
) {
}
