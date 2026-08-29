package com.jamesaworo.stocky.dao.stock;

import com.jamesaworo.stocky.entity.stock.StockSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockSettlementDao extends JpaRepository<StockSettlement, Long> {
}
