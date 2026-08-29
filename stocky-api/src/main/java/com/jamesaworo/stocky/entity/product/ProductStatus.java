package com.jamesaworo.stocky.entity.product;

import com.jamesaworo.stocky.entity.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

import static com.jamesaworo.stocky.core.constants.Table.PRODUCT_STATUS;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = PRODUCT_STATUS)
@Data
public class ProductStatus extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<ProductBasic> productBasics;

    public ProductStatus(Long id) {
        this.id = id;
    }

    public ProductStatus() {
    }
}
