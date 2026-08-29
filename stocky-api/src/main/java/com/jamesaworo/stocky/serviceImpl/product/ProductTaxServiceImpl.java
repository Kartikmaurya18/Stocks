package com.jamesaworo.stocky.serviceImpl.product;

import com.jamesaworo.stocky.dao.product.ProductTaxDao;
import com.jamesaworo.stocky.dto.request.product.ProductTaxRequestDto;
import com.jamesaworo.stocky.service.product.ProductTaxService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductTaxServiceImpl implements ProductTaxService {
    private final ProductTaxDao dao;

    @Override
    public ResponseEntity<ProductTaxRequestDto> find(Long id) {
        return ResponseEntity.ok(new ProductTaxRequestDto());
    }

    @Override
    public ResponseEntity<List<ProductTaxRequestDto>> findMany() {
        return ResponseEntity.ok(List.of());
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductTaxRequestDto>> save(ProductTaxRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductTaxRequestDto>> update(ProductTaxRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<Boolean>> remove(Long id) {
        dao.deleteById(id);
        return ResponseEntity.ok(Optional.of(true));
    }
}
