package com.example.GoodsCollectionPoint.repositories;

import com.example.GoodsCollectionPoint.models.PickupPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PickupPointRepository extends JpaRepository<PickupPoint, Long> {
    List<PickupPoint> findByName(String name);
}
