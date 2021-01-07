package com.yubar.shopcenter.shopservice.service;

import com.yubar.shopcenter.shopservice.entity.Category;
import com.yubar.shopcenter.shopservice.exception.CategoryNotFoundException;
import com.yubar.shopcenter.shopservice.model.CategoryModel;
import com.yubar.shopcenter.shopservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    Category getCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public List<CategoryModel> getCategories() {

        return categoryRepository.findByParentIsNull()
                .stream()
                .map(CategoryModel::modelOf)
                .collect(Collectors.toList());

    }

    public CategoryModel getCategoryModel(Long id) {
        return CategoryModel.modelOf(getCategory(id));
    }

    public List<CategoryModel> getCategories(Long parentId) {

        Category parent = categoryRepository.findById(parentId)
                .orElseThrow(CategoryNotFoundException::new);

        return categoryRepository.findByParent(parent)
                .stream()
                .map(CategoryModel::modelOf)
                .collect(Collectors.toList());
        
    }

    public CategoryModel addCategory(String name) {

        Category category = new Category(name);
        categoryRepository.save(category);
        return CategoryModel.modelOf(category);
        
    }

    public CategoryModel addCategory(String name, Long parentId) {

        Category parent = categoryRepository.findById(parentId)
                .orElseThrow(CategoryNotFoundException::new);
        Category category = new Category(name, parent);
        categoryRepository.save(category);
        return CategoryModel.modelOf(category);
        
    }

}
