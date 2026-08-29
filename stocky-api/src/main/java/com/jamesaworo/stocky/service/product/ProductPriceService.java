package com.jamesaworo.stocky.service.product;

import com.jamesaworo.stocky.dto.request.product.ProductPriceRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductPriceService {
    ResponseEntity<ProductPriceRequestDto> find(Long id);
    ResponseEntity<List<ProductPriceRequestDto>> findMany();
    ResponseEntity<Optional<ProductPriceRequestDto>> save(ProductPriceRequestDto request);
    ResponseEntity<Optional<ProductPriceRequestDto>> update(ProductPriceRequestDto request);
    ResponseEntity<Optional<Boolean>> remove(Long id);
}
