package com.jamesaworo.stocky.controller.stock;

import com.jamesaworo.stocky.core.params.PageSearchRequest;
import com.jamesaworo.stocky.core.params.PageSearchResult;
import com.jamesaworo.stocky.dto.request.stock.StockCounterRequestDto;
import com.jamesaworo.stocky.dto.response.stock.StockCounterResponseDto;
import com.jamesaworo.stocky.dto.request.stock.StockRequestDto;
import com.jamesaworo.stocky.dto.request.stock.StockSearchRequestDto;
import com.jamesaworo.stocky.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService service;

    @PostMapping(value = "/create")
    public ResponseEntity<StockRequestDto> create(@RequestBody StockRequestDto request) {
        return this.service.save(request);
    }

    @PostMapping(value = "/search-request")
    public ResponseEntity<PageSearchResult<List<StockRequestDto>>> searchProducts(
            @Valid @RequestBody PageSearchRequest<StockSearchRequestDto> request
    ) {
        return this.service.search(request);
    }

    @GetMapping("search")
    public ResponseEntity<List<StockRequestDto>> search(
            @RequestParam(value = "term") String term
    ) {
        return this.service.search(term);
    }

    @PostMapping(value = "/count-stock")
    public ResponseEntity<PageSearchResult<StockCounterResponseDto>> countStock(@RequestBody StockCounterRequestDto request) {
        return this.service.countStock(request);
    }
}
