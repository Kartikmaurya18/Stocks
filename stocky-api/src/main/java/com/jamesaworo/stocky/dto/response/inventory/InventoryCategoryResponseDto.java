package com.jamesaworo.stocky.dto.response.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InventoryCategoryResponseDto {
    private final Long id;
    private final String name;
}
