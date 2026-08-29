package com.jamesaworo.stocky.serviceImpl.product;

import com.jamesaworo.stocky.dao.product.ProductBasicDao;
import com.jamesaworo.stocky.dto.request.product.ProductBasicRequestDto;
import com.jamesaworo.stocky.entity.product.ProductBasic;
import com.jamesaworo.stocky.service.product.ProductBasicService;
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
public class ProductBasicServiceImpl implements ProductBasicService {
    private final ProductBasicDao dao;

    @Override
    public ResponseEntity<ProductBasicRequestDto> find(Long id) {
        return ResponseEntity.ok(new ProductBasicRequestDto());
    }

    @Override
    public ResponseEntity<List<ProductBasicRequestDto>> findMany() {
        return ResponseEntity.ok(List.of());
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductBasicRequestDto>> save(ProductBasicRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductBasicRequestDto>> update(ProductBasicRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<Boolean>> remove(Long id) {
        dao.deleteById(id);
        return ResponseEntity.ok(Optional.of(true));
    }

    @Override
    @Transactional
    public ResponseEntity<Boolean> incrementQuantity(Long id, Integer quantity) {
        return ResponseEntity.ok(dao.incrementQuantity(id, quantity) > 0);
    }

    @Override
    @Transactional
    public ResponseEntity<Boolean> decrementQuantity(Long id, Integer quantity) {
        return ResponseEntity.ok(dao.decrementQuantity(id, quantity) > 0);
    }
}
