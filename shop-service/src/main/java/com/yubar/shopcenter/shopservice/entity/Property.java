package com.yubar.shopcenter.shopservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Property implements Serializable {

    private Long id;
    private Long attributeId;
    private String name;
    private String value;
    private Product product;

    Property() {
    }

    public Property(String name, String value, Long attributeId, Product product) {
        this.name = name;
        this.value = value;
        this.attributeId = attributeId;
        this.product = product;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public Long getAttributeId() {
        return attributeId;
    }

    private void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    @NotEmpty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Product getProduct() {
        return product;
    }

    private void setProduct(Product product) {
        this.product = product;
    }
}
