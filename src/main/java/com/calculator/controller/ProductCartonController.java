package com.calculator.controller;

import com.calculator.model.ProductCarton;
import com.calculator.repository.ProductCartonRepository;
import com.calculator.service.ProductCartonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = {"/product_carton"}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductCartonController {

    private ProductCartonService productCartonService;

    /**
     * Construct a new instance
     *
     * @param productCartonService the ProductCartonService
     */
    @Autowired
    public ProductCartonController(final ProductCartonService productCartonService) {
        this.productCartonService = productCartonService;
    }

    @GetMapping(value = "/product")
    public Optional<ProductCarton> getProduct(@RequestParam(value = "id", required = true) String id){
        return productCartonService.getProductCarton(id);
    }
}
