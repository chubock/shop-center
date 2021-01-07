package com.yubar.shopcenter.productservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ProductType implements Serializable {

    private Long id;
    private String name;
    private ProductType parent;
    private Sex sex;
    private Age age;
    private List<Attribute> attributes = new ArrayList<Attribute>();
    private List<Brand> brands = new ArrayList<>();
    private List<ProductType> children = new ArrayList<>();

    ProductType() {
    }

    public ProductType(String name) {
        this.name = name;
    }

    public ProductType(String name, ProductType parent) {
        this.name = name;
        this.parent = parent;
        this.sex = parent.getSex();
        this.age = parent.getAge();
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

    @ManyToOne(fetch = FetchType.LAZY)
    public ProductType getParent() {
        return parent;
    }

    public void setParent(ProductType parent) {
        this.parent = parent;
    }

    @Enumerated
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Enumerated
    public Age getAge() {
        return age;
    }

    public void setAge(Age age) {
        this.age = age;
    }

    @OneToMany(mappedBy = "productType", fetch = FetchType.LAZY)
    public List<Attribute> getAttributes() {
        return attributes;
    }

    private void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productType")
    public List<Brand> getBrands() {
        return brands;
    }

    private void setBrands(List<Brand> brands) {
        this.brands = brands;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    public List<ProductType> getChildren() {
        return children;
    }

    private void setChildren(List<ProductType> children) {
        this.children = children;
    }
}
