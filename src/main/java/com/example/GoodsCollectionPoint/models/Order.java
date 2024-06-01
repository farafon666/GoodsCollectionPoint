package com.example.GoodsCollectionPoint.models;

import com.example.GoodsCollectionPoint.models.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private PickupPoint pickupPoint;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    private Integer totalPrice;

    private LocalDateTime dateOfCreated;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @PrePersist
    private void onCreate() {
        dateOfCreated = LocalDateTime.now();
    }

}
