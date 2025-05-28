package com.project.ecommerce.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {

    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
