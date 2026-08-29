package com.jamesaworo.stocky.service.product;

import com.jamesaworo.stocky.dto.request.product.ProductBasicRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductBasicService {
    ResponseEntity<ProductBasicRequestDto> find(Long id);
    ResponseEntity<List<ProductBasicRequestDto>> findMany();
    ResponseEntity<Optional<ProductBasicRequestDto>> save(ProductBasicRequestDto request);
    ResponseEntity<Optional<ProductBasicRequestDto>> update(ProductBasicRequestDto request);
    ResponseEntity<Optional<Boolean>> remove(Long id);
    ResponseEntity<Boolean> incrementQuantity(Long id, Integer quantity);
    ResponseEntity<Boolean> decrementQuantity(Long id, Integer quantity);
}
