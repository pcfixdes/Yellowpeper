package com.kata.kata.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long productId;

    @NotBlank(message = "Product name is mandatory")
    @Size(max = 100, message = "Product name must be less than or equal to 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @Size(max = 500, message = "Description must be less than or equal to 500 characters")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "Stock is mandatory")
    @Column(nullable = false)
    private Integer stock;

    @NotBlank(message = "Seller ID is mandatory")
    @Size(max = 50, message = "Seller ID must be less than or equal to 50 characters")
    @Column(nullable = false, length = 50)
    private String sellerId;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();
}

