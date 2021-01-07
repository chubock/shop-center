package com.yubar.shopcenter.productservice.repository;

import com.yubar.shopcenter.productservice.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {

    List<ProductType> findByParent(ProductType productType);
    List<ProductType> findByParentIsNull();

}
