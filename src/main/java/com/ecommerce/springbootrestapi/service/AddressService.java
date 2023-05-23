package com.ecommerce.springbootrestapi.service;

import com.ecommerce.springbootrestapi.model.Address;
import com.ecommerce.springbootrestapi.model.Customer;
import com.ecommerce.springbootrestapi.repositoryDAO.AddressRepository;
import com.ecommerce.springbootrestapi.repositoryDAO.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecommerce.springbootrestapi.exception.ResourceNotFoundException;
import com.ecommerce.springbootrestapi.exception.GlobalExceptionHandler;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CustomerRepository customerRepository;

    public AddressService(AddressRepository addressRepository, CustomerRepository customerRepository) {

        this.addressRepository = addressRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    public List<Address> getAddressesByCustomerId(Long customerId) {
        List<Address> addresses = addressRepository.findByCustomer_Id(customerId);
        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException("No addresses found for customer with id: " + customerId);
        }
        return addresses;
    }

    @Transactional
    public Address saveAddress(Long customerId, Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Cannot save null address");
        }
        // Fetch the customer and assign it to the address
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find customer with id: " + customerId));
        address.setCustomer(customer);
        return addressRepository.save(address);
    }

    @Transactional
    public void deleteAddress(Long customerId, Long id) {
        if (!addressRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete non-existing address with id: " + id);
        }
        // Fetch the customer and the address
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find customer with id: " + customerId));
        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find address with id: " + id));
        // Check if the customer owns the address
        if (!address.getCustomer().equals(customer)) {
            throw new IllegalArgumentException("Customer with id: " + customerId + " does not own the address with id: " + id);
        }
        addressRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Address> getAddressesByCity(String city) {
        List<Address> addresses = addressRepository.findByCity(city);
        if (addresses.isEmpty()) {
            throw new ResourceNotFoundException("No addresses found in city: " + city);
        }
        return addresses;
    }

}
