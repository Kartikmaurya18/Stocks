package com.jamesaworo.stocky.controller.product;

import com.jamesaworo.stocky.dto.request.product.ProductUnitOfMeasureRequestDto;
import com.jamesaworo.stocky.service.product.ProductUnitOfMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "/product/unit-of-measure")
@RequiredArgsConstructor
public class ProductUnitOfMeasureController {
    private final ProductUnitOfMeasureService service;

    @GetMapping(value = "get/{id}")
    public ResponseEntity<ProductUnitOfMeasureRequestDto> find(@PathVariable Long id) {
        return this.service.find(id);
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductUnitOfMeasureRequestDto>> findMany() {
        return this.service.findMany();
    }

    @PostMapping("create")
    public ResponseEntity<Optional<ProductUnitOfMeasureRequestDto>> save(@RequestBody @Valid ProductUnitOfMeasureRequestDto dto) {
        return this.service.save(dto);
    }

    @PutMapping("update")
    public ResponseEntity<Optional<ProductUnitOfMeasureRequestDto>> update(@RequestBody @Valid ProductUnitOfMeasureRequestDto dto) {
        return this.service.update(dto);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Optional<Boolean>> remove(@PathVariable Long id) {
        return this.service.remove(id);
    }
}
