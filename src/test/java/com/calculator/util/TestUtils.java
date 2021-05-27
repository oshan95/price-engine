package com.calculator.util;

import com.calculator.model.Product;
import org.springframework.beans.BeanUtils;

import static org.springframework.test.util.ReflectionTestUtils.setField;

public class TestUtils {

    public static Product mockProduct(int productId, String productName, boolean rare, int unitsPerCarton,
                                int cartonPrice) {
        final Product product = BeanUtils.instantiateClass(Product.class);
        setField(product, "productId", productId);
        setField(product, "productName", productName);
        setField(product, "rareProduct", rare);
        setField(product, "unitsPerCarton", unitsPerCarton);
        setField(product, "cartonPrice", cartonPrice);
        return product;
    }

}
