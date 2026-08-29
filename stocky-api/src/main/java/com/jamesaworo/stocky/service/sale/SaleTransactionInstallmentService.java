package com.jamesaworo.stocky.service.sale;

import com.jamesaworo.stocky.entity.sale.SaleTransactionInstallment;
import com.jamesaworo.stocky.entity.sale.SaleTransaction;

public interface SaleTransactionInstallmentService {
    SaleTransactionInstallment save(SaleTransactionInstallment installment);
    void saveAndSet(SaleTransaction transaction);
}
