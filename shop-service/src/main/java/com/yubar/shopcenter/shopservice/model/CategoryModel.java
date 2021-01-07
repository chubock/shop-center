package com.yubar.shopcenter.shopservice.model;

import com.yubar.shopcenter.shopservice.entity.Category;
import org.springframework.beans.BeanUtils;

public class CategoryModel {

    private Long id;
    private String name;
    private Long parentId;
    private CategoryModel parentModel;

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

    public CategoryModel getParentModel() {
        return parentModel;
    }

    public void setParentModel(CategoryModel parentModel) {
        this.parentModel = parentModel;
    }

    public static CategoryModel modelOf(Category category) {
        CategoryModel model = new CategoryModel();
        BeanUtils.copyProperties(category, model);
        if (category.getParent() != null)
            model.setParentId(category.getParent().getId());
        return model;
    }
}
