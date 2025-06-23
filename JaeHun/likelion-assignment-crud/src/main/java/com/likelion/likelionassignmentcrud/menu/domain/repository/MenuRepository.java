package com.likelion.likelionassignmentcrud.menu.domain.repository;

import com.likelion.likelionassignmentcrud.menu.domain.Menu;
import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Page<Menu> findByRestaurant(Restaurant restaurant, Pageable pageable);
}