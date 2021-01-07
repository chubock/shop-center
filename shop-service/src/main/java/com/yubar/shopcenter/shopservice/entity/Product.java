package com.yubar.shopcenter.shopservice.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    private Long id;
    private String name;
    private Long productTypeId;
    private List<Property> properties = new ArrayList<Property>();
    private Sex sex;
    private Shop shop;
    private Long price;
    private Integer quantity;
    private Integer discount = 0;

    Product() {
    }

    public Product(String name, Long productTypeId, Shop shop, Long price, Integer quantity) {
        this.name = name;
        this.productTypeId = productTypeId;
        this.shop = shop;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String name, Long productTypeId, Sex sex, Shop shop, Long price, Integer quantity) {
        this.name = name;
        this.productTypeId = productTypeId;
        this.sex = sex;
        this.shop = shop;
        this.price = price;
        this.quantity = quantity;
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
    public Long getProductTypeId() {
        return productTypeId;
    }

    private void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public List<Property> getProperties() {
        return properties;
    }

    private void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Enumerated
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Shop getShop() {
        return shop;
    }

    private void setShop(Shop shop) {
        this.shop = shop;
    }

    @Min(1)
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Min(0)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void decrementQuantity() {
        quantity -= 1;
    }

    public void decrementQuantity(int count) {
        quantity -= count;
    }

    public void incrementQuantity(int count) {
        quantity += count;
    }

    @Min(0)
    @Max(99)
    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public void removeDiscount() {
        this.discount = 0;
    }
}