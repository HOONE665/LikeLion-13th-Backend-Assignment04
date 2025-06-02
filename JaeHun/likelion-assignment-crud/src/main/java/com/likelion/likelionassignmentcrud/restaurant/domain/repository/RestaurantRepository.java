package com.likelion.likelionassignmentcrud.restaurant.domain.repository;

import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
