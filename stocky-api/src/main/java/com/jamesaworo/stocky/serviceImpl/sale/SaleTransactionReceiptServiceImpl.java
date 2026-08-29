package com.jamesaworo.stocky.serviceImpl.sale;

import com.jamesaworo.stocky.entity.sale.SaleTransaction;
import com.jamesaworo.stocky.service.sale.SaleTransactionReceiptService;
import com.jamesaworo.stocky.service.sale.SaleTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleTransactionReceiptServiceImpl implements SaleTransactionReceiptService {
    private SaleTransactionService usecase;

    @Override
    public Optional<String> generateReceiptUrl(Long saleTransactionId) {
        Optional<SaleTransaction> optionalSaleTransaction = this.usecase.findOne(saleTransactionId);
        return optionalSaleTransaction.map(transaction -> {
            return "";
        });
    }
}
