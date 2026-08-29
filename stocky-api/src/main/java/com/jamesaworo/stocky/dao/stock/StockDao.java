package com.jamesaworo.stocky.dao.stock;

import com.jamesaworo.stocky.entity.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StockDao extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock> {
	Stock findTopByOrderByIdDesc();
}
