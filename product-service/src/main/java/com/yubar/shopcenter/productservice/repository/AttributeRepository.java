package com.yubar.shopcenter.productservice.repository;

import com.yubar.shopcenter.productservice.entity.Attribute;
import com.yubar.shopcenter.productservice.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {

    List<Attribute> findByProductType(ProductType productType);

}
