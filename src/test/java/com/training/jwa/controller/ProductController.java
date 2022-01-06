package com.training.jwa.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.training.jwa.models.Product;
import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class ProductController extends AbstractTest {

    String uri = "/pr";
    int productId = 763;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
    }

    @Test
    @DisplayName(value = "Testing save a product")
    @Order(value =1)
    void testSaveProducts() throws Exception {
        Product product = new Product(productId, "DummyWidget", 100, 20);
        System.out.println(product);
        String productJSON = super.mapToJson(product);
        System.out.println(productJSON);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(productJSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(201, status);
        assertEquals(content, "Product saved successfully");
    }

    @Test
    @DisplayName(value = "Testing update a product")
    @Order(value =2)
    void testUpdateProducts() throws Exception {
        Product product = new Product(productId, "NewDummyWidget", 200, 300);
        System.out.println(product);
        String productJSON = super.mapToJson(product);
        System.out.println(productJSON);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.put(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(productJSON)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(201, status);
        assertEquals(content, "Product saved successfully");
    }

    @Test
    @DisplayName(value = "Testing delete a product")
    @Order(value =5)
    void testDeleteProducts() {
    }

    @Test
    @DisplayName(value = "Testing get a product")
    @Order(value =3)
    void testGetProduct() {
    }

    @Test
    @DisplayName(value = "Testing get all products")
    @Order(value =4)
    void testGetProducts() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        Product[] products = super.mapFromJson(content,Product[].class);
        assertEquals(200, status);
        assertTrue(products.length > 0);
    }



    @BeforeAll
    static void beforeAll() {
    }
    @AfterEach
    void tearDown() {
    }
}