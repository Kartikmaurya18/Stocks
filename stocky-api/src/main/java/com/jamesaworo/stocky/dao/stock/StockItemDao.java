package com.jamesaworo.stocky.dao.stock;

import com.jamesaworo.stocky.entity.stock.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemDao extends JpaRepository<StockItem, Long> {
}
