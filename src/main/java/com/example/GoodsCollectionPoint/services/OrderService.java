package com.example.GoodsCollectionPoint.services;

import com.example.GoodsCollectionPoint.models.*;
import com.example.GoodsCollectionPoint.models.enums.OrderStatus;
import com.example.GoodsCollectionPoint.repositories.OrderRepository;
import com.example.GoodsCollectionPoint.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final Cart cart;

    public List<Order> getOrders() {
        return orderRepository.findAll().stream()
                .sorted((o1, o2) -> o2.getDateOfCreated().compareTo(o1.getDateOfCreated()))
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId).stream()
                .sorted((o1, o2) -> o2.getDateOfCreated().compareTo(o1.getDateOfCreated()))
                .collect(Collectors.toList());
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(PickupPoint pickupPoint, String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setPickupPoint(pickupPoint);
        order.setStatus(OrderStatus.PENDING);

        Integer totalPrice = 0;

        for (CartItem cartItem : cart.getItems()) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getProduct().getPrice());

            totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            order.getOrderDetails().add(orderDetail);
        }

        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        cart.clear();

        log.info("Saving new Order from {}", email);

        return order;
    }

    public void updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = getOrderById(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }
}
