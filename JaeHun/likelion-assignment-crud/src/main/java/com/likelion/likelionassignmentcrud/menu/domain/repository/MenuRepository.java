package com.likelion.likelionassignmentcrud.menu.domain.repository;

import com.likelion.likelionassignmentcrud.menu.domain.Menu;
import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByRestaurant(Restaurant restaurant);
}