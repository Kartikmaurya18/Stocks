package com.jamesaworo.stocky.service.product;

import com.jamesaworo.stocky.dto.request.product.ProductVariantRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductVariantService {
    ResponseEntity<ProductVariantRequestDto> find(Long id);
    ResponseEntity<List<ProductVariantRequestDto>> findMany();
    ResponseEntity<Optional<ProductVariantRequestDto>> save(ProductVariantRequestDto request);
    ResponseEntity<Optional<ProductVariantRequestDto>> update(ProductVariantRequestDto request);
    ResponseEntity<Optional<Boolean>> remove(Long id);
}
