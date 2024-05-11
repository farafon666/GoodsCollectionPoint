package com.example.GoodsCollectionPoint.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pickup_points")
@Data
public class PickupPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;
}
