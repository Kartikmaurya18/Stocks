package com.jamesaworo.stocky.dao.product;

import com.jamesaworo.stocky.entity.product.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Long> {
	Optional<ProductCategory> findByTitle(String title);

	List<ProductCategory> findAllByTitleContainsIgnoreCase(String title);

	@Transactional
	@Modifying
	@Query(value = "update ProductCategory c set c.isActiveStatus = :status where c.id = :id")
	int updateIsActiveStatus(@Param(value = "status") Boolean status, @Param(value = "id") Long id);
}
