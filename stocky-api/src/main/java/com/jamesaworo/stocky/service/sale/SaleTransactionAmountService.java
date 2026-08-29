package com.jamesaworo.stocky.service.sale;

import com.jamesaworo.stocky.entity.sale.SaleTransactionAmount;
import com.jamesaworo.stocky.entity.sale.SaleTransaction;

public interface SaleTransactionAmountService {
    SaleTransactionAmount save(SaleTransactionAmount amount);
    void saveAndSet(SaleTransaction transaction);
}
