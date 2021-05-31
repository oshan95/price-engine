package com.calculator.service;

import com.calculator.domain.PriceComponent;
import com.calculator.domain.PurchaseOrder;

import java.util.List;
import java.util.Map;

public interface PriceEngineService {

    /**
     *
     * @param productId
     * @return List of PriceComponents
     */
    List<PriceComponent> getPriceList(final Integer productId);

    /**
     *
     * @param purchaseOrder
     * @return Net amount
     */
    Double calculateAmount(final PurchaseOrder purchaseOrder);
}
