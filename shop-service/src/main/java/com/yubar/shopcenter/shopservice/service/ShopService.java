package com.yubar.shopcenter.shopservice.service;

import com.yubar.shopcenter.shopservice.entity.Category;
import com.yubar.shopcenter.shopservice.entity.Product;
import com.yubar.shopcenter.shopservice.entity.Shop;
import com.yubar.shopcenter.shopservice.exception.DuplicateProductException;
import com.yubar.shopcenter.shopservice.exception.ShopNotFoundException;
import com.yubar.shopcenter.shopservice.model.CategoryModel;
import com.yubar.shopcenter.shopservice.model.ProductModel;
import com.yubar.shopcenter.shopservice.model.ShopModel;
import com.yubar.shopcenter.shopservice.repository.ProductRepository;
import com.yubar.shopcenter.shopservice.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    public ShopService(
            ShopRepository shopRepository,
            ProductRepository productRepository,
            CategoryService categoryService
    ) {
        this.shopRepository = shopRepository;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    Shop getShop(Long id) {
        return shopRepository.findByIdWithCategories(id)
                .orElseThrow(ShopNotFoundException::new);
    }

    public List<ShopModel> getShops() {
        return shopRepository.findAll().stream().map(ShopModel::modelOf).collect(Collectors.toList());
    }

    public List<CategoryModel> getShopCategories(Long shopId) {

        Shop shop = shopRepository.findByIdWithCategories(shopId)
                .orElseThrow(ShopNotFoundException::new);

        return shop.getCategories().stream().map(CategoryModel::modelOf).collect(Collectors.toList());
    }

    public List<ProductModel> getShopProducts(Long shopId) {

        Shop shop = shopRepository.findByIdWithProducts(shopId)
                .orElseThrow(ShopNotFoundException::new);

        return shop.getProducts().stream().map(ProductModel::modelOf).collect(Collectors.toList());

    }

    public ShopModel addShop(ShopModel model) {

        Shop shop = new Shop(model.getMobile(), model.getName(), model.getLocation());
        shop.setAddress(model.getAddress());
        shop.setPhoneNumber(model.getPhoneNumber());

        shopRepository.save(shop);

        return ShopModel.modelOf(shop);

    }

    public ShopModel addCategory(Long shopId, Long categoryId) {

        Category category = categoryService.getCategory(categoryId);
        Shop shop = shopRepository.findByIdWithCategories(shopId)
                .orElseThrow(ShopNotFoundException::new);

        Boolean duplicate = shop.getCategories()
                .stream()
                .anyMatch(c -> c.getId().equals(categoryId));

        if (! duplicate)
            shop.getCategories().add(category);

        ShopModel model = ShopModel.modelOf(shop);

        model.setCategoryModels(shop.getCategories().stream().map(CategoryModel::modelOf).collect(Collectors.toList()));

        return model;

    }

    public ProductModel addProduct(Long shopId, ProductModel model) {

        Shop shop = shopRepository.findByIdWithProducts(shopId)
                .orElseThrow(ShopNotFoundException::new);

        if (productRepository.existsByProductIdAndShop(model.getProductId(), shop))
            throw new DuplicateProductException();

        Product product = new Product(model.getProductId(), model.getPrice(), model.getQuantity(), shop);
        productRepository.save(product);
        return ProductModel.modelOf(product);

    }


}
