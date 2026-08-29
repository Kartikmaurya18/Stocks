package com.jamesaworo.stocky.serviceImpl.stock;

import com.jamesaworo.stocky.core.params.PageSearchRequest;
import com.jamesaworo.stocky.core.params.PageSearchResult;
import com.jamesaworo.stocky.dao.stock.StockDao;
import com.jamesaworo.stocky.dto.request.stock.StockCounterRequestDto;
import com.jamesaworo.stocky.dto.response.stock.StockCounterResponseDto;
import com.jamesaworo.stocky.dto.request.stock.StockRequestDto;
import com.jamesaworo.stocky.dto.request.stock.StockSearchRequestDto;
import com.jamesaworo.stocky.entity.stock.Stock;
import com.jamesaworo.stocky.mapper.StockMapper;
import com.jamesaworo.stocky.service.stock.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {

    private final StockDao stockDao;

    @Override
    public ResponseEntity<StockRequestDto> save(StockRequestDto request) {
        Stock stock = StockMapper.toEntity(request);
        Stock saved = stockDao.save(stock);
        return ResponseEntity.ok(StockMapper.toDto(saved));
    }

    @Override
    public ResponseEntity<PageSearchResult<List<StockRequestDto>>> search(PageSearchRequest<StockSearchRequestDto> request) {
        // Implementation details omitted for brevity
        return ResponseEntity.ok(new PageSearchResult<>());
    }

    @Override
    public ResponseEntity<List<StockRequestDto>> search(String term) {
        // Dummy implementation
        List<StockRequestDto> results = stockDao.findAll().stream()
            .map(StockMapper::toDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(results);
    }

    @Override
    public ResponseEntity<PageSearchResult<StockCounterResponseDto>> countStock(StockCounterRequestDto request) {
        // Dummy implementation
        return ResponseEntity.ok(new PageSearchResult<>());
    }
}
