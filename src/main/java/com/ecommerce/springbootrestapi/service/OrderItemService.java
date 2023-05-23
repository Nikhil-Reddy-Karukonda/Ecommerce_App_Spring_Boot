package com.ecommerce.springbootrestapi.service;

import com.ecommerce.springbootrestapi.exception.ResourceNotFoundException;
import com.ecommerce.springbootrestapi.model.OrderItem;
import com.ecommerce.springbootrestapi.repositoryDAO.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional(readOnly = true)
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Item not found with id: " + id));
    }

//    @Transactional(readOnly = true)
//    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
//        return orderItemRepository.findByOrder_Id(orderId);
//    }

    @Transactional(readOnly = true)
    public Integer getTotalQuantityOrderedOfProduct(Long productId) {
        Integer totalQuantity = orderItemRepository.getTotalQuantityOrderedOfProduct(productId);
        if (totalQuantity == null) {
            throw new ResourceNotFoundException("No order items found with product id: " + productId);
        }
        return totalQuantity;
    }

    @Transactional(readOnly = true)
    public List<OrderItem> findItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findItemsByOrderId(orderId);
        if (orderItems.isEmpty()) {
            throw new ResourceNotFoundException("No order items found for order id: " + orderId);
        }
        return orderItems;
    }

    @Transactional
    public OrderItem saveOrderItem(OrderItem orderItem) {
        if (orderItem == null) {
            throw new IllegalArgumentException("Order item to be saved cannot be null");
        }
        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public void deleteOrderItem(Long id) {
        if (!orderItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order Item not found with id: " + id);
        }
        orderItemRepository.deleteById(id);
    }
}

