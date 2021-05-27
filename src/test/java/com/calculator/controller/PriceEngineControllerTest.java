package com.calculator.controller;

import com.calculator.domain.PurchaseOrder;
import com.calculator.service.PriceEngineService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;

@ExtendWith(SpringExtension.class)
public class PriceEngineControllerTest {

    private static final String PATH_PRICE_LIST = "/calculator/prices";
    private static final String PATH_GENERATE = "/calculator/generate";

    @Mock
    PriceEngineService priceEngineService;

    @InjectMocks
    PriceEngineController controller;

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
    void testGetPriceList() throws Exception {
        final Map<Integer, Double> priceList = new HashMap<>();
        priceList.put(1, 2.0);
        priceList.put(2, 3.0);
        priceList.put(3, 4.0);
        priceList.put(4, 5.0);

        when(priceEngineService.getPriceList(anyInt())).thenReturn(priceList);

        final MvcResult mvcResult = mockMvc
                .perform(get(PATH_PRICE_LIST).contentType(MediaType.APPLICATION_JSON_VALUE)
                .param("product_id", "1"))
                .andExpect(status().isOk())
                .andReturn();
        final String responseString = mvcResult.getResponse().getContentAsString();

        verify(priceEngineService, times(1)).getPriceList(anyInt());
        assertNotNull(responseString);
    }

    @Test
    void testGenerateFinalAmount() throws Exception {
       final String purchaseOrder  = "{\n" +
               "    \"productId\":2,\n" +
               "    \"cartonOrder\":false,\n" +
               "    \"units\":39\n" +
               "}";

        when(priceEngineService.calculateAmount(any(PurchaseOrder.class))).thenReturn(2344.75);

        final MvcResult mvcResult = mockMvc
                .perform(get(PATH_GENERATE).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(purchaseOrder))
                .andExpect(status().isOk())
                .andReturn();
        final String responseString = mvcResult.getResponse().getContentAsString();

        verify(priceEngineService, times(1)).calculateAmount(any(PurchaseOrder.class));
        assertNotNull(responseString);
    }

    @Test
    void testGenerateFinalAmountInvalidPurchaseOrder() throws Exception {
        final String purchaseOrder  = "{\n" +
                "    \"producctId\":2,\n" +
                "    \"cartonOrder\":false,\n" +
                "    \"units\":39\n" +
                "}";

        when(priceEngineService.calculateAmount(any(PurchaseOrder.class))).thenReturn(2344.75);

        final MvcResult mvcResult = mockMvc
                .perform(get(PATH_GENERATE).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(purchaseOrder))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    private PurchaseOrder mockPurchaseOrder(int productId, boolean cartonOrder, int units) {
        final PurchaseOrder purchaseOrder = BeanUtils.instantiateClass(PurchaseOrder.class);
        setField(purchaseOrder, "productId", productId);
        setField(purchaseOrder, "cartonOrder", cartonOrder);
        setField(purchaseOrder, "units", units);
        return purchaseOrder;
    }
}
