package com.jamesaworo.stocky.dao.inventory;

import com.jamesaworo.stocky.entity.inventory.InventoryCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryCategoryDao extends JpaRepository<InventoryCategory, Long> {
    boolean existsByNameIgnoreCase(String name);
}
