package com.jamesaworo.stocky.entity.product;

import com.jamesaworo.stocky.entity.BaseModel;
import com.jamesaworo.stocky.entity.product.enums.ProductVariantType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static com.jamesaworo.stocky.core.constants.Table.PRODUCT_VARIATIONS;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = PRODUCT_VARIATIONS)
@Data
public class ProductVariant extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "Variant type cannot be null")
    private ProductVariantType variantType;

    private String variantValue;
}
