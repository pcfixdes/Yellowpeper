package com.kata.kata.service;



import com.kata.kata.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product addProduct(Product product);

    Product updateProduct(Long productId, Product productDetails);

    void deleteProduct(Long productId);

    Optional<Product> getProductById(Long productId);

    List<Product> getAllProducts();

    List<Product> searchProducts(String keyword);

    List<Product> getProductsBySellerId(String sellerId);
}

