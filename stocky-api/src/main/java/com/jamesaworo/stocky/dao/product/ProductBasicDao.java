package com.jamesaworo.stocky.dao.product;

import com.jamesaworo.stocky.entity.product.ProductBasic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ProductBasicDao extends JpaRepository<ProductBasic, Long> {
    @Query(value = "update ProductBasic p set p.quantity = p.quantity + :quantity where p.id = :id")
    @Modifying
    @Transactional
    int incrementQuantity(@Param(value = "id") Long id, @Param(value = "quantity") Integer quantity);

    @Query(value = "update ProductBasic p set p.quantity = p.quantity - :quantity where p.id = :id")
    @Modifying
    @Transactional
    int decrementQuantity(@Param(value = "id") Long id, @Param(value = "quantity") Integer quantity);

    Optional<ProductBasic> findByProductNameEqualsIgnoreCaseAndBrandNameEqualsIgnoreCase(String productName, String brandName);

    Optional<ProductBasic> findByBarcodeEqualsIgnoreCase(String barcode);

    Optional<ProductBasic> findBySkuEqualsIgnoreCase(String sku);
}
