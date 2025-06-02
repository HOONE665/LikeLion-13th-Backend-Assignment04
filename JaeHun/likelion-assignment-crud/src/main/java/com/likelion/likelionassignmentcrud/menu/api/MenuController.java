package com.likelion.likelionassignmentcrud.menu.api;

import com.likelion.likelionassignmentcrud.menu.api.dto.request.MenuSaveRequestDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuInfoResponseDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuListResponseDto;
import com.likelion.likelionassignmentcrud.menu.application.MenuService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> menuSave(@RequestBody MenuSaveRequestDto menuSaveRequestDto) {
        menuService.menuSave(menuSaveRequestDto);
        return new ResponseEntity<>("메뉴 저장!", HttpStatus.CREATED);
    }

    // 메뉴 전체 조회
    @GetMapping("/all")
    public ResponseEntity<MenuListResponseDto> menuFindAll() {
        MenuListResponseDto menuListResponseDto = menuService.menuFindAll();
        return new ResponseEntity<>(menuListResponseDto, HttpStatus.OK);
    }

    // 메뉴 ID로 단일 조회
    @GetMapping("/{menuId}")
    public ResponseEntity<MenuInfoResponseDto> menuFindOne(@PathVariable Long menuId) {
        MenuInfoResponseDto menuInfoResponseDto = menuService.menuFindOne(menuId);
        return new ResponseEntity<>(menuInfoResponseDto, HttpStatus.OK);
    }
}
