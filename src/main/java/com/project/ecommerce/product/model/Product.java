package com.project.ecommerce.product.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Entity
public class Product  extends BaseModel {

    private String title;
    private String description;
    private double price;
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
