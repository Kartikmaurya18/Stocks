package com.jamesaworo.stocky.service.stock;

import com.jamesaworo.stocky.core.params.PageSearchRequest;
import com.jamesaworo.stocky.core.params.PageSearchResult;
import com.jamesaworo.stocky.dto.request.stock.StockCounterRequestDto;
import com.jamesaworo.stocky.dto.response.stock.StockCounterResponseDto;
import com.jamesaworo.stocky.dto.request.stock.StockRequestDto;
import com.jamesaworo.stocky.dto.request.stock.StockSearchRequestDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface StockService {
    ResponseEntity<StockRequestDto> save(StockRequestDto request);
    ResponseEntity<PageSearchResult<List<StockRequestDto>>> search(PageSearchRequest<StockSearchRequestDto> request);
    ResponseEntity<List<StockRequestDto>> search(String term);
    ResponseEntity<PageSearchResult<StockCounterResponseDto>> countStock(StockCounterRequestDto request);
}
