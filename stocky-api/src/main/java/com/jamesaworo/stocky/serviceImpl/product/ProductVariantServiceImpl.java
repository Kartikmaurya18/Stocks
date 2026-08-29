package com.jamesaworo.stocky.serviceImpl.product;

import com.jamesaworo.stocky.dao.product.ProductVariantDao;
import com.jamesaworo.stocky.dto.request.product.ProductVariantRequestDto;
import com.jamesaworo.stocky.service.product.ProductVariantService;
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
public class ProductVariantServiceImpl implements ProductVariantService {
    private final ProductVariantDao dao;

    @Override
    public ResponseEntity<ProductVariantRequestDto> find(Long id) {
        return ResponseEntity.ok(new ProductVariantRequestDto());
    }

    @Override
    public ResponseEntity<List<ProductVariantRequestDto>> findMany() {
        return ResponseEntity.ok(List.of());
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductVariantRequestDto>> save(ProductVariantRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductVariantRequestDto>> update(ProductVariantRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<Boolean>> remove(Long id) {
        dao.deleteById(id);
        return ResponseEntity.ok(Optional.of(true));
    }
}
