package com.calculator.controller;

import com.calculator.domain.PurchaseOrder;
import com.calculator.service.PriceEngineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = {"/calculator"}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class PriceEngineController {

    private static final String PRODUCT_ID = "product_id";

    private PriceEngineService priceEngineService;

    public PriceEngineController(final PriceEngineService priceEngineService) {
        this.priceEngineService = priceEngineService;
    }

    @GetMapping(value = "/prices")
    public ResponseEntity<Map<Integer, Double>> getProductPriceList(@RequestParam(value = PRODUCT_ID, required = true) Integer productId) {
        return new ResponseEntity<>(priceEngineService.getPriceList(productId), HttpStatus.OK);
    }

    @GetMapping(value = "/generate")
    public ResponseEntity<Double>  generateFinalAmount(@RequestBody PurchaseOrder purchaseOder) {
        return new ResponseEntity<>(priceEngineService.calculateAmount(purchaseOder), HttpStatus.OK);
    }

}
