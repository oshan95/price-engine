package com.calculator.service;

import com.calculator.model.Product;
import com.calculator.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     *
     * {@link ProductService#getAllProducts()}
     */
    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
