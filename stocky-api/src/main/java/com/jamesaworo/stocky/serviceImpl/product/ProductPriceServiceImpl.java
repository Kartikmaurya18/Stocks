package com.jamesaworo.stocky.serviceImpl.product;

import com.jamesaworo.stocky.dao.product.ProductPriceDao;
import com.jamesaworo.stocky.dto.request.product.ProductPriceRequestDto;
import com.jamesaworo.stocky.service.product.ProductPriceService;
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
public class ProductPriceServiceImpl implements ProductPriceService {
    private final ProductPriceDao dao;

    @Override
    public ResponseEntity<ProductPriceRequestDto> find(Long id) {
        return ResponseEntity.ok(new ProductPriceRequestDto());
    }

    @Override
    public ResponseEntity<List<ProductPriceRequestDto>> findMany() {
        return ResponseEntity.ok(List.of());
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductPriceRequestDto>> save(ProductPriceRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductPriceRequestDto>> update(ProductPriceRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<Boolean>> remove(Long id) {
        dao.deleteById(id);
        return ResponseEntity.ok(Optional.of(true));
    }
}
