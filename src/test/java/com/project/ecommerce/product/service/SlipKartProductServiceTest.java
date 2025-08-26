package com.project.ecommerce.product.service;

import com.project.ecommerce.product.ExceptionHandler.ProductNotFound;
import com.project.ecommerce.product.ExceptionHandler.ProductNotUpdated;
import com.project.ecommerce.product.model.Category;
import com.project.ecommerce.product.model.Product;
import com.project.ecommerce.product.repository.CategoryRepository;
import com.project.ecommerce.product.repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SlipKartProductServiceTest {

    @Mock
    ProductRepo productRepo;
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    SlipKartProductService slipKartProductService;

    @Test
    void getProduct_HappyPath() throws ProductNotFound {
        Product product = new Product();
        product.setTitle("Phones");
        product.setDescription("i phone description");
        when(productRepo.getById(5)).thenReturn(Optional.of(product));

        Product recievedProduct = slipKartProductService.getProduct(5);
        assertNotNull(recievedProduct);
        assertEquals("Phones",recievedProduct.getTitle());
        assertEquals("i phone description",recievedProduct.getDescription());
    }

    @Test
    void getAllProducts_HappyPath() throws ProductNotFound {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setTitle("Phones");
        product1.setDescription("i phone description");

        Product product2 = new Product();
        product2.setTitle("Clothes");
        product2.setDescription("women clothes description");

        Product product3= new Product();
        product3.setTitle("Racket");
        product3.setDescription("Badminton racket");

        products.add(product1);
        products.add(product2);
        products.add(product3);

        when(productRepo.findAll()).thenReturn(products);

        List<Product> recievedList = slipKartProductService.getAllProducts();
        assertEquals(recievedList.size(),products.size());
        assertNotNull(recievedList);
    }

    @Test
    void createProduct_HappyPath() throws ProductNotUpdated {
        Product product = new Product();
        product.setTitle("gadgets");
        product.setPrice(23);
        product.setDescription("best gadget ever");
        product.setImageUrl("http://imageUrl");

        Category category = new Category();
        category.setId(1);
        category.setName("Appliances");

        product.setCategory(category);

        when(categoryRepository.findByName("Appliances")).thenReturn(category);
        when(productRepo.save(any())).thenReturn(product);

        Product recievedProduct = slipKartProductService.createProduct(product.getTitle(),product.getPrice(),product.getDescription(),product.getImageUrl(),category.getName());
        assertNotNull(recievedProduct);
        assertEquals(recievedProduct.getTitle(),product.getTitle());
        assertEquals(recievedProduct.getDescription(),product.getDescription());
        assertEquals(recievedProduct.getImageUrl(),product.getImageUrl());
        assertEquals(recievedProduct.getCategory().getName(),category.getName());

    }

    @Test
    void deleteProduct_HappyPath() throws ProductNotUpdated {
        Product product = new Product();
        product.setId(1);
        product.setTitle("gadgets");
        product.setPrice(23);
        product.setDescription("best gadget ever");
        product.setImageUrl("http://imageUrl");

        when(productRepo.deleteById(1)).thenReturn(product);
        Product recievedProduct = slipKartProductService.deleteProduct(1);

        assertNotNull(recievedProduct);
        assertEquals(recievedProduct.getId(),product.getId());
        assertEquals(recievedProduct.getTitle(),product.getTitle());
        assertEquals(recievedProduct.getPrice(),product.getPrice());
    }

}