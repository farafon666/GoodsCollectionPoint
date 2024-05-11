package com.example.GoodsCollectionPoint.services;

import com.example.GoodsCollectionPoint.models.PickupPoint;
import com.example.GoodsCollectionPoint.models.User;
import com.example.GoodsCollectionPoint.repositories.PickupPointRepository;
import com.example.GoodsCollectionPoint.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PickupPointService {
    private final PickupPointRepository pickupPointRepository;
    private final UserRepository userRepository;

    public List<PickupPoint> getPickupPoints() {
        return  pickupPointRepository.findAll();
    }

    public void  savePickupPoint(PickupPoint point) {
        log.info("Creating new pickup point. Name: {}; Address: {}", point.getName(), point.getAddress());
        pickupPointRepository.save(point);
    }

    public void deletePickupPoint(User user, Long id) {
        PickupPoint point = pickupPointRepository.findById(id).orElse(null);
        if (point != null) {
            if (user.isAdmin()) {
                pickupPointRepository.delete(point);
            } else {
                log.error("User: {} doesn't have permission to delete the pickup point", user.getEmail());
            }
        } else {
            log.error("Pickup point with id {} is not found", id);
        }
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
}
