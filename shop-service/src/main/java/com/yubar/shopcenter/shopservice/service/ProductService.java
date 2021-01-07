package com.yubar.shopcenter.shopservice.service;

import com.yubar.shopcenter.productservice.entity.Attribute;
import com.yubar.shopcenter.productservice.entity.Product;
import com.yubar.shopcenter.productservice.entity.ProductType;
import com.yubar.shopcenter.productservice.entity.Value;
import com.yubar.shopcenter.productservice.exception.InvalidAttributeException;
import com.yubar.shopcenter.productservice.exception.ProductNotFoundException;
import com.yubar.shopcenter.productservice.model.ProductModel;
import com.yubar.shopcenter.productservice.model.ValueModel;
import com.yubar.shopcenter.productservice.repository.ProductRepository;
import com.yubar.shopcenter.productservice.repository.ValueRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ValueRepository valueRepository;
    
    private final ProductTypeService productTypeService;

    public ProductService(
            ProductRepository productRepository,
            ValueRepository valueRepository,
            ProductTypeService productTypeService
    ) {
        this.productRepository = productRepository;
        this.valueRepository = valueRepository;
        this.productTypeService = productTypeService;
    }

    Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    public ProductModel addProduct(String name, Long productTypeId) {

        ProductType productType = productTypeService.getProductType(productTypeId);
        Product product = new Product(name, productType);
        productRepository.save(product);
        return ProductModel.modelOf(product);

    }
    
    public ValueModel addValue(ValueModel valueModel) {

        Product product = getProduct(valueModel.getProductId());
        Attribute attribute = productTypeService.getAttribute(valueModel.getAttributeId());

        if (! attribute.getProductType().getId().equals(product.getProductType().getId()))
            throw new InvalidAttributeException();

        Value value = new Value(valueModel.getValue(), attribute, product);
        valueRepository.save(value);
        return ValueModel.modelOf(value);

    }
    
    
}
