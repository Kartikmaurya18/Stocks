package com.jamesaworo.stocky.entity.product;

import com.jamesaworo.stocky.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import static com.jamesaworo.stocky.core.constants.Table.PRODUCT_TAX;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = PRODUCT_TAX)
@Data
public class ProductTax extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private double percent;

    public ProductTax(Long id) {
        this.id = id;
    }

    public ProductTax() {
    }
}
