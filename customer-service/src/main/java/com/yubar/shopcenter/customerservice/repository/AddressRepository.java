package com.yubar.shopcenter.customerservice.repository;

import com.yubar.shopcenter.customerservice.entity.Address;
import com.yubar.shopcenter.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, String> {

    List<Address> findByCustomer(Customer customer);

}
