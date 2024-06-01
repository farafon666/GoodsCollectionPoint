package com.example.GoodsCollectionPoint.controllers;

import com.example.GoodsCollectionPoint.models.Cart;
import com.example.GoodsCollectionPoint.models.Product;
import com.example.GoodsCollectionPoint.services.PickupPointService;
import com.example.GoodsCollectionPoint.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final PickupPointService pickupPointService;
    private final Cart cart;

    @GetMapping("/cart")
    public String viewCart(Model model, Principal principal) {
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("pickupPoints", pickupPointService.getPickupPoints());
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId, @RequestParam Integer quantity) {
        Product product = productService.getProductById(productId);
        cart.addItem(product, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long productId) {
        cart.removeItem(productId);
        return "redirect:/cart";
    }
}
