package com.jamesaworo.stocky.dao.stock;

import com.jamesaworo.stocky.entity.stock.StockExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockExpensesDao extends JpaRepository<StockExpenses, Long> {
}
