package com.calculator.service;

import com.calculator.domain.PriceComponent;
import com.calculator.domain.PurchaseOrder;
import com.calculator.model.Product;
import com.calculator.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static com.calculator.util.TestUtils.mockProduct;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

@ExtendWith(SpringExtension.class)
public class PriceEngineServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    PriceEngineServiceImpl priceEngineService;

    @Test
    void testGetPriceListInvalidProductId() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        Exception ex = assertThrows(
                NoSuchElementException.class,
                () -> priceEngineService.getPriceList(1));
        assertTrue(ex.getMessage().contains("Product is not found for the product id: 1"));
    }

    @Test
    void testGetPriceList() {
        final Product product = mockProduct(1, "test1", true, 10, 500);
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        List<PriceComponent> list =  priceEngineService.getPriceList(product.getProductId());

        System.out.println(list);

        assertEquals(50, list.size());
        assertEquals(65.0, list.get(0).getPrice());
        assertEquals(500.0, list.get(9).getPrice());
        assertEquals(2385.0, list.get(48).getPrice());
        assertEquals(2250.0, list.get(49).getPrice());
    }

    @Test
    void testCalculateAmountCartonOrder() {
        final Product product = mockProduct(1, "test1", true, 10, 500);
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        final Double amount = priceEngineService.calculateAmount(mockPurchaseOrder(1, true, 5));
        assertEquals(2250.0, amount);
    }

    @Test
    void testCalculateAmountUnitOrder() {
        final Product product = mockProduct(1, "test1", true, 10, 500);
        when(productRepository.findById(anyInt())).thenReturn(Optional.of(product));

        final Double amount = priceEngineService.calculateAmount(mockPurchaseOrder(1, false, 5));
        assertEquals(325.0, amount);
    }

    @Test
    void testCalculateAmountInvalidProduct() {
        when(productRepository.findById(anyInt())).thenReturn(Optional.empty());

        Exception ex = assertThrows(
                NoSuchElementException.class,
                () -> priceEngineService.calculateAmount(mockPurchaseOrder(1, false, 5)));
        assertTrue(ex.getMessage().contains("Product is not found for the product id: 1"));
    }

    private PurchaseOrder mockPurchaseOrder(int productId, boolean cartonOrder, int units) {
        final PurchaseOrder purchaseOrder = BeanUtils.instantiateClass(PurchaseOrder.class);
        setField(purchaseOrder, "productId", productId);
        setField(purchaseOrder, "cartonOrder", cartonOrder);
        setField(purchaseOrder, "units", units);
        return purchaseOrder;
    }
}
