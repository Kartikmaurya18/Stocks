package com.jamesaworo.stocky.dao.sale;

import com.jamesaworo.stocky.entity.sale.SaleTransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleTransactionItemDao extends JpaRepository<SaleTransactionItem, Long> {
}
