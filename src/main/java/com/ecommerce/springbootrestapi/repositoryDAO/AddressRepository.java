package com.ecommerce.springbootrestapi.repositoryDAO;

import com.ecommerce.springbootrestapi.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    // Find all addresses associated with a particular customer.
    List<Address> findByCustomer_Id(Long customerId);

    // Find all addresses within a particular city.
    List<Address> findByCity(String city);

}