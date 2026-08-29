package com.jamesaworo.stocky.service.product;

import com.jamesaworo.stocky.dto.request.product.ProductRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    ResponseEntity<ProductRequestDto> find(Long id);
    ResponseEntity<List<ProductRequestDto>> findMany();
    ResponseEntity<Optional<ProductRequestDto>> save(ProductRequestDto request);
    ResponseEntity<Optional<ProductRequestDto>> update(ProductRequestDto request);
    ResponseEntity<Optional<Boolean>> remove(Long id);
    ResponseEntity<List<ProductRequestDto>> search(String term);
    ResponseEntity<Resource> downloadTemplate() throws IOException;
    ResponseEntity<?> uploadTemplate(MultipartFile file);
}
