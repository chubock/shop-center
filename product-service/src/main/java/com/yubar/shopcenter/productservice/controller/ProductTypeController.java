package com.yubar.shopcenter.productservice.controller;

import com.yubar.shopcenter.productservice.model.AttributeModel;
import com.yubar.shopcenter.productservice.model.BrandModel;
import com.yubar.shopcenter.productservice.model.ProductTypeModel;
import com.yubar.shopcenter.productservice.service.ProductTypeService;
import com.yubar.shopcenter.security.IsAdmin;
import com.yubar.shopcenter.security.IsUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-prefix}/product-types")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @IsUser
    @GetMapping
    public List<ProductTypeModel> getProductTypes() {
        return productTypeService.getProductTypes();
    }

    @IsUser
    @GetMapping("/{id}/children")
    public List<ProductTypeModel> getProductTypes(@PathVariable Long id) {
        return productTypeService.getProductTypeChildren(id);
    }

    @IsUser
    @GetMapping("/{id}/attributes")
    public List<AttributeModel> getProductTypeAttributes(@PathVariable Long id) {
        return productTypeService.getProductTypeAttributes(id);
    }

    @IsUser
    @GetMapping("/{id}/brands")
    public List<BrandModel> getProductTypeBrands(@PathVariable Long id) {
        return productTypeService.getProductTypeBrands(id);
    }

    @IsAdmin
    @PostMapping
    public ProductTypeModel addProductType(@Validated @RequestBody ProductTypeModel model) {
        return productTypeService.addProductType(model);
    }

    @IsAdmin
    @PostMapping("/{id}/children")
    public ProductTypeModel addProductType(@PathVariable Long id, @Validated @RequestBody ProductTypeModel model) {
        model.setParentId(id);
        return productTypeService.addProductType(model);
    }

    @IsAdmin
    @PostMapping("/{id}/attributes")
    public AttributeModel addAttribute(@PathVariable Long id, @Validated @RequestBody AttributeModel model) {
        model.setProductTypeId(id);
        return productTypeService.addAttribute(model);
    }

    @IsAdmin
    @PostMapping("/{id}/brands")
    public BrandModel addBrand(@PathVariable Long id, @Validated @RequestBody BrandModel model) {
        model.setProductTypeId(id);
        return productTypeService.addBrand(model);
    }

}
