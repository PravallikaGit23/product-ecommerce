package com.project.ecommerce.product.service;

import com.project.ecommerce.product.ExceptionHandler.ProductNotFound;
import com.project.ecommerce.product.ExceptionHandler.ProductNotUpdated;
import com.project.ecommerce.product.dto.FakeStoreProductDto;
import com.project.ecommerce.product.model.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProduct(long id) throws ProductNotFound {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto =
                restTemplate.exchange("https://fakestoreapi.com/products/" + id,
                        HttpMethod.GET, null,
                        FakeStoreProductDto.class

                );

        if(fakeStoreProductDto.getBody() == null)
            throw  new ProductNotFound("Please try again with correct Id");


        return fakeStoreProductDto.getBody().toProduct();
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFound {
//        ResponseEntity<FakeStoreProductDto[]> listResponseEntity = restTemplate.exchange(
//                "https://fakestoreapi.com/products",
//                HttpMethod.GET,
//                null,
//              FakeStoreProductDto[].class);
//        List<Product> products = new ArrayList<>();
//        for(FakeStoreProductDto fakeStoreProductDto : listResponseEntity.getBody()){
//            products.add(fakeStoreProductDto.toProduct());
//        }

        ResponseEntity<List<FakeStoreProductDto>> listResponseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
                }
        );
        if(! listResponseEntity.hasBody())
            throw  new ProductNotFound("Please try again ");


        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : listResponseEntity.getBody()) {
            products.add(fakeStoreProductDto.toProduct());
        }
        return products;
    }

    @Override
    public List<Product> getLimitedProducts() throws ProductNotFound {
        ResponseEntity<List<FakeStoreProductDto>> listResponseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products?limit=5",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
                }
        );
        if(! listResponseEntity.hasBody())
            throw  new ProductNotFound("Please try again ");

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : listResponseEntity.getBody()) {
            products.add(fakeStoreProductDto.toProduct());
        }
        return products;
    }

    @Override
    public List<Product> getSortedProducts() throws ProductNotFound {
        ResponseEntity<List<FakeStoreProductDto>> listResponseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products?sort=desc",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
                }
        );
        if(! listResponseEntity.hasBody())
            throw  new ProductNotFound("Please try again ");

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : listResponseEntity.getBody()) {
            products.add(fakeStoreProductDto.toProduct());
        }
        return products;
    }

    @Override
    public Product createProduct(String title, double price, String description, String imageUrl, String category) throws ProductNotUpdated {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(imageUrl);
        fakeStoreProductDto.setCategory(category);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreProductDto, headers);

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.POST,
                requestEntity,         //it requires body with headers
                FakeStoreProductDto.class
        );
        if(! responseEntity.hasBody())
            throw  new ProductNotUpdated("Please try again with correct format");

        return responseEntity.getBody().toProduct();
    }

    @Override
    public Product deleteProduct(long id) throws ProductNotUpdated {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.exchange(
                           "https://fakestoreapi.com/products/"+ id ,
                           HttpMethod.DELETE,
                            null,
                            FakeStoreProductDto.class
                           );
        if(! fakeStoreProductDtoResponseEntity.hasBody())
            throw  new ProductNotUpdated("NOT DELETED. Please try again with correct Id");

        return fakeStoreProductDtoResponseEntity.getBody().toProduct();
    }

    @Override
    public Product updateProductUsingPut(long id,  String title, String description, double price, String image, String category) throws ProductNotUpdated {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreProductDto, headers);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+ id ,
                HttpMethod.PUT,
                requestEntity,
                FakeStoreProductDto.class
        );
        if(! fakeStoreProductDtoResponseEntity.hasBody())
            throw  new ProductNotUpdated("NOT UPDATED. Please try again ");

        return fakeStoreProductDtoResponseEntity.getBody().toProduct();
    }

    @Override
    public Product updateProductUsingPatch(long id, String title, String description, double price, String image, String category) throws ProductNotUpdated {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        if(title != null)
         fakeStoreProductDto.setTitle(title);
        if(description != null)
         fakeStoreProductDto.setDescription(description);
        if(price != 0)
         fakeStoreProductDto.setPrice(price);
        if(image != null)
         fakeStoreProductDto.setImage(image);
        if(category != null)
         fakeStoreProductDto.setCategory(category);
     HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreProductDto, headers);

    ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/"+ id ,
                HttpMethod.PATCH,
                requestEntity,
                FakeStoreProductDto.class
        );
        if(responseEntity.getBody() == null)
            throw  new ProductNotUpdated("product not updated. Please try again with correct Id");

        return responseEntity.getBody().toProduct();
    }


}
