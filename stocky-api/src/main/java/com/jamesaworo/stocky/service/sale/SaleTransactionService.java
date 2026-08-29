package com.jamesaworo.stocky.service.sale;

import com.jamesaworo.stocky.core.params.PageSearchRequest;
import com.jamesaworo.stocky.core.params.PageSearchResult;
import com.jamesaworo.stocky.dto.request.sale.SaleTransactionRequestDto;
import com.jamesaworo.stocky.dto.request.sale.SaleTransactionSearchRequestDto;
import com.jamesaworo.stocky.entity.sale.SaleTransaction;
import com.jamesaworo.stocky.entity.sale.SaleTransactionItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface SaleTransactionService {
    SaleTransactionRequestDto saveTransaction(SaleTransactionRequestDto request);
    PageSearchResult<List<SaleTransactionRequestDto>> searchMany(PageSearchRequest<SaleTransactionSearchRequestDto> request);
    List<SaleTransactionRequestDto> searchMany(SaleTransactionSearchRequestDto request);
    Optional<SaleTransactionRequestDto> getOne(Long id);
    Optional<byte[]> getTransactionReceipt(String reference, String serial);
    byte[] searchReceiptBySerial(String serial);
    byte[] searchSaleTransactionReport(SaleTransactionSearchRequestDto request);
    
    SaleTransaction save(SaleTransaction saleTransaction, List<SaleTransactionItem> transactionItems);
    Page<SaleTransaction> findMany(Specification<SaleTransaction> specification, Pageable pageable);
    List<SaleTransaction> findMany(Specification<SaleTransaction> specification);
    Optional<SaleTransaction> findOne(Long id);
    Optional<SaleTransaction> findOne(String reference, String token);
    Optional<SaleTransaction> findOne(String serial);
}
