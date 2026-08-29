package com.jamesaworo.stocky.service.inventory;

import com.jamesaworo.stocky.dto.request.inventory.InventoryProductRequestDto;
import com.jamesaworo.stocky.dto.response.inventory.InventoryProductResponseDto;

import java.util.List;

public interface InventoryProductService {
    InventoryProductResponseDto create(InventoryProductRequestDto request);
    InventoryProductResponseDto findById(Long id);
    List<InventoryProductResponseDto> findAll(Long categoryId);
    InventoryProductResponseDto update(Long id, InventoryProductRequestDto request);
    void delete(Long id);
}
