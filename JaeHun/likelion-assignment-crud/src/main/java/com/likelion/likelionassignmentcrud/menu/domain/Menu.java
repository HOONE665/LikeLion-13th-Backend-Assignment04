package com.likelion.likelionassignmentcrud.menu.domain;

import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    private String name;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @Builder
    private Menu(String name, int price, Restaurant restaurant) {
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

    // 메뉴 정보 업데이트 메서드
    public void update(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // 연관된 식당 업데이트 메서드 (메뉴의 식당 변경 시 사용)
    public void updateRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}