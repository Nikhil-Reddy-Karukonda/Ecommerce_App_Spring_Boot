package com.ecommerce.springbootrestapi.service;

import com.ecommerce.springbootrestapi.exception.ResourceNotFoundException;
import com.ecommerce.springbootrestapi.model.Customer;
import com.ecommerce.springbootrestapi.repositoryDAO.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));
    }

    @Transactional(readOnly = true)
    public List<Customer> getCustomersByCity(String city) {
        return customerRepository.findByAddress_City(city);
    }

    @Transactional(readOnly = true)
    public List<Customer> getCustomersByOrderedProductId(Long productId) {
        return customerRepository.findByOrders_Products_Id(productId);
    }

    @Transactional(readOnly = true)
    public Optional<Customer> getCustomerByOrderId(Long orderId) {
        return customerRepository.findByOrders_Id(orderId);
    }

    @Transactional(readOnly = true)
    public List<Customer> getCustomersByOrderedProductInCategory(Long categoryId) {
        return customerRepository.findByOrders_OrderItems_Product_Category_Id(categoryId);
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer to be saved cannot be null");
        }
        return customerRepository.save(customer);
    }

    @Transactional
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }

}

