package com.yubar.shopcenter.productservice.service;

import com.yubar.shopcenter.productservice.entity.Attribute;
import com.yubar.shopcenter.productservice.entity.Brand;
import com.yubar.shopcenter.productservice.entity.Choice;
import com.yubar.shopcenter.productservice.entity.ProductType;
import com.yubar.shopcenter.productservice.model.AttributeModel;
import com.yubar.shopcenter.productservice.model.BrandModel;
import com.yubar.shopcenter.productservice.model.ChoiceModel;
import com.yubar.shopcenter.productservice.model.ProductTypeModel;
import com.yubar.shopcenter.productservice.repository.AttributeRepository;
import com.yubar.shopcenter.productservice.repository.BrandRepository;
import com.yubar.shopcenter.productservice.repository.ChoiceRepository;
import com.yubar.shopcenter.productservice.repository.ProductTypeRepository;
import com.yubar.shopcenter.util.ExceptionUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductTypeService {

    private final AttributeRepository attributeRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ChoiceRepository choiceRepository;
    private final BrandRepository brandRepository;

    public ProductTypeService(
            AttributeRepository attributeRepository,
            ProductTypeRepository productTypeRepository,
            ChoiceRepository choiceRepository,
            BrandRepository brandRepository
    ) {
        this.attributeRepository = attributeRepository;
        this.productTypeRepository = productTypeRepository;
        this.choiceRepository = choiceRepository;
        this.brandRepository = brandRepository;
    }

    public ProductTypeModel getProductType(Long id) {

        return productTypeRepository.findById(id)
                .map(ProductTypeModel::modelOf)
                .orElseThrow(ExceptionUtils::notFound);

    }

    public List<ProductTypeModel> getProductTypes() {

        return productTypeRepository.findByParentIsNull()
                .stream()
                .map(ProductTypeModel::modelOf)
                .collect(Collectors.toList());

    }

    public List<ProductTypeModel> getProductTypeChildren(Long id) {

        ProductType parent = productTypeRepository.findById(id)
                .orElseThrow(ExceptionUtils::notFound);

        return productTypeRepository
                .findByParent(parent)
                .stream()
                .map(ProductTypeModel::modelOf)
                .collect(Collectors.toList());

    }

    public List<AttributeModel> getProductTypeAttributes(Long id) {

        ProductType productType = productTypeRepository.findById(id)
                .orElseThrow(ExceptionUtils::notFound);

        return attributeRepository.findByProductType(productType)
                .stream()
                .map(AttributeModel::modelOf)
                .collect(Collectors.toList());

    }

    public List<BrandModel> getProductTypeBrands(Long id) {

        ProductType productType = productTypeRepository.findById(id)
                .orElseThrow(ExceptionUtils::notFound);

        return brandRepository.findByProductType(productType)
                .stream()
                .map(BrandModel::modelOf)
                .collect(Collectors.toList());

    }

    public AttributeModel getAttribute(Long id) {

        return attributeRepository.findById(id)
                .map(AttributeModel::modelOf)
                .orElseThrow(ExceptionUtils::notFound);

    }

    public List<ChoiceModel> getAttributeChoices(Long id) {

        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(ExceptionUtils::notFound);

        return choiceRepository.findByAttribute(attribute)
                .stream()
                .map(ChoiceModel::modelOf)
                .collect(Collectors.toList());

    }

    public ProductTypeModel addProductType(ProductTypeModel model) {

        ProductType parent = null;
        if (model.getParentId() != null)
            parent = productTypeRepository.findById(model.getParentId())
                    .orElseThrow(ExceptionUtils::badRequest);

        ProductType productType =
                parent != null ? new ProductType(model.getName(), parent) : new ProductType(model.getName());

        if (parent == null) {
            productType.setSex(model.getSex());
            productType.setAge(model.getAge());
        }

        productTypeRepository.save(productType);
        return ProductTypeModel.modelOf(productType);
        
    }
    
    public AttributeModel addAttribute(AttributeModel model) {

        ProductType productType = productTypeRepository.findById(model.getProductTypeId())
                .orElseThrow(ExceptionUtils::badRequest);

        Attribute attribute = new Attribute(
                model.getName(),
                model.getType(),
                model.getCollection(),
                productType
        );

        attribute.setMandatory(model.getMandatory());
        
        attributeRepository.save(attribute);
        
        return AttributeModel.modelOf(attribute);

    }

    public BrandModel addBrand(BrandModel model) {

        ProductType productType = productTypeRepository.findById(model.getProductTypeId())
                .orElseThrow(ExceptionUtils::badRequest);

        Brand brand = new Brand(model.getName(), productType);

        brandRepository.save(brand);

        return BrandModel.modelOf(brand);

    }

    public ChoiceModel addChoice(ChoiceModel model) {

        Attribute attribute = attributeRepository.findById(model.getAttributeId())
                .orElseThrow(ExceptionUtils::badRequest);

        Choice choice = new Choice(model.getValue(), attribute);
        choiceRepository.save(choice);

        return ChoiceModel.modelOf(choice);

    }
    
    

}
