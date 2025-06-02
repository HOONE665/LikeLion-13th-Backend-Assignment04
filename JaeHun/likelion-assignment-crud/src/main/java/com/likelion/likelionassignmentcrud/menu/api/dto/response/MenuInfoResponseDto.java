package com.likelion.likelionassignmentcrud.menu.api.dto.response;

import com.likelion.likelionassignmentcrud.menu.domain.Menu;
import lombok.Builder;

@Builder
public record MenuInfoResponseDto(
        String name,
        int price,
        String restaurantName
) {
    public static MenuInfoResponseDto from(Menu menu) {
        return MenuInfoResponseDto.builder()
                .name(menu.getName())
                .price(menu.getPrice())
                .restaurantName(menu.getRestaurant().getName())
                .build();
    }
}
