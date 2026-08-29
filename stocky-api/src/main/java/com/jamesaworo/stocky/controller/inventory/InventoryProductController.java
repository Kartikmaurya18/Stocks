package com.jamesaworo.stocky.controller.inventory;

import com.jamesaworo.stocky.dto.request.inventory.InventoryProductRequestDto;
import com.jamesaworo.stocky.dto.response.inventory.InventoryProductResponseDto;
import com.jamesaworo.stocky.service.inventory.InventoryProductService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/inventory/products")
@RequiredArgsConstructor
public class InventoryProductController {
    private final InventoryProductService productService;

    @PostMapping
    public ResponseEntity<InventoryProductResponseDto> create(@Valid @RequestBody InventoryProductRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
    }

    @GetMapping("/{id}")
    public InventoryProductResponseDto findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping
    public List<InventoryProductResponseDto> findAll(
            @RequestParam(required = false) Long categoryId) {
        return productService.findAll(categoryId);
    }

    @PutMapping("/{id}")
    public InventoryProductResponseDto update(@PathVariable Long id, @Valid @RequestBody InventoryProductRequestDto request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
