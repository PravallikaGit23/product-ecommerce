package com.project.ecommerce.product.service;

import com.project.ecommerce.product.ExceptionHandler.ProductNotFound;
import com.project.ecommerce.product.ExceptionHandler.ProductNotUpdated;
import com.project.ecommerce.product.model.Category;
import com.project.ecommerce.product.model.Product;
import com.project.ecommerce.product.repository.CategoryRepository;
import com.project.ecommerce.product.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dataBaseProductService")
public class SlipKartProductService implements ProductService{
    private final ProductRepo productRepo;
    private CategoryRepository categoryRepository;

    public SlipKartProductService(ProductRepo productRepo, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepo = productRepo;
    }
    //not getting proper error when passed wrong id.
    @Override
    public Product getProduct(long id) throws ProductNotFound {
        Optional<Product> product ;
        try{
            product = productRepo.getById(id);
        }
        catch (Exception e){
            throw new ProductNotFound(e.getMessage());
        }
        if (product.isPresent()) {
            System.out.println("title: " + product.get().getTitle());
            return product.get();
        }

       return null;
    }

    @Override
    public List<Product> getAllProducts() throws ProductNotFound {
        List<Product> products = productRepo.findAll();
        return products;
    }

    @Override
    public List<Product> getLimitedProducts() throws ProductNotFound {
        List<Product> products = productRepo.getProductDataAndLimit();
        return products;
    }

    @Override
    public List<Product> getSortedProducts() throws ProductNotFound {
        List<Product> products = productRepo.getSortedProductsByTitle();
        return products;
    }

    @Override
    public Product createProduct(String title, double price, String description, String imageUrl, String category) throws ProductNotUpdated {
        Product product = new Product();
        product.setTitle(title);
        product.setPrice(price);
        product.setDescription(description);
        product.setImageUrl(imageUrl);

        Category categoryFromDB = categoryRepository.findByName(category);
        if(categoryFromDB == null) {
            Category newCategory = new Category();
            newCategory.setName(category);
           // categoryRepository.save(newCategory);
            categoryFromDB = newCategory;
        }
        product.setCategory(categoryFromDB);
        Product product1 = productRepo.save(product);

        if (product1 == null) {
            throw new ProductNotUpdated("please try again");
        }
        return product1;
    }

    @Override
    public Product deleteProduct(long id) throws ProductNotUpdated {
        Product product = productRepo.deleteById(id);
        if (product == null) {
            throw new ProductNotUpdated("please enter correct id.");
        }
        return product;
    }

    @Override
    public Product updateProductUsingPut(long id, String title, String description, double price, String image, String category) throws ProductNotUpdated, ProductNotFound {

        Product product = getProduct(id);
         product.setId(id);
         product.setTitle(title);
         product.setDescription(description);
         product.setPrice(price);
         product.setImageUrl(image);
        Category categoryFromDB = categoryRepository.findByName(category);

        if(categoryFromDB == null) {
            Category newCategory = new Category();
            newCategory.setName(category);
            categoryFromDB = newCategory;
        }
        product.setCategory(categoryFromDB);
        Product product1 = productRepo.save(product);
        if(product1 == null) {
            throw new ProductNotUpdated("please try again");
        }
        return product1;
    }

    @Override
    public Product updateProductUsingPatch(long id, String title, String description, double price, String image, String category) throws ProductNotUpdated, ProductNotFound {
        Product product = getProduct(id);
        if (id != 0)
            product.setId(id);
        if (title != null)
            product.setTitle(title);
        if (description != null)
            product.setDescription(description);
        if (price != 0)
            product.setPrice(price);
        if (image != null)
            product.setImageUrl(image);
        Category categoryFromDB = categoryRepository.findByName(category);

        if(categoryFromDB == null) {
            Category newCategory = new Category();
            newCategory.setName(category);
            categoryFromDB = newCategory;
        }
        product.setCategory(categoryFromDB);
        Product product1 = productRepo.save(product);
        if(product1 == null) {
            throw new ProductNotUpdated("please try again");
        }
        return product1;
    }
}
