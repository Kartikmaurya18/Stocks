package com.jamesaworo.stocky.controller.product;

import com.jamesaworo.stocky.dto.request.product.ProductVariantRequestDto;
import com.jamesaworo.stocky.service.product.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "/product/variant")
@RequiredArgsConstructor
public class ProductVariantController {
    private final ProductVariantService service;

    @GetMapping(value = "get/{id}")
    public ResponseEntity<ProductVariantRequestDto> find(@PathVariable Long id) {
        return this.service.find(id);
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductVariantRequestDto>> findMany() {
        return this.service.findMany();
    }

    @PostMapping("create")
    public ResponseEntity<Optional<ProductVariantRequestDto>> save(@RequestBody @Valid ProductVariantRequestDto dto) {
        return this.service.save(dto);
    }

    @PutMapping("update")
    public ResponseEntity<Optional<ProductVariantRequestDto>> update(@RequestBody @Valid ProductVariantRequestDto dto) {
        return this.service.update(dto);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Optional<Boolean>> remove(@PathVariable Long id) {
        return this.service.remove(id);
    }
}
