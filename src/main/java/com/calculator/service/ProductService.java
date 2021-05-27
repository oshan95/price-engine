package com.calculator.service;

import com.calculator.model.Product;

import java.util.List;

public interface ProductService {

    /**
     * Get all the available products
     *
     * @return list of products
     */
    List<Product> getAllProducts();
}
