package com.likelion.likelionassignmentcrud.menu.api.dto.request;

public record MenuSaveRequestDto(
        Long restaurantId,
        String name,
        int price
) {
}
