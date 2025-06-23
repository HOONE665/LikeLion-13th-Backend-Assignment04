package com.likelion.likelionassignmentcrud.menu.application;

import com.likelion.likelionassignmentcrud.commom.error.ErrorCode;
import com.likelion.likelionassignmentcrud.commom.exception.BusinessException;
import com.likelion.likelionassignmentcrud.menu.api.dto.request.MenuSaveRequestDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.request.MenuUpdateRequestDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuInfoResponseDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuListResponseDto;
import com.likelion.likelionassignmentcrud.menu.domain.Menu;
import com.likelion.likelionassignmentcrud.menu.domain.repository.MenuRepository;
import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import com.likelion.likelionassignmentcrud.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new BusinessException(ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION));

        Menu menu = Menu.builder()
                .name(menuSaveRequestDto.name())
                .price(menuSaveRequestDto.price())
                .restaurant(restaurant)
                .build();

        menuRepository.save(menu);
    }

    // 특정 식당의 메뉴 목록 조회
    public MenuListResponseDto menuFindRestaurant(Long restaurantId, Pageable pageable) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION));

        Page<Menu> menuPage = menuRepository.findByRestaurant(restaurant, pageable);
        Page<MenuInfoResponseDto> menuInfoResponseDtos = menuPage.map(MenuInfoResponseDto::from);

        return MenuListResponseDto.from(menuInfoResponseDtos);
    }

    // 전체 메뉴 조회
    public MenuListResponseDto menuFindAll(Pageable pageable) {
        Page<Menu> menuPage = menuRepository.findAll(pageable);
        Page<MenuInfoResponseDto> menuInfoResponseDtos = menuPage.map(MenuInfoResponseDto::from);
        return MenuListResponseDto.from(menuInfoResponseDtos);
    }

    // 특정 메뉴 단일 조회
    public MenuInfoResponseDto menuFindOne(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MENU_NOT_FOUND_EXCEPTION));
        return MenuInfoResponseDto.from(menu);
    }

    // 메뉴 수정
    @Transactional
    public void menuUpdate(Long menuId, MenuUpdateRequestDto menuUpdateRequestDto) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MENU_NOT_FOUND_EXCEPTION));

        // restaurantId가 변경될 경우 새로운 Restaurant를 찾아 설정
        if (!menu.getRestaurant().getRestaurantId().equals(menuUpdateRequestDto.restaurantId())) {
            Restaurant newRestaurant = restaurantRepository.findById(menuUpdateRequestDto.restaurantId())
                    .orElseThrow(() -> new BusinessException(ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION));
            menu.updateRestaurant(newRestaurant);
        }

        menu.update(menuUpdateRequestDto.name(), menuUpdateRequestDto.price());
    }

    // 메뉴 삭제
    @Transactional
    public void menuDelete(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new BusinessException(ErrorCode.MENU_NOT_FOUND_EXCEPTION));
        menuRepository.delete(menu);
    }
}