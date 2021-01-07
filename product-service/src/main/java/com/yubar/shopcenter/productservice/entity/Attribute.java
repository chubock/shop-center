package com.yubar.shopcenter.productservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Attribute implements Serializable {

    private Long id;
    private String name;
    private Boolean mandatory = false;
    private Type type = Type.STRING;
    private Boolean collection = false;
    private ProductType productType;
    private List<Choice> choices = new ArrayList<>();

    Attribute() {
    }

    public Attribute(String name, ProductType productType) {
        this.name = name;
        this.productType = productType;
    }

    public Attribute(String name, Type type, ProductType productType) {
        this.name = name;
        this.type = type;
        this.productType = productType;
    }

    public Attribute(String name, Type type, Boolean collection, ProductType productType) {
        this.name = name;
        this.type = type;
        this.collection = collection;
        this.productType = productType;
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
    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    @NotEmpty
    @Enumerated(EnumType.ORDINAL)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @NotNull
    public Boolean getCollection() {
        return collection;
    }

    public void setCollection(Boolean collection) {
        this.collection = collection;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public ProductType getProductType() {
        return productType;
    }

    private void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute")
    public List<Choice> getChoices() {
        return choices;
    }

    private void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public enum Type {
        STRING, NUMERAL, BOOLEAN, DATE, TIME, DATE_TIME, SINGLE_CHOICE, MULTIPLE_CHOICE
    }
}
