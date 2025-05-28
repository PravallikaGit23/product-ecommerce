package com.project.ecommerce.product.dto;

import com.project.ecommerce.product.model.Category;
import com.project.ecommerce.product.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;

    public Product toProduct(){
        Product product = new Product();
        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setPrice(getPrice());
        product.setImageUrl(getImage());

        Category category1 = new Category();
        category1.setName(getCategory());
        product.setCategory(category1);

     return product;
    }
}
