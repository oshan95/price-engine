package com.calculator.service;

import com.calculator.model.Product;
import com.calculator.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static com.calculator.util.TestUtils.mockProduct;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    void testGetAllProducts() {
        final List<Product> products = new ArrayList<>();
        products.add(mockProduct(1, "test1", true, 34, 400));
        products.add(mockProduct(2, "test2", false, 55, 565));
        products.add(mockProduct(3, "test3", false, 11, 789));
        when(productRepository.findAll()).thenReturn(products);

        final List<Product> returnedProducts = productService.getAllProducts();
        final Product expectedProduct = products.get(0);
        final Product actualProduct = returnedProducts.get(0);

        assertEquals(expectedProduct.getProductId(), actualProduct.getProductId());
        assertEquals(expectedProduct.getProductName(), actualProduct.getProductName());
        assertEquals(expectedProduct.isRareProduct(), actualProduct.isRareProduct());
        assertEquals(expectedProduct.getUnitsPerCarton(), actualProduct.getUnitsPerCarton());
        assertEquals(expectedProduct.getCartonPrice(), actualProduct.getCartonPrice());
    }

}
