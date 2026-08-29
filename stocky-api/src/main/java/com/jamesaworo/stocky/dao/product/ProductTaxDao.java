package com.jamesaworo.stocky.dao.product;

import com.jamesaworo.stocky.entity.product.ProductTax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ProductTaxDao extends JpaRepository<ProductTax, Long> {
    Optional<ProductTax> findByTitleEqualsIgnoreCaseAndPercent(String title, Double percent);

    Optional<ProductTax> findByTitleEqualsIgnoreCase(String name);

    @Transactional
    @Modifying
    @Query(value = "update ProductTax c set c.isActiveStatus = :status where c.id = :id")
    int updateIsActiveStatus(@Param(value = "status") Boolean status, @Param(value = "id") Long id);
}
