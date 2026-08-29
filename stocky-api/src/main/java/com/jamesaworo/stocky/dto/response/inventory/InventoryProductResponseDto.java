package com.jamesaworo.stocky.dto.response.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class InventoryProductResponseDto {
    private final Long id;
    private final String sku;
    private final String name;
    private final BigDecimal unitPrice;
    private final Integer quantityOnHand;
    private final InventoryCategoryResponseDto category;
}
