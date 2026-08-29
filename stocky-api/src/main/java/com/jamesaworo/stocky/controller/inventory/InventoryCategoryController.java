package com.jamesaworo.stocky.controller.inventory;

import com.jamesaworo.stocky.dto.request.inventory.InventoryCategoryRequestDto;
import com.jamesaworo.stocky.dto.response.inventory.InventoryCategoryResponseDto;
import com.jamesaworo.stocky.service.inventory.InventoryCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/inventory/categories")
@RequiredArgsConstructor
public class InventoryCategoryController {
    private final InventoryCategoryService categoryService;

    @PostMapping
    public ResponseEntity<InventoryCategoryResponseDto> create(@Valid @RequestBody InventoryCategoryRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(request));
    }

    @GetMapping("/{id}")
    public InventoryCategoryResponseDto findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @GetMapping
    public List<InventoryCategoryResponseDto> findAll() {
        return categoryService.findAll();
    }

    @PutMapping("/{id}")
    public InventoryCategoryResponseDto update(@PathVariable Long id, @Valid @RequestBody InventoryCategoryRequestDto request) {
        return categoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
