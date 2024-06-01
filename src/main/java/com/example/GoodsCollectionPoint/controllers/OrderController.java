package com.example.GoodsCollectionPoint.controllers;

import com.example.GoodsCollectionPoint.models.Order;
import com.example.GoodsCollectionPoint.models.PickupPoint;
import com.example.GoodsCollectionPoint.models.User;
import com.example.GoodsCollectionPoint.models.enums.OrderStatus;
import com.example.GoodsCollectionPoint.services.OrderService;
import com.example.GoodsCollectionPoint.services.PickupPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final PickupPointService pickupPointService;

    @GetMapping("/orders/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String viewAllOrders(Principal principal, Model model) {
        model.addAttribute("user", pickupPointService.getUserByPrincipal(principal));
        model.addAttribute("orders", orderService.getOrders());
        return "orders";
    }

    @GetMapping("/orders/user")
    public String viewUserOrders(Principal principal, Model model) {
        User user = pickupPointService.getUserByPrincipal(principal);
        model.addAttribute("user", pickupPointService.getUserByPrincipal(principal));
        model.addAttribute("orders", orderService.getOrdersByUser(user.getId()));
        return "user-orders";
    }

    @PostMapping("/orders/create")
    public String createOrder(@RequestParam Long pickupPointId, Principal principal, Model model) {
        String email = pickupPointService.getUserByPrincipal(principal).getEmail();
        PickupPoint pickupPoint = pickupPointService.getPickupPointById(pickupPointId);
        Order order = orderService.createOrder(pickupPoint, email);
        model.addAttribute("user", pickupPointService.getUserByPrincipal(principal));
        model.addAttribute("order", order);
        return "order-confirmation";
    }

    @PostMapping("/orders/updateStatus")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateOrderStatus(@RequestParam Long orderId, @RequestParam OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/orders/all";
    }
}
