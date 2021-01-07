package com.yubar.shopcenter.productservice.model;

import com.yubar.shopcenter.productservice.entity.Brand;
import org.springframework.beans.BeanUtils;

public class BrandModel {

    private Long id;
    private String name;
    private Long productTypeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public static BrandModel modelOf(Brand brand) {
        BrandModel model = new BrandModel();
        BeanUtils.copyProperties(brand, model);
        if (brand.getProductType() != null)
            model.setProductTypeId(brand.getProductType().getId());
        return model;
    }
}
