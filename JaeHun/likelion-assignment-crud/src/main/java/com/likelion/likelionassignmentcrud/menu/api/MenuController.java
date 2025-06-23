package com.likelion.likelionassignmentcrud.menu.api;

import com.likelion.likelionassignmentcrud.commom.error.SuccessCode;
import com.likelion.likelionassignmentcrud.commom.template.ApiResTemplate;
import com.likelion.likelionassignmentcrud.menu.api.dto.request.MenuSaveRequestDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.request.MenuUpdateRequestDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuInfoResponseDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuListResponseDto;
import com.likelion.likelionassignmentcrud.menu.application.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    // 메뉴 저장
    @PostMapping("/save")
    public ResponseEntity<ApiResTemplate<Void>> menuSave(@Valid @RequestBody MenuSaveRequestDto menuSaveRequestDto) {
        menuService.menuSave(menuSaveRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResTemplate.successWithNoContent(SuccessCode.MENU_SAVE_SUCCESS));
    }

    // 메뉴 전체 조회
    @GetMapping("/all")
    public ResponseEntity<ApiResTemplate<MenuListResponseDto>> menuFindAll(Pageable pageable) {
        MenuListResponseDto menuListResponseDto = menuService.menuFindAll(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, menuListResponseDto));
    }

    // 메뉴 ID로 단일 조회
    @GetMapping("/{menuId}")
    public ResponseEntity<ApiResTemplate<MenuInfoResponseDto>> menuFindOne(@PathVariable Long menuId) {
        MenuInfoResponseDto menuInfoResponseDto = menuService.menuFindOne(menuId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, menuInfoResponseDto));
    }

    // 메뉴 수정
    @PutMapping("/{menuId}")
    public ResponseEntity<ApiResTemplate<Void>> menuUpdate(@PathVariable Long menuId,
                                                           @Valid @RequestBody MenuUpdateRequestDto menuUpdateRequestDto) {
        menuService.menuUpdate(menuId, menuUpdateRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResTemplate.successWithNoContent(SuccessCode.MENU_UPDATE_SUCCESS));
    }

    // 메뉴 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity<ApiResTemplate<Void>> menuDelete(@PathVariable Long menuId) {
        menuService.menuDelete(menuId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResTemplate.successWithNoContent(SuccessCode.MENU_DELETE_SUCCESS));
    }
}