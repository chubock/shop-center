package com.yubar.shopcenter.shopservice.controller;

import com.yubar.shopcenter.shopservice.model.CategoryModel;
import com.yubar.shopcenter.shopservice.model.ProductModel;
import com.yubar.shopcenter.shopservice.model.ShopModel;
import com.yubar.shopcenter.shopservice.service.ShopService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public List<ShopModel> getShops() {
        return shopService.getShops();
    }

    @GetMapping("/{id}/categories")
    public List<CategoryModel> getShopCategories(@PathVariable Long id) {
        return shopService.getShopCategories(id);
    }

    @GetMapping("/{id}/products")
    public List<ProductModel> getShopProducts(@PathVariable Long id) {
        return shopService.getShopProducts(id);
    }

    @PostMapping
    public ShopModel addShop(@RequestBody ShopModel model) {
        return shopService.addShop(model);
    }

    @PutMapping("/{shopId}/categories/{categoryId}")
    public ShopModel addCategory(@PathVariable Long shopId, @PathVariable Long categoryId) {
        return shopService.addCategory(shopId, categoryId);
    }

    @PostMapping("/{id}/products")
    public ProductModel addProduct(@PathVariable Long id, @RequestBody ProductModel model) {
        return shopService.addProduct(id, model);
    }


}
