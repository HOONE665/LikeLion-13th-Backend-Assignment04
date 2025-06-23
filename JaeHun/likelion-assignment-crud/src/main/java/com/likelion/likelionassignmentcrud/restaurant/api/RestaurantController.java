package com.likelion.likelionassignmentcrud.restaurant.api;

import com.likelion.likelionassignmentcrud.commom.error.SuccessCode;
import com.likelion.likelionassignmentcrud.commom.template.ApiResTemplate;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.request.RestaurantSaveRequestDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.request.RestaurantUpdateRequestDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.response.RestaurantInfoResponseDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.response.RestaurantListResponseDto;
import com.likelion.likelionassignmentcrud.restaurant.application.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 식당 저장
    @PostMapping("/save")
    public ResponseEntity<ApiResTemplate<Void>> restaurantSave(@Valid @RequestBody RestaurantSaveRequestDto restaurantSaveRequestDto) {
        restaurantService.restaurantSave(restaurantSaveRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResTemplate.successWithNoContent(SuccessCode.RESTAURANT_SAVE_SUCCESS));
    }

    // 식당 전체 조회
    @GetMapping("/all")
    public ResponseEntity<ApiResTemplate<RestaurantListResponseDto>> restaurantFindAll(Pageable pageable) {
        RestaurantListResponseDto restaurantListResponseDto = restaurantService.restaurantFindAll(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, restaurantListResponseDto));
    }

    // 식당 ID로 단일 조회
    @GetMapping("/{restaurantId}")
    public ResponseEntity<ApiResTemplate<RestaurantInfoResponseDto>> restaurantFindOne(@PathVariable Long restaurantId) {
        RestaurantInfoResponseDto restaurantInfoResponseDto = restaurantService.restaurantFindOne(restaurantId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, restaurantInfoResponseDto));
    }


    // 식당 수정
    @PutMapping("/{restaurantId}")
    public ResponseEntity<ApiResTemplate<Void>> restaurantUpdate(@PathVariable Long restaurantId,
                                                                 @Valid @RequestBody RestaurantUpdateRequestDto restaurantUpdateRequestDto) {
        restaurantService.restaurantUpdate(restaurantId, restaurantUpdateRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResTemplate.successWithNoContent(SuccessCode.RESTAURANT_UPDATE_SUCCESS));
    }

    // 식당 삭제
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<ApiResTemplate<Void>> restaurantDelete(@PathVariable Long restaurantId) {
        restaurantService.restaurantDelete(restaurantId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResTemplate.successWithNoContent(SuccessCode.RESTAURANT_DELETE_SUCCESS));
    }
}