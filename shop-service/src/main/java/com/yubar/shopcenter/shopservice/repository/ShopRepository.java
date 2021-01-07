package com.yubar.shopcenter.shopservice.repository;

import com.yubar.shopcenter.shopservice.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    @Query("select s from Shop s join fetch s.categories where s.id = :id")
    Optional<Shop> findByIdWithCategories(@Param("id") Long id);

    @Query("select s from Shop s join fetch s.products where s.id = :id")
    Optional<Shop> findByIdWithProducts(@Param("id") Long id);

}
