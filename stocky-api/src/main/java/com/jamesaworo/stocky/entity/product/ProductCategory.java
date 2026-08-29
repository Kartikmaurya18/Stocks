package com.jamesaworo.stocky.entity.product;

import com.jamesaworo.stocky.entity.BaseModel;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static com.jamesaworo.stocky.core.constants.Table.PRODUCT_CATEGORY;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = PRODUCT_CATEGORY)
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCategory extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ProductCategory parent;

    @OneToMany(mappedBy = "productCategory")
    private List<ProductBasic> products;

    public ProductCategory(Long id) {
        this.id = id;
    }
}
