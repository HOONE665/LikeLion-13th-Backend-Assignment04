package com.likelion.likelionassignmentcrud.restaurant.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RestaurantUpdateRequestDto(

        @NotBlank(message = "비워둘 수 없습니다.")
        @Size(min = 1, max = 50, message = "50자 이하로 입력해주세요.")
        @NotNull(message = "필수로 입력해야합니다")
        String name,

        @NotBlank(message = "비워둘 수 없습니다.")
        String location,

        @NotBlank(message = "비워둘 수 없습니다.")
        String address
) {
}
