package com.yubar.shopcenter.productservice.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Choice implements Serializable {

    private Long id;
    private String value;
    private Attribute attribute;

    Choice() {
    }

    public Choice(String value, Attribute attribute) {
        this.value = value;
        this.attribute = attribute;
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
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }
}
