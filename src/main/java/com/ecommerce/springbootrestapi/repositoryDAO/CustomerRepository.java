package com.ecommerce.springbootrestapi.repositoryDAO;

import com.ecommerce.springbootrestapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);
    List<Customer> findByAddresses_City(String city);

    // Find customers who have ordered a certain product.
    @Query("SELECT c FROM Customer c JOIN c.orders o JOIN o.orderItems oi WHERE oi.product.id = :productId")
    List<Customer> findCustomersByProductId(@Param("productId") Long productId);


    Optional<Customer> findByOrders_Id(Long orderId);

    @Query("SELECT c FROM Customer c JOIN c.orders o JOIN o.orderItems oi JOIN oi.product p JOIN p.categories cat WHERE cat.id = :categoryId")
    List<Customer> findCustomersByCategory(@Param("categoryId") Long categoryId);
    // It retrieves all Customers (c) who have Orders (o) with OrderItems (oi) that contain a Product (p)
    // which belongs to the Category (cat) with the provided ID (categoryId).


}
