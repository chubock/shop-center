package com.yubar.shopcenter.productservice.model;

import com.yubar.shopcenter.productservice.entity.Age;
import com.yubar.shopcenter.productservice.entity.ProductType;
import com.yubar.shopcenter.productservice.entity.Sex;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeModel {

    private Long id;
    private String name;
    private Long parentId;
    private ProductTypeModel parent;
    private Sex sex;
    private Age age;
    private List<AttributeModel> attributes = new ArrayList<AttributeModel>();
    private List<BrandModel> brands = new ArrayList<>();
    private List<ProductTypeModel> children = new ArrayList<>();

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    public ProductTypeModel getParent() {
        return parent;
    }

    public void setParent(ProductTypeModel parent) {
        this.parent = parent;
    }

    public List<AttributeModel> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributeModel> attributes) {
        this.attributes = attributes;
    }

    public List<BrandModel> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandModel> brands) {
        this.brands = brands;
    }

    public List<ProductTypeModel> getChildren() {
        return children;
    }

    public void setChildren(List<ProductTypeModel> children) {
        this.children = children;
    }

    public static ProductTypeModel modelOf(ProductType productType) {
        ProductTypeModel model = new ProductTypeModel();
        BeanUtils.copyProperties(productType, model);
        if (productType.getParent() != null)
            model.setParentId(productType.getParent().getId());
        return model;
    }
}
