package com.jamesaworo.stocky.service.sale;

import java.util.Optional;

public interface SaleTransactionReceiptService {
    Optional<String> generateReceiptUrl(Long saleTransactionId);
}
