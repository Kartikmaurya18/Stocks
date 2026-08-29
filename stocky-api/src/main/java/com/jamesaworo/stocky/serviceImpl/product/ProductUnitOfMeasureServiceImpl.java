package com.jamesaworo.stocky.serviceImpl.product;

import com.jamesaworo.stocky.dao.product.ProductUnitOfMeasureDao;
import com.jamesaworo.stocky.dto.request.product.ProductUnitOfMeasureRequestDto;
import com.jamesaworo.stocky.service.product.ProductUnitOfMeasureService;
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
public class ProductUnitOfMeasureServiceImpl implements ProductUnitOfMeasureService {
    private final ProductUnitOfMeasureDao dao;

    @Override
    public ResponseEntity<ProductUnitOfMeasureRequestDto> find(Long id) {
        return ResponseEntity.ok(new ProductUnitOfMeasureRequestDto());
    }

    @Override
    public ResponseEntity<List<ProductUnitOfMeasureRequestDto>> findMany() {
        return ResponseEntity.ok(List.of());
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductUnitOfMeasureRequestDto>> save(ProductUnitOfMeasureRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductUnitOfMeasureRequestDto>> update(ProductUnitOfMeasureRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<Boolean>> remove(Long id) {
        dao.deleteById(id);
        return ResponseEntity.ok(Optional.of(true));
    }
}
