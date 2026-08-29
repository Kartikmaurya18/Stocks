package com.jamesaworo.stocky.dao.product;

import com.jamesaworo.stocky.entity.product.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductStatusDao extends JpaRepository<ProductStatus, Long> {
	Optional<ProductStatus> findByTitleEqualsIgnoreCase(String title);
}
