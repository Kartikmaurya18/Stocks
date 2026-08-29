package com.jamesaworo.stocky.dao.stock;

import com.jamesaworo.stocky.entity.stock.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockPriceDao extends JpaRepository<StockPrice, Long> {
}
