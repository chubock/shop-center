package com.yubar.shopcenter.productservice.repository;

import com.yubar.shopcenter.productservice.entity.Attribute;
import com.yubar.shopcenter.productservice.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {

    List<Choice> findByAttribute(Attribute attribute);

}
