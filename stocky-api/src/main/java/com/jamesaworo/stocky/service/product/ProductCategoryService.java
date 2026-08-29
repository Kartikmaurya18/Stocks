package com.jamesaworo.stocky.service.product;

import com.jamesaworo.stocky.dto.request.product.ProductCategoryRequestDto;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {
    ResponseEntity<ProductCategoryRequestDto> find(Long id);

    ResponseEntity<List<ProductCategoryRequestDto>> findMany();

    ResponseEntity<Optional<ProductCategoryRequestDto>> save(ProductCategoryRequestDto request);

    ResponseEntity<Optional<ProductCategoryRequestDto>> update(ProductCategoryRequestDto request);

    ResponseEntity<Optional<Boolean>> remove(Long id);

    ResponseEntity<List<ProductCategoryRequestDto>> search(String term);

    ResponseEntity<Optional<Boolean>> toggleActiveStatus(Long id);

    ResponseEntity<Resource> downloadTemplate() throws IOException;

    ResponseEntity<?> uploadTemplate(MultipartFile file);
}
