package com.jamesaworo.stocky.serviceImpl.product;

import com.jamesaworo.stocky.dao.product.ProductStatusDao;
import com.jamesaworo.stocky.dto.request.product.ProductStatusRequestDto;
import com.jamesaworo.stocky.service.product.ProductStatusService;
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
public class ProductStatusServiceImpl implements ProductStatusService {
    private final ProductStatusDao dao;

    @Override
    public ResponseEntity<ProductStatusRequestDto> find(Long id) {
        return ResponseEntity.ok(new ProductStatusRequestDto());
    }

    @Override
    public ResponseEntity<List<ProductStatusRequestDto>> findMany() {
        return ResponseEntity.ok(List.of());
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductStatusRequestDto>> save(ProductStatusRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductStatusRequestDto>> update(ProductStatusRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<Boolean>> remove(Long id) {
        dao.deleteById(id);
        return ResponseEntity.ok(Optional.of(true));
    }
}
