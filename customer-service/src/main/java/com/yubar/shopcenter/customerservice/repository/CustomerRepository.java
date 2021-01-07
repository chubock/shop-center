package com.yubar.shopcenter.customerservice.repository;

import com.yubar.shopcenter.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
