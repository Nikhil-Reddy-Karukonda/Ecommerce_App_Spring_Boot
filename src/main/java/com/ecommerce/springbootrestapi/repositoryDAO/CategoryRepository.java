package com.ecommerce.springbootrestapi.repositoryDAO;

import com.ecommerce.springbootrestapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Find all categories that a certain product belongs to
    List<Category> findByProducts_Id(Long productId);

    // Get count of products in each category
    @Query("SELECT c.name, COUNT(p.id) FROM Category c JOIN c.products p GROUP BY c.name")
    List<Object[]> countProductsInEachCategory();

}
