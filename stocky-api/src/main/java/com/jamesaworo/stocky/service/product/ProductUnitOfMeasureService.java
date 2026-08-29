package com.jamesaworo.stocky.service.product;

import com.jamesaworo.stocky.dto.request.product.ProductUnitOfMeasureRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductUnitOfMeasureService {
    ResponseEntity<ProductUnitOfMeasureRequestDto> find(Long id);
    ResponseEntity<List<ProductUnitOfMeasureRequestDto>> findMany();
    ResponseEntity<Optional<ProductUnitOfMeasureRequestDto>> save(ProductUnitOfMeasureRequestDto request);
    ResponseEntity<Optional<ProductUnitOfMeasureRequestDto>> update(ProductUnitOfMeasureRequestDto request);
    ResponseEntity<Optional<Boolean>> remove(Long id);
}
