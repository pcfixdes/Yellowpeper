package com.kata.kata.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long orderId;

    @NotNull(message = "User ID is mandatory")
    @Column(nullable = false)
    private Long userId;

    @NotNull(message = "Total amount is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total amount must be greater than zero")
    @Column(nullable = false)
    private BigDecimal totalAmount;

    @NotNull(message = "Order date is mandatory")
    private LocalDateTime orderDate = LocalDateTime.now();

    @NotBlank(message = "Status is mandatory")
    @Size(max = 20, message = "Status must be less than or equal to 20 characters")
    @Column(nullable = false, length = 20)
    private String status;

    @NotEmpty(message = "Order must contain at least one order item")
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude // Prevent circular reference
    private Set<OrderItem> orderItems = new HashSet<>();
}
