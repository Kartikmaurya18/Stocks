package com.jamesaworo.stocky.controller.product;

import com.jamesaworo.stocky.dto.request.product.ProductRequestDto;
import com.jamesaworo.stocky.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping(value = "get/{id}")
    public ResponseEntity<ProductRequestDto> find(@PathVariable Long id) {
        return this.service.find(id);
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductRequestDto>> findMany() {
        return this.service.findMany();
    }

    @PostMapping("create")
    public ResponseEntity<Optional<ProductRequestDto>> save(@RequestBody @Valid ProductRequestDto dto) {
        return this.service.save(dto);
    }

    @PutMapping("update")
    public ResponseEntity<Optional<ProductRequestDto>> update(@RequestBody @Valid ProductRequestDto dto) {
        return this.service.update(dto);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Optional<Boolean>> remove(@PathVariable Long id) {
        return this.service.remove(id);
    }
}
