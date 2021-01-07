package com.yubar.shopcenter.shopservice.controller;

import com.yubar.shopcenter.shopservice.model.CategoryModel;
import com.yubar.shopcenter.shopservice.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryModel> getCategories() {

        return categoryService.getCategories();

    }

    @GetMapping("/{id}")
    public CategoryModel getCategory(@PathVariable Long id) {
        return categoryService.getCategoryModel(id);
    }

    @GetMapping("/{id}/categories")
    public List<CategoryModel> getCategories(@PathVariable Long id) {
        return categoryService.getCategories(id);
    }

    @PostMapping
    public CategoryModel addCategory(@RequestBody CategoryModel model) {
        return categoryService.addCategory(model.getName());
    }

    @PostMapping("/{id}/categories")
    public CategoryModel addCategory(@RequestBody CategoryModel model, @PathVariable Long id) {
        return categoryService.addCategory(model.getName(), id);
    }


}
