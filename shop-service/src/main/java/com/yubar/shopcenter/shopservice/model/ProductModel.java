package com.yubar.shopcenter.shopservice.model;

import com.yubar.shopcenter.shopservice.entity.Product;
import org.springframework.beans.BeanUtils;

public class ProductModel {

    private Long id;
    private Long productId;
    private Long price;
    private Integer quantity;
    private Long shopId;
    private ShopModel shopModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public ShopModel getShopModel() {
        return shopModel;
    }

    public void setShopModel(ShopModel shopModel) {
        this.shopModel = shopModel;
    }

    public static ProductModel modelOf(Product product) {
        ProductModel model = new ProductModel();
        BeanUtils.copyProperties(product, model);
        if (product.getShop() != null)
            model.setShopId(product.getShop().getId());
        return model;
    }
}
