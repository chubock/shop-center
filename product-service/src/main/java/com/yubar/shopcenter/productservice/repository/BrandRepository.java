package com.yubar.shopcenter.productservice.repository;

import com.yubar.shopcenter.productservice.entity.Brand;
import com.yubar.shopcenter.productservice.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findByProductType(ProductType productType);

}
