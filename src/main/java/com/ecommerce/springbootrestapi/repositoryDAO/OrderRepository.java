package com.ecommerce.springbootrestapi.repositoryDAO;


import com.ecommerce.springbootrestapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Find all orders by a specific customer.
    List<Order> findByCustomer_Id(Long customerId);

    // Get count of orders by each customer
    @Query("SELECT o.customer.id, COUNT(o.id) FROM Order o GROUP BY o.customer.id")
    List<Object[]> countOrdersByEachCustomer();

    // Update Order Status -> following Order State Machine

}

// Checkout Cart, Payment API