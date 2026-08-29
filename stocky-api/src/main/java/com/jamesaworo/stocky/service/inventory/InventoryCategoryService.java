package com.jamesaworo.stocky.service.inventory;

import com.jamesaworo.stocky.dto.request.inventory.InventoryCategoryRequestDto;
import com.jamesaworo.stocky.dto.response.inventory.InventoryCategoryResponseDto;

import java.util.List;

public interface InventoryCategoryService {
    InventoryCategoryResponseDto create(InventoryCategoryRequestDto request);
    InventoryCategoryResponseDto findById(Long id);
    List<InventoryCategoryResponseDto> findAll();
    InventoryCategoryResponseDto update(Long id, InventoryCategoryRequestDto request);
    void delete(Long id);
}
