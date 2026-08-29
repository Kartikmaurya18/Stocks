package com.jamesaworo.stocky.entity.product;

import com.jamesaworo.stocky.entity.BaseModel;
import lombok.*;

import javax.persistence.*;

import static com.jamesaworo.stocky.core.constants.Table.PRODUCT;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = PRODUCT)
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private ProductBasic basic;

    @OneToOne
    private ProductPrice price;

    public Product(Long id) {
        this.id = id;
    }
}
