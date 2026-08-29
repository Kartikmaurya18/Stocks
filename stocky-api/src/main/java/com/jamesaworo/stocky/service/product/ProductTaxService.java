package com.jamesaworo.stocky.service.product;

import com.jamesaworo.stocky.dto.request.product.ProductTaxRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductTaxService {
    ResponseEntity<ProductTaxRequestDto> find(Long id);
    ResponseEntity<List<ProductTaxRequestDto>> findMany();
    ResponseEntity<Optional<ProductTaxRequestDto>> save(ProductTaxRequestDto request);
    ResponseEntity<Optional<ProductTaxRequestDto>> update(ProductTaxRequestDto request);
    ResponseEntity<Optional<Boolean>> remove(Long id);
}
