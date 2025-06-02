package com.likelion.likelionassignmentcrud.restaurant.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantId;

    private String name;

    private String location;

    private String address;

    @Builder
    private Restaurant(String name, String location, String address) {
        this.name = name;
        this.location = location;
        this.address = address;
    }
}
