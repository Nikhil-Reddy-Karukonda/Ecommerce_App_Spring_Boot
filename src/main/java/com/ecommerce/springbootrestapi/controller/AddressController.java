package com.ecommerce.springbootrestapi.controller;

import com.ecommerce.springbootrestapi.model.Address;
import com.ecommerce.springbootrestapi.service.AddressService;
import com.ecommerce.springbootrestapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/{customerId}/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAddressesByCustomerId(@PathVariable Long customerId) {
        try {
            List<Address> addresses = addressService.getAddressesByCustomerId(customerId);
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Address> saveAddress(@PathVariable Long customerId, @RequestBody Address address) {
        try {
            Address savedAddress = addressService.saveAddress(customerId, address);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long customerId, @PathVariable Long id) {
        try {
            addressService.deleteAddress(customerId, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Address>> getAddressesByCity(@PathVariable String city, @PathVariable String customerId) {
        try {
            List<Address> addresses = addressService.getAddressesByCity(city);
            return new ResponseEntity<>(addresses, HttpStatus.OK);
        } catch (ResourceNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
