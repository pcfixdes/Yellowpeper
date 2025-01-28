package com.kata.kata.service;



import com.kata.kata.model.Order;
import com.kata.kata.model.OrderItem;
import com.kata.kata.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order placeOrder(Order order) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (OrderItem item : order.getOrderItems()) {
            BigDecimal price = getProductPrice(item.getProductId());
            int stock = getProductStock(item.getProductId());

            if (stock < item.getQuantity()) {
                throw new RuntimeException("Product out of stock.");
            }

            item.setPrice(price);
            totalAmount = totalAmount.add(price.multiply(BigDecimal.valueOf(item.getQuantity())));

            updateProductStock(item.getProductId(), stock - item.getQuantity());

            item.setOrder(order);
        }

        order.setTotalAmount(totalAmount);
        order.setStatus("Processing");

        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found."));

        order.setStatus(status);
        return orderRepository.save(order);
    }


    private BigDecimal getProductPrice(Long productId) {
        return BigDecimal.valueOf(100.00); // Placeholder value
    }

    private int getProductStock(Long productId) {
        return 50; // Placeholder value
    }

    private void updateProductStock(Long productId, int newStock) {
    }
}
