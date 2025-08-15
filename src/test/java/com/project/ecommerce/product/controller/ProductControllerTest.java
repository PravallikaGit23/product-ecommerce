/*
package com.project.ecommerce.product.controller;

import com.project.ecommerce.product.ExceptionHandler.ProductNotFound;
import com.project.ecommerce.product.dto.ResponseProduct;
import com.project.ecommerce.product.model.Product;
import com.project.ecommerce.product.service.ProductService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    ProductController productController;

    @SneakyThrows
    @Test
    void testGetProductByIdSuccess(){
        Product product= new Product();
        product.setId(1);
        product.setTitle("test");
        product.setDescription("test");
        product.setPrice(100.0);
        product.setImageUrl("test");

        Mockito.when(productService.getProduct(1)).thenReturn(product);
        ResponseEntity<ResponseProduct> testProduct = productController.getProduct(1);

        assertEquals(200, testProduct.getStatusCodeValue());
        assertNotNull(testProduct.getBody());
        assertEquals("test", testProduct.getBody().getTitle());
        assertEquals("test", testProduct.getBody().getDescription());
        assertEquals(100.0, testProduct.getBody().getPrice());
        assertEquals("test", testProduct.getBody().getImageUrl());
        assertEquals(1, testProduct.getBody().getId());

    }
}*/
