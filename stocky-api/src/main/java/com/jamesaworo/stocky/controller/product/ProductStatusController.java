package com.jamesaworo.stocky.controller.product;

import com.jamesaworo.stocky.dto.request.product.ProductStatusRequestDto;
import com.jamesaworo.stocky.service.product.ProductStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "/product/status")
@RequiredArgsConstructor
public class ProductStatusController {
    private final ProductStatusService service;

    @GetMapping(value = "get/{id}")
    public ResponseEntity<ProductStatusRequestDto> find(@PathVariable Long id) {
        return this.service.find(id);
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductStatusRequestDto>> findMany() {
        return this.service.findMany();
    }

    @PostMapping("create")
    public ResponseEntity<Optional<ProductStatusRequestDto>> save(@RequestBody @Valid ProductStatusRequestDto dto) {
        return this.service.save(dto);
    }

    @PutMapping("update")
    public ResponseEntity<Optional<ProductStatusRequestDto>> update(@RequestBody @Valid ProductStatusRequestDto dto) {
        return this.service.update(dto);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Optional<Boolean>> remove(@PathVariable Long id) {
        return this.service.remove(id);
    }
}
