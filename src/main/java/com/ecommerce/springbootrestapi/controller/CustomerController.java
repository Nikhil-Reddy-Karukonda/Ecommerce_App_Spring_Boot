package com.ecommerce.springbootrestapi.controller;

import com.ecommerce.springbootrestapi.exception.ResourceNotFoundException;
import com.ecommerce.springbootrestapi.model.Customer;
import com.ecommerce.springbootrestapi.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        try {
            Customer customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        try {
            Customer customer = customerService.getCustomerByEmail(email);
            return ResponseEntity.ok(customer);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Customer>> getCustomersByCity(@PathVariable String city) {
        return ResponseEntity.ok(customerService.getCustomersByCity(city));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Customer>> getCustomersByOrderedProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(customerService.getCustomersByOrderedProductId(productId));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Customer> getCustomerByOrderId(@PathVariable Long orderId) {
        Customer customer = customerService.getCustomerByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with order id: " + orderId));
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Customer>> getCustomersByOrderedProductInCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(customerService.getCustomersByOrderedProductInCategory(categoryId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(customer));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
