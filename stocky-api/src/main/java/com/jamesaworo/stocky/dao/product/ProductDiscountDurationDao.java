package com.jamesaworo.stocky.dao.product;

import com.jamesaworo.stocky.entity.product.ProductDiscountDuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductDiscountDurationDao extends JpaRepository<ProductDiscountDuration, Long> {

    Optional<ProductDiscountDuration> findProductDiscountDurationByPriceId(Long price);
}
