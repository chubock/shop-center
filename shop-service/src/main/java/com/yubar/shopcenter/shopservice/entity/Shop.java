package com.yubar.shopcenter.shopservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Shop implements Serializable {

    private Long id;
    private String name;
    private String phoneNumber;
    private String address;
    private Location location;
    private List<Product> products = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    Shop() {
    }

    public Shop(String name, String phoneNumber, String address, Location location) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.location = location;
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

    @NotEmpty
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NotEmpty
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotNull
    @Embedded
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY)
    public List<Product> getProducts() {
        return products;
    }

    private void setProducts(List<Product> products) {
        this.products = products;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    public List<Category> getCategories() {
        return categories;
    }

    private void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
