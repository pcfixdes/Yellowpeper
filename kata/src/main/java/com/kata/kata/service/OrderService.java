package com.kata.kata.service;



import com.kata.kata.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order placeOrder(Order order);

    Optional<Order> getOrderById(Long orderId);

    List<Order> getOrdersByUserId(Long userId);

    List<Order> getAllOrders();

    Order updateOrderStatus(Long orderId, String status);
}

