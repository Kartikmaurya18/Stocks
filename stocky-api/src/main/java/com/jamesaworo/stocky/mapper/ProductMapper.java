package com.jamesaworo.stocky.mapper;

import com.jamesaworo.stocky.dto.request.product.ProductCategoryRequestDto;
import com.jamesaworo.stocky.entity.product.ProductCategory;

public class ProductMapper {
    public static ProductCategoryRequestDto toProductCategoryDto(ProductCategory model) {
        if (model == null) return null;
        ProductCategoryRequestDto dto = new ProductCategoryRequestDto();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());
        dto.setIsActiveStatus(model.getIsActiveStatus());
        if (model.getCreatedAt() != null) {
            dto.setCreatedAt(model.getCreatedAt().toString());
        }
        if (model.getParent() != null && !model.getParent().getId().equals(model.getId())) {
            dto.setParent(toProductCategoryDto(model.getParent()));
        }
        return dto;
    }

    public static ProductCategory toProductCategoryModel(ProductCategoryRequestDto dto) {
        if (dto == null) return null;
        ProductCategory model = new ProductCategory();
        model.setId(dto.getId());
        model.setTitle(dto.getTitle());
        model.setDescription(dto.getDescription());
        model.setIsActiveStatus(dto.getIsActiveStatus());
        if (dto.getParent() != null) {
            ProductCategory parent = new ProductCategory();
            parent.setId(dto.getParent().getId());
            model.setParent(parent);
        }
        return model;
    }
}
