package com.ecommerce.springbootrestapi.repositoryDAO;

import com.ecommerce.springbootrestapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find products by category.
    List<Product> findByCategories_Name(String categoryName);

    // Find products within a certain price range.
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

}
