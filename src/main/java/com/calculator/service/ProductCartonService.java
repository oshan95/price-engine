package com.calculator.service;

import com.calculator.model.ProductCarton;

import java.util.Optional;

public interface ProductCartonService {

    /**
     * test
     * @param productCartonId
     * @return
     */
    Optional<ProductCarton> getProductCarton(String productCartonId);
}
