package com.jamesaworo.stocky.entity.product;

import com.jamesaworo.stocky.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

import static com.jamesaworo.stocky.core.constants.Table.PRODUCT_UNIT_OF_MEASURE;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = PRODUCT_UNIT_OF_MEASURE)
@Data
public class ProductUnitOfMeasure extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String unit;

    @OneToMany(mappedBy = "unitOfMeasure", fetch = FetchType.LAZY)
    private List<ProductBasic> products;

    public ProductUnitOfMeasure() {
    }

    public ProductUnitOfMeasure(Long id) {
        this.id = id;
    }
}
