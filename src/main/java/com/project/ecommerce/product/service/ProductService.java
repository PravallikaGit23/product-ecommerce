package com.project.ecommerce.product.service;

import com.project.ecommerce.product.ExceptionHandler.ProductNotFound;
import com.project.ecommerce.product.ExceptionHandler.ProductNotUpdated;
import com.project.ecommerce.product.model.Product;

import java.util.List;

public interface ProductService {

    public Product getProduct(long id) throws ProductNotFound;

    List<Product> getAllProducts() throws ProductNotFound;

    List<Product> getLimitedProducts() throws ProductNotFound;

    List<Product> getSortedProducts() throws ProductNotFound;

    Product createProduct(String title, double price, String description, String imageUrl, String category) throws ProductNotUpdated;

    Product deleteProduct(long id) throws ProductNotUpdated;

    Product updateProductUsingPut(long id, String title, String description, double price, String image, String category) throws ProductNotUpdated, ProductNotFound;

    Product updateProductUsingPatch(long id, String title, String description, double price, String image, String category) throws ProductNotUpdated, ProductNotFound;
}
