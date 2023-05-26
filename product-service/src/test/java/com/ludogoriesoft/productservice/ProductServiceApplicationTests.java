package com.ludogoriesoft.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ludogoriesoft.productservice.dto.ProductRequest;
import com.ludogoriesoft.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

// Note: The Flyway Migration runs before the tests. Because of that, the migrations need to be taken into account.
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    private static MySQLContainer<?> mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.24"));

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", mySQLContainer::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", mySQLContainer::getPassword);
    }

    @Test
    void createProductTest() throws Exception {
        final String createProductUrl = "/api/v1/products/new";
        final ProductRequest newProductRequest = getTestProductRequest();
        final String newProductJson = objectMapper.writeValueAsString(newProductRequest);
        final int numberOfProductBeforePost = productRepository.findAll().size();

        mockMvc.perform(MockMvcRequestBuilders.post(createProductUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newProductJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Assertions.assertEquals(numberOfProductBeforePost + 1, productRepository.findAll().size());
    }

    @Test
    void getProductsTest() throws Exception {
        final String getProductsUrl = "/api/v1/products";

        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(getProductsUrl))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final String jsonResponse = result.getResponse().getContentAsString();
        final String productsAsJson = objectMapper.writeValueAsString(productRepository.findAll());
        Assertions.assertEquals(productsAsJson, jsonResponse);
    }

    private ProductRequest getTestProductRequest() {
        return ProductRequest.builder()
                .name("Iphone 13")
                .description("Smartphone")
                .price(BigDecimal.valueOf(1200))
                .build();
    }
}
