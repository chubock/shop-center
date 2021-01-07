package com.yubar.shopcenter.productservice.model;

import com.yubar.shopcenter.productservice.entity.Choice;
import org.springframework.beans.BeanUtils;

public class ChoiceModel {

    private Long id;
    private String value;
    private Long attributeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public static ChoiceModel modelOf(Choice choice) {
        ChoiceModel model = new ChoiceModel();
        BeanUtils.copyProperties(choice, model);
        if (choice.getAttribute() != null)
            model.setAttributeId(choice.getAttribute().getId());
        return model;
    }
}
