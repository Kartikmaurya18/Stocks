package com.jamesaworo.stocky.serviceImpl.sale;

import com.jamesaworo.stocky.dao.sale.SaleTransactionInstallmentDao;
import com.jamesaworo.stocky.entity.sale.SaleTransaction;
import com.jamesaworo.stocky.entity.sale.SaleTransactionInstallment;
import com.jamesaworo.stocky.service.sale.SaleTransactionInstallmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SaleTransactionInstallmentServiceImpl implements SaleTransactionInstallmentService {

    private final SaleTransactionInstallmentDao repository;

    @Override
    public SaleTransactionInstallment save(SaleTransactionInstallment installment) {
        return this.repository.save(installment);
    }

    @Override
    public void saveAndSet(SaleTransaction transaction) {
        SaleTransactionInstallment savedInstallment = this.save(transaction.getInstallment());
        transaction.setInstallment(savedInstallment);
    }
}
