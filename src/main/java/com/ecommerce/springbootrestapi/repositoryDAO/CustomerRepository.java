package com.ecommerce.springbootrestapi.repositoryDAO;

import com.ecommerce.springbootrestapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    List<Customer> findByAddress_City(String city);

    // Find customers who have ordered a certain product.
    List<Customer> findByOrders_Products_Id(Long productId);

    Optional<Customer> findByOrders_Id(Long orderId);

    // find all customers who have ordered products within a specific category.
    List<Customer> findByOrders_OrderItems_Product_Category_Id(Long categoryId);

}
