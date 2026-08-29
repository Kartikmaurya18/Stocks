package com.jamesaworo.stocky.dto.request.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductBasicRequestDto {
    private Long id;
    private ProductCategoryRequestDto productCategory;
    private ProductUnitOfMeasureRequestDto unitOfMeasure;
    private ProductStatusRequestDto status;
    private Boolean isActive;
    private Boolean useQuantity;
    private Boolean isService;
    private Integer minAgeLimit;
    private String productName;
    private String brandName;
    private String sku;
    private String barcode;
    private String description;
    private Integer lowStockPoint;
    private List<ProductTaxRequestDto> taxes;
    private Integer quantity;
    private Long productId;
}
