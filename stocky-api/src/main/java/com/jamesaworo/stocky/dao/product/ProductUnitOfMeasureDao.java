package com.jamesaworo.stocky.dao.product;

import com.jamesaworo.stocky.entity.product.ProductUnitOfMeasure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductUnitOfMeasureDao extends JpaRepository<ProductUnitOfMeasure, Long> {
    Optional<ProductUnitOfMeasure> findByTitleEqualsIgnoreCaseAndUnitEqualsIgnoreCase(String type, String unit);

    Optional<ProductUnitOfMeasure> findByTitleEqualsIgnoreCase(String title);
}
