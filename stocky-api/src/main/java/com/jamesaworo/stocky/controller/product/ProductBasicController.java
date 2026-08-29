package com.jamesaworo.stocky.controller.product;

import com.jamesaworo.stocky.dto.request.product.ProductBasicRequestDto;
import com.jamesaworo.stocky.service.product.ProductBasicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "/product/basic")
@RequiredArgsConstructor
public class ProductBasicController {
    private final ProductBasicService service;

    @GetMapping(value = "get/{id}")
    public ResponseEntity<ProductBasicRequestDto> find(@PathVariable Long id) {
        return this.service.find(id);
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductBasicRequestDto>> findMany() {
        return this.service.findMany();
    }

    @PostMapping("create")
    public ResponseEntity<Optional<ProductBasicRequestDto>> save(@RequestBody @Valid ProductBasicRequestDto dto) {
        return this.service.save(dto);
    }

    @PutMapping("update")
    public ResponseEntity<Optional<ProductBasicRequestDto>> update(@RequestBody @Valid ProductBasicRequestDto dto) {
        return this.service.update(dto);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Optional<Boolean>> remove(@PathVariable Long id) {
        return this.service.remove(id);
    }
}
