package com.likelion.likelionassignmentcrud.menu.application;

import com.likelion.likelionassignmentcrud.menu.api.dto.request.MenuSaveRequestDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuInfoResponseDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuListResponseDto;
import com.likelion.likelionassignmentcrud.menu.domain.Menu;
import com.likelion.likelionassignmentcrud.menu.domain.repository.MenuRepository;
import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import com.likelion.likelionassignmentcrud.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    // 메뉴 저장
    @Transactional
    public void menuSave(MenuSaveRequestDto menuSaveRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(menuSaveRequestDto.restaurantId())
                .orElseThrow(IllegalArgumentException::new);

        Menu menu = Menu.builder()
                .name(menuSaveRequestDto.name())
                .price(menuSaveRequestDto.price())
                .restaurant(restaurant)
                .build();

        menuRepository.save(menu);
    }

    // 특정 식당의 메뉴 목록 조회
    public MenuListResponseDto menuFindRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(IllegalArgumentException::new);

        List<Menu> menus = menuRepository.findByRestaurant(restaurant);
        List<MenuInfoResponseDto> menuInfoResponseDtos = menus.stream()
                .map(MenuInfoResponseDto::from)
                .toList();

        return MenuListResponseDto.from(menuInfoResponseDtos);
    }

    // 전체 메뉴 조회
    public MenuListResponseDto menuFindAll() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuInfoResponseDto> menuInfoResponseDtos = menus.stream()
                .map(MenuInfoResponseDto::from)
                .toList();
        return MenuListResponseDto.from(menuInfoResponseDtos);
    }

    // 특정 메뉴 단일 조회
    public MenuInfoResponseDto menuFindOne(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 없습니다."));
        return MenuInfoResponseDto.from(menu);
    }

}
