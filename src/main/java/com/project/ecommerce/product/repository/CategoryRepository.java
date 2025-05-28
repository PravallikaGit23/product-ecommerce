package com.project.ecommerce.product.repository;

import com.project.ecommerce.product.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
 Category findByName(String category);
 Category save(Category category);
 Optional<Category> findById(long id);
}
