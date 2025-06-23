package com.likelion.likelionassignmentcrud.menu.api.dto.response;

import com.likelion.likelionassignmentcrud.menu.domain.Menu;
import lombok.Builder;

@Builder
public record MenuInfoResponseDto(
        Long menuId,
        String name,
        int price,
        Long restaurantId,
        String restaurantName
) {
    public static MenuInfoResponseDto from(Menu menu) {
        return MenuInfoResponseDto.builder()
                .menuId(menu.getMenuId())
                .name(menu.getName())
                .price(menu.getPrice())
                .restaurantId(menu.getRestaurant().getRestaurantId())
                .restaurantName(menu.getRestaurant().getName())
                .build();
    }
}