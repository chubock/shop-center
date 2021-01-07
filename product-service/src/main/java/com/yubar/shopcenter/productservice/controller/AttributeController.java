package com.yubar.shopcenter.productservice.controller;

import com.yubar.shopcenter.productservice.model.AttributeModel;
import com.yubar.shopcenter.productservice.model.ChoiceModel;
import com.yubar.shopcenter.productservice.service.ProductTypeService;
import com.yubar.shopcenter.security.IsAdmin;
import com.yubar.shopcenter.security.IsUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api-prefix}/attributes")
public class AttributeController {

    private final ProductTypeService productTypeService;

    public AttributeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @IsUser
    @GetMapping("/{id}")
    public AttributeModel getAttribute(@PathVariable Long id) {
        return productTypeService.getAttribute(id);
    }

    @IsUser
    @GetMapping("/{id}/choices")
    public List<ChoiceModel> getChoices(@PathVariable Long id) {
        return productTypeService.getAttributeChoices(id);
    }

    @IsAdmin
    @PostMapping("/{id}/choices")
    public ChoiceModel addChoice(@PathVariable Long id, @Validated @RequestBody ChoiceModel model) {
        model.setAttributeId(id);
        return productTypeService.addChoice(model);
    }

}
