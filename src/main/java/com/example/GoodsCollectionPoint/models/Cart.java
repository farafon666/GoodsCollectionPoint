package com.example.GoodsCollectionPoint.models;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();

    public List<CartItem> getItems() {
        return cartItems;
    }

    public void addItem(Product product, Integer quantity) {
        // Проверяем, есть ли товар уже в корзине
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        // Если товара нет в корзине, добавляем новый элемент
        cartItems.add(new CartItem(product, quantity));
    }

    public void removeItem(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public void clear() {
        cartItems.clear();
    }
}
