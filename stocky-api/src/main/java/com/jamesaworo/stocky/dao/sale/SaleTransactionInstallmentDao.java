package com.jamesaworo.stocky.dao.sale;

import com.jamesaworo.stocky.entity.sale.SaleTransactionInstallment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleTransactionInstallmentDao extends JpaRepository<SaleTransactionInstallment, Long> {
}
