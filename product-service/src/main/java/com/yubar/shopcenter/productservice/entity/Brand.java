package com.yubar.shopcenter.productservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Brand implements Serializable {

    private Long id;
    private String name;
    private ProductType productType;

    Brand() {
    }

    public Brand(String name, ProductType productType) {
        this.name = name;
        this.productType = productType;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public ProductType getProductType() {
        return productType;
    }

    private void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
