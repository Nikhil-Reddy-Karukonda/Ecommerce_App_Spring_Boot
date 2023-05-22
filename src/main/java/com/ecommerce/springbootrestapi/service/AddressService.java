package com.ecommerce.springbootrestapi.service;

import com.ecommerce.springbootrestapi.model.Address;
import com.ecommerce.springbootrestapi.repositoryDAO.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ecommerce.springbootrestapi.exception.ResourceNotFoundException;
import com.ecommerce.springbootrestapi.exception.GlobalExceptionHandler;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
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
    public Address saveAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Cannot save null address");
        }
        return addressRepository.save(address);
    }

    @Transactional
    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete non-existing address with id: " + id);
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
