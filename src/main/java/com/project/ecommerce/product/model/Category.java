package com.project.ecommerce.product.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel{

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnore
    List<Product> products;

}
