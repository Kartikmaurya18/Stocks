package com.jamesaworo.stocky.dao.product;

import com.jamesaworo.stocky.entity.product.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantDao extends JpaRepository<ProductVariant, Long> {
}
