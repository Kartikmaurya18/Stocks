package com.jamesaworo.stocky.service.product;

import com.jamesaworo.stocky.dto.request.product.ProductStatusRequestDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductStatusService {
    ResponseEntity<ProductStatusRequestDto> find(Long id);
    ResponseEntity<List<ProductStatusRequestDto>> findMany();
    ResponseEntity<Optional<ProductStatusRequestDto>> save(ProductStatusRequestDto request);
    ResponseEntity<Optional<ProductStatusRequestDto>> update(ProductStatusRequestDto request);
    ResponseEntity<Optional<Boolean>> remove(Long id);
}
