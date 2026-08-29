package com.jamesaworo.stocky.dao.sale;

import com.jamesaworo.stocky.entity.sale.SaleTransactionAmount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleTransactionAmountDao extends JpaRepository<SaleTransactionAmount, Long> {
}
