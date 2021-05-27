package com.calculator.controller;

import com.calculator.model.Product;
import com.calculator.service.ProductService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.calculator.util.TestUtils.mockProduct;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class ProductControllerTest {

    private static final String PATH_PRODUCTS = "/products";

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController controller;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        mockMvc =
                MockMvcBuilders.standaloneSetup(controller).setMessageConverters(jackson2HttpMessageConverter).build();
    }

    @Test
    void  testGetProducts() throws Exception {
        final List<Product> products = new ArrayList<>();
        products.add(mockProduct(1, "test1", true, 34, 400));
        products.add(mockProduct(2, "test2", false, 55, 565));
        products.add(mockProduct(3, "test3", false, 11, 789));

        when(productService.getAllProducts()).thenReturn(products);

        final MvcResult mvcResult = mockMvc
                .perform(get(PATH_PRODUCTS).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        final String responseString = mvcResult.getResponse().getContentAsString();
        assertNotNull(responseString);

    }
}
