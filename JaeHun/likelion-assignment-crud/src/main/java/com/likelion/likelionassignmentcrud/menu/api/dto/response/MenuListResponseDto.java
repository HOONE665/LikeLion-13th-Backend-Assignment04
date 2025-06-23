package com.likelion.likelionassignmentcrud.menu.api.dto.response;

import lombok.Builder;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
public record MenuListResponseDto(
        List<MenuInfoResponseDto> menus,
        int totalPages,
        long totalElements,
        int currentPage,
        int pageSize
) {
    public static MenuListResponseDto from(Page<MenuInfoResponseDto> menuPage) {
        return MenuListResponseDto.builder()
                .menus(menuPage.getContent())
                .totalPages(menuPage.getTotalPages())
                .totalElements(menuPage.getTotalElements())
                .currentPage(menuPage.getNumber())
                .pageSize(menuPage.getSize())
                .build();
    }
}