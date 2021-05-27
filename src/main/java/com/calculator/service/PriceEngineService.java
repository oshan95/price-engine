package com.calculator.service;

import com.calculator.domain.PurchaseOrder;

import java.util.Map;

public interface PriceEngineService {

    /**
     *
     * @param productId
     * @return HashMap of price list
     */
    Map<Integer, Double> getPriceList(final Integer productId);

    /**
     *
     * @param purchaseOrder
     * @return Net amount
     */
    Double calculateAmount(final PurchaseOrder purchaseOrder);
}
