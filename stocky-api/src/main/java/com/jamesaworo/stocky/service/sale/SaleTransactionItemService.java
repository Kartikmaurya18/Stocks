package com.jamesaworo.stocky.service.sale;

import com.jamesaworo.stocky.entity.sale.SaleTransactionItem;
import java.util.Optional;

public interface SaleTransactionItemService {
    SaleTransactionItem save(SaleTransactionItem item);
    Optional<SaleTransactionItem> find(Long id);
}
