package com.jamesaworo.stocky.controller.product;

import com.jamesaworo.stocky.dto.request.product.ProductCategoryRequestDto;
import com.jamesaworo.stocky.service.product.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "/product/category")
@RequiredArgsConstructor
public class ProductCategoryController {
    private final ProductCategoryService service;

    @GetMapping(value = "get/{id}")
    public ResponseEntity<ProductCategoryRequestDto> find(@PathVariable Long id) {
        return this.service.find(id);
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductCategoryRequestDto>> findMany() {
        return this.service.findMany();
    }

    @PostMapping("create")
    public ResponseEntity<Optional<ProductCategoryRequestDto>> save(
            @RequestBody @Valid ProductCategoryRequestDto dto
    ) {
        return this.service.save(dto);
    }

    @PutMapping("update")
    public ResponseEntity<Optional<ProductCategoryRequestDto>> update(
            @RequestBody @Valid ProductCategoryRequestDto dto
    ) {
        return this.service.update(dto);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Optional<Boolean>> remove(@PathVariable Long id) {
        return this.service.remove(id);
    }

    @GetMapping("search")
    public ResponseEntity<List<ProductCategoryRequestDto>> search(
            @RequestParam(value = "term") String term
    ) {
        return this.service.search(term);
    }

    @PutMapping("status/{id}")
    public ResponseEntity<Optional<Boolean>> toggleIsActiveStatus(@PathVariable Long id) {
        return this.service.toggleActiveStatus(id);
    }

    @GetMapping("/download-template")
    @ResponseBody
    public ResponseEntity<Resource> downloadFile() throws IOException {
        return this.service.downloadTemplate();
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadBatchCategories(@RequestParam("file") MultipartFile file) throws IOException {
        return this.service.uploadTemplate(file);
    }
}
