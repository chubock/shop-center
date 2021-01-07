package com.yubar.shopcenter.authservice.repository;

import com.yubar.shopcenter.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(@Param("username") String username);

}