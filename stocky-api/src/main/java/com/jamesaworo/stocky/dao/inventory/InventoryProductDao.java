package com.jamesaworo.stocky.dao.inventory;

import com.jamesaworo.stocky.entity.inventory.InventoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryProductDao extends JpaRepository<InventoryProduct, Long> {
    boolean existsBySku(String sku);
    List<InventoryProduct> findAllByCategoryId(Long categoryId);
}
