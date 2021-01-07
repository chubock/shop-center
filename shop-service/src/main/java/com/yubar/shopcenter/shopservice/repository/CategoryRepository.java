package com.yubar.shopcenter.shopservice.repository;

import com.yubar.shopcenter.shopservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentIsNull();
    List<Category> findByParent(Category category);

}
