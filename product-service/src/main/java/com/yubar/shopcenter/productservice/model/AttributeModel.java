package com.yubar.shopcenter.productservice.model;

import com.yubar.shopcenter.productservice.entity.Attribute;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class AttributeModel {

    private Long id;
    private String name;
    private Boolean mandatory = false;
    private Attribute.Type type = Attribute.Type.STRING;
    private Boolean collection = false;
    private Long productTypeId;
    private ProductTypeModel productType;
    private List<ChoiceModel> choices = new ArrayList<>();

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

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public Attribute.Type getType() {
        return type;
    }

    public void setType(Attribute.Type type) {
        this.type = type;
    }

    public Boolean getCollection() {
        return collection;
    }

    private void setCollection(Boolean collection) {
        this.collection = collection;
    }

    public Long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public ProductTypeModel getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeModel productType) {
        this.productType = productType;
    }

    public List<ChoiceModel> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceModel> choices) {
        this.choices = choices;
    }

    public static AttributeModel modelOf(Attribute attribute) {
        AttributeModel model = new AttributeModel();
        BeanUtils.copyProperties(attribute, model);
        if (attribute.getProductType() != null)
            model.setProductTypeId(attribute.getProductType().getId());
        return model;
    }
}
