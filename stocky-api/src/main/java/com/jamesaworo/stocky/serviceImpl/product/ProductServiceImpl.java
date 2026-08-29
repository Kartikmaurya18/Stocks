package com.jamesaworo.stocky.serviceImpl.product;

import com.jamesaworo.stocky.dao.product.ProductDao;
import com.jamesaworo.stocky.dto.request.product.ProductRequestDto;
import com.jamesaworo.stocky.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductDao dao;

    @Override
    public ResponseEntity<ProductRequestDto> find(Long id) {
        return ResponseEntity.ok(new ProductRequestDto());
    }

    @Override
    public ResponseEntity<List<ProductRequestDto>> findMany() {
        return ResponseEntity.ok(List.of());
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductRequestDto>> save(ProductRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductRequestDto>> update(ProductRequestDto request) {
        return ResponseEntity.ok(Optional.of(request));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<Boolean>> remove(Long id) {
        dao.deleteById(id);
        return ResponseEntity.ok(Optional.of(true));
    }

    @Override
    public ResponseEntity<List<ProductRequestDto>> search(String term) {
        return ResponseEntity.ok(List.of());
    }

    @Override
    public ResponseEntity<Resource> downloadTemplate() throws IOException {
        return ResponseEntity.ok(null);
    }

    @Override
    @Transactional
    public ResponseEntity<?> uploadTemplate(MultipartFile file) {
        return ResponseEntity.ok().build();
    }
}
