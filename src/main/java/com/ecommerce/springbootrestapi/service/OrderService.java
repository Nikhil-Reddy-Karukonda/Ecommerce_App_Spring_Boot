package com.ecommerce.springbootrestapi.service;

import com.ecommerce.springbootrestapi.exception.ResourceNotFoundException;
import com.ecommerce.springbootrestapi.model.Order;
import com.ecommerce.springbootrestapi.repositoryDAO.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }

    @Transactional(readOnly = true)
    public List<Object[]> countOrdersByEachCustomer() {
        return orderRepository.countOrdersByEachCustomer();
    }

    @Transactional
    public Order saveOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order to be saved cannot be null");
        }
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}

