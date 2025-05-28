package com.project.ecommerce.product.controller;

import com.project.ecommerce.product.ExceptionHandler.ProductNotFound;
import com.project.ecommerce.product.ExceptionHandler.ProductNotUpdated;
import com.project.ecommerce.product.dto.RequestDto;
import com.project.ecommerce.product.dto.ResponseProduct;
import com.project.ecommerce.product.model.Product;
import com.project.ecommerce.product.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController{

    private final ProductService productService;

    ProductController(@Qualifier("dataBaseProductService") ProductService productService){

        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ResponseProduct> getProduct(@PathVariable("id") long id) throws ProductNotFound {
        Product product =  productService.getProduct(id);
        ResponseProduct responseProduct = new ResponseProduct(product);

        ResponseEntity<ResponseProduct> responseEntity= new ResponseEntity<>(responseProduct, HttpStatusCode.valueOf(200));
        return responseEntity;
   }

   @GetMapping("/products")
    public ResponseEntity<List<ResponseProduct>> getProducts() throws ProductNotFound {
        List<Product> productList = productService.getAllProducts();
        List<ResponseProduct> responseProductList = new ArrayList<>();
        for (Product product : productList) {
            ResponseProduct responseProduct = new ResponseProduct(product);
            responseProductList.add(responseProduct);
        }
        return new ResponseEntity<>(responseProductList, HttpStatusCode.valueOf(200));
   }

    @GetMapping("/limitedproducts")
    public ResponseEntity<List<ResponseProduct>> getlimitedProducts() throws ProductNotFound {
        List<Product> productList = productService.getLimitedProducts();
        List<ResponseProduct> responseProductList = new ArrayList<>();
        for (Product product : productList) {
            ResponseProduct responseProduct = new ResponseProduct(product);
            responseProductList.add(responseProduct);

        }
        return new ResponseEntity<>(responseProductList, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/sortedProducts")
    public ResponseEntity<List<ResponseProduct>> getSortedProducts() throws ProductNotFound {
        List<Product> productList = productService.getSortedProducts();
        List<ResponseProduct> responseProductList = new ArrayList<>();
        for (Product product : productList) {
            ResponseProduct responseProduct = new ResponseProduct(product);
            responseProductList.add(responseProduct);

        }
        return new ResponseEntity<>(responseProductList, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/product")
    public ResponseEntity<ResponseProduct> createProduct(@RequestBody RequestDto requestDto) throws ProductNotUpdated {
      Product product =  productService.createProduct(
                requestDto.getTitle(),
                requestDto.getPrice(),
                requestDto.getDescription() ,
                requestDto.getImage() ,
                requestDto.getCategory()
        );
      ResponseProduct responseProduct = new ResponseProduct(product);
        return new ResponseEntity<>(responseProduct, HttpStatusCode.valueOf(200));

    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<ResponseProduct> deleteProduct(@PathVariable("id") long id) throws ProductNotUpdated {
        Product product= productService.deleteProduct(id);
        ResponseProduct responseProduct = new ResponseProduct(product);
        return new ResponseEntity<>(responseProduct, HttpStatusCode.valueOf(200));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ResponseProduct> updateCompleteProduct(@PathVariable("id") long id, @RequestBody RequestDto requestDto) throws ProductNotUpdated, ProductNotFound {

        Product product = productService.updateProductUsingPut(id,
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getPrice(),
                requestDto.getImage(),
                requestDto.getCategory()
                );
        ResponseProduct responseProduct = new ResponseProduct(product);
        return new ResponseEntity<>(responseProduct, HttpStatusCode.valueOf(200));
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<ResponseProduct> updateProduct(@PathVariable("id") long id, @RequestBody RequestDto requestDto) throws ProductNotUpdated, ProductNotFound {

        Product product = productService.updateProductUsingPatch(id,
                requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getPrice(),
                requestDto.getImage(),
                requestDto.getCategory()
        );
        ResponseProduct responseProduct = new ResponseProduct(product);
        return new ResponseEntity<>(responseProduct, HttpStatusCode.valueOf(200));
    }


}
