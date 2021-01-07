package com.yubar.shopcenter.shopservice.repository;

import com.yubar.shopcenter.shopservice.entity.Product;
import com.yubar.shopcenter.shopservice.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Boolean existsByProductIdAndShop(Long productId, Shop shop);

}
