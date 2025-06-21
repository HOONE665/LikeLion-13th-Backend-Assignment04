package com.likelion.likelionassignmentcrud.menu.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record MenuUpdateRequestDto(
        @NotBlank(message = "비워둘 수 없습니다.")
        @Positive(message = "음수는 허용되지 않음")
        Long restaurantId,

        @NotBlank(message = "비워둘 수 없습니다.")
        @Max(value = 30, message = "최대 30자까지 입력 가능합니다")
        String name,

        @NotBlank(message = "비워둘 수 없습니다.")
        @Positive(message = "음수는 허용되지 않음")
        int price
){
    }


