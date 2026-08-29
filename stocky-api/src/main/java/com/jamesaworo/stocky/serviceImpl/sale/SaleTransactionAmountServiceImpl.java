package com.jamesaworo.stocky.serviceImpl.sale;

import com.jamesaworo.stocky.dao.sale.SaleTransactionAmountDao;
import com.jamesaworo.stocky.entity.sale.SaleTransaction;
import com.jamesaworo.stocky.entity.sale.SaleTransactionAmount;
import com.jamesaworo.stocky.service.sale.SaleTransactionAmountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleTransactionAmountServiceImpl implements SaleTransactionAmountService {
    private final SaleTransactionAmountDao repository;

    @Override
    public SaleTransactionAmount save(SaleTransactionAmount amount) {
        return this.repository.save(amount);
    }

    @Override
    public void saveAndSet(SaleTransaction transaction) {
        SaleTransactionAmount save = this.save(transaction.getAmount());
        transaction.setAmount(save);
    }
}
