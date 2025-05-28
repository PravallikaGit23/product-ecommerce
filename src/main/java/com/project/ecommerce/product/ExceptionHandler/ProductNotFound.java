package com.project.ecommerce.product.ExceptionHandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFound extends Exception {
    private String message;

    public ProductNotFound(String message) {
        this.message = message;
    }
}
