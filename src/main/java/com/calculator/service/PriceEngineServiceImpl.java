package com.calculator.service;

import com.calculator.domain.PurchaseOrder;
import com.calculator.model.Product;
import com.calculator.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PriceEngineServiceImpl implements PriceEngineService {

    private ProductRepository productRepository;

    public PriceEngineServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 
     * {@link PriceEngineService#getPriceList(Integer)}
     */
    @Override
    public Map<Integer, Double> getPriceList(final Integer productId) {
        final Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NoSuchElementException("Product is not found for the product id: " + productId));

        final Integer cartonPrice = product.getCartonPrice();
        final Integer unitsPerCarton = product.getUnitsPerCarton();
        final Integer increaseRate = 30;
        final Integer discountRate = 10;
        final Double unitPriceWithCarton = (double) cartonPrice/unitsPerCarton;
        final Double unitPriceWithoutCarton = unitPriceWithCarton
                + ( (unitPriceWithCarton * increaseRate) / 100);

        final Map<Integer, Double> priceList = new HashMap<>();

        for (int i=1; i<51; i++) {

            if (i < unitsPerCarton) {
                priceList.put(i, i*unitPriceWithoutCarton);
            } else if (i % unitsPerCarton == 0) {
                priceList.put(i, getDiscountedPrice(discountRate, i,i*unitPriceWithCarton, unitsPerCarton));
            } else if (i % unitsPerCarton > 0) {
                int extraUnitsExceedsCartonCapacity = i % unitsPerCarton;
                int unitsWithCarton = i - extraUnitsExceedsCartonCapacity;
                double amount = (extraUnitsExceedsCartonCapacity * unitPriceWithoutCarton)
                        + (getDiscountedPrice(discountRate, unitsWithCarton,unitsWithCarton * unitPriceWithCarton, unitsPerCarton));
                priceList.put(i, amount);
            }
        }

        return priceList;
    }

    /**
     * 
     * {@link PriceEngineService#calculateAmount(PurchaseOrder)}
     */
    @Override
    public Double calculateAmount(final PurchaseOrder purchaseOrder) {
        final Product product = productRepository.findById(purchaseOrder.getProductId())
                .orElseThrow(() -> new NoSuchElementException("Product is not found for the product id: " + purchaseOrder.getProductId()));

        final Integer cartonPrice = product.getCartonPrice();
        final Integer unitsPerCarton = product.getUnitsPerCarton();
        final Integer increaseRate = 30;
        final Integer discountRate = 10;

        final Double unitPriceWithCarton = (double) cartonPrice/unitsPerCarton;
        final Double unitPriceWithoutCarton = unitPriceWithCarton
                + ( (unitPriceWithCarton * increaseRate) / 100);

        final Integer orderUnits = purchaseOrder.getUnits();

        if (purchaseOrder.getCartonOrder()) {
            double grossAmount = cartonPrice * orderUnits;
            if (orderUnits >= 3) {
                return grossAmount - ((grossAmount * discountRate) / 100);
            }
            return grossAmount;
        } else {
            if (orderUnits < unitsPerCarton) {
                return orderUnits * unitPriceWithoutCarton;
            } else if (orderUnits % unitsPerCarton == 0) {
                return getDiscountedPrice(discountRate, orderUnits,orderUnits * unitPriceWithCarton, unitsPerCarton);
            } else if (orderUnits % unitsPerCarton > 0) {
                int extraUnitsExceedsCartonCapacity = orderUnits % unitsPerCarton;
                int unitsWithCarton = orderUnits - extraUnitsExceedsCartonCapacity;
                double amount = (extraUnitsExceedsCartonCapacity * unitPriceWithoutCarton)
                        + (getDiscountedPrice(discountRate, unitsWithCarton,unitsWithCarton * unitPriceWithCarton, unitsPerCarton));
                return amount;
            }
        }
        return 0.0;
    }

    /**
     * 
     * get the discounted price
     * 
     * @param discount
     * @param units
     * @param grossAmount
     * @param unitsPerCarton
     * @return
     */
    private Double getDiscountedPrice(int discount, int units, double grossAmount, int unitsPerCarton) {
        int count = units / unitsPerCarton;
        if (count >= 3) {
            return grossAmount - ((grossAmount * discount) / 100);
        }
        return grossAmount;
    }
}
