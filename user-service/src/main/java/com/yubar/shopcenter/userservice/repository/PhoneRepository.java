package com.yubar.shopcenter.userservice.repository;

import com.yubar.shopcenter.userservice.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneRepository extends JpaRepository<Phone, String> {

    Optional<Phone> findTopByNumberOrderByCreatedDateDesc(String phoneNumber);

}
