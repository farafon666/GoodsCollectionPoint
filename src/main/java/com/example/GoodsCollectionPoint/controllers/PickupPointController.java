package com.example.GoodsCollectionPoint.controllers;

import com.example.GoodsCollectionPoint.models.PickupPoint;
import com.example.GoodsCollectionPoint.services.PickupPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PickupPointController {
    private final PickupPointService pickupPointService;

    @GetMapping("/points")
    public String pickupPoints(Model model, Principal principal) {
        model.addAttribute("points", pickupPointService.getPickupPoints());
        model.addAttribute("user", pickupPointService.getUserByPrincipal(principal));
        return  "points";
    }

    @PostMapping("/points/create")
    public String createPickupPoint(PickupPoint point) {
        pickupPointService.savePickupPoint(point);
        return "redirect:/points";
    }

    @PostMapping("/points/delete/{id}")
    public String deletePickupPoint(@PathVariable Long id, Principal principal) {
        pickupPointService.deletePickupPoint(pickupPointService.getUserByPrincipal(principal), id);
        return "redirect:/points";
    }
}
