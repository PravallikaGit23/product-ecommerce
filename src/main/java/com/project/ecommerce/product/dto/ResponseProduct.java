package com.project.ecommerce.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import com.project.ecommerce.product.model.Category;
import com.project.ecommerce.product.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseProduct {
    private long id;
    private String title;
    private String description;

    private double price;

    private String imageUrl;

    private Category category;

    public ResponseProduct(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.category = product.getCategory();

    }
}
