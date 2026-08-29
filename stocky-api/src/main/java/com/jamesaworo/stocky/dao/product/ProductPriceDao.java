package com.jamesaworo.stocky.dao.product;

import com.jamesaworo.stocky.entity.product.Product;
import com.jamesaworo.stocky.entity.product.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceDao extends JpaRepository<ProductPrice, Long> {
	Optional<ProductPrice> findProductPriceByProduct(Product product);
}
