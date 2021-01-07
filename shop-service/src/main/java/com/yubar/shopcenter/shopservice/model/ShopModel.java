package com.yubar.shopcenter.shopservice.model;

import com.yubar.shopcenter.shopservice.entity.Location;
import com.yubar.shopcenter.shopservice.entity.Shop;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ShopModel {

    private Long id;
    private String mobile;
    private String name;
    private String address;
    private Location location;
    private String phoneNumber;
    private List<ProductModel> productModels = new ArrayList<>();
    private List<CategoryModel> categoryModels = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(List<ProductModel> productModels) {
        this.productModels = productModels;
    }

    public List<CategoryModel> getCategoryModels() {
        return categoryModels;
    }

    public void setCategoryModels(List<CategoryModel> categoryModels) {
        this.categoryModels = categoryModels;
    }

    public static ShopModel modelOf(Shop shop) {
        ShopModel model = new ShopModel();
        BeanUtils.copyProperties(shop, model);
        return model;
    }
}
