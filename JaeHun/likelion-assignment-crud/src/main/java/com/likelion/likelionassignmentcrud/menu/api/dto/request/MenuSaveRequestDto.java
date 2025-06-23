package com.likelion.likelionassignmentcrud.menu.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record MenuSaveRequestDto(
        @NotNull(message = "식당 ID는 필수입니다.")
        @Positive(message = "식당 ID는 양수여야 합니다.")
        Long restaurantId,

        @NotBlank(message = "메뉴 이름은 비워둘 수 없습니다.")
        @Size(min = 1, max = 30, message = "메뉴 이름은 1자 이상 30자 이하로 입력 가능합니다.")
        String name,

        @NotNull(message = "메뉴 가격은 필수입니다.")
        @Positive(message = "메뉴 가격은 양수여야 합니다.")
        @Min(value = 100, message = "메뉴 가격은 최소 100원 이상이어야 합니다.")
        @Max(value = 1000000, message = "메뉴 가격은 1,000,000원을 초과할 수 없습니다.")
        int price
) {
}