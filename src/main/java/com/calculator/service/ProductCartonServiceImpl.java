package com.calculator.service;

import com.calculator.model.ProductCarton;
import com.calculator.repository.ProductCartonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCartonServiceImpl implements ProductCartonService {

    private ProductCartonRepository productCartonRepository;

    /**
     * Construct a new instance
     *
     * @param productCartonRepository
     */
    @Autowired
    public ProductCartonServiceImpl(final ProductCartonRepository productCartonRepository) {
        this.productCartonRepository = productCartonRepository;
    }

    /**
     * {@link ProductCartonService#getProductCarton(String)}
     */
    @Override
    public Optional<ProductCarton> getProductCarton(String productCartonId) {
        return productCartonRepository.findById(productCartonId);
    }
}
