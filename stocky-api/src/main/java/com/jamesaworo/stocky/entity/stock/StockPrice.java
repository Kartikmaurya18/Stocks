package com.jamesaworo.stocky.entity.stock;

import com.jamesaworo.stocky.entity.BaseModel;
import lombok.*;

import javax.persistence.*;

import static com.jamesaworo.stocky.core.constants.Table.STOCK_PRICE;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = STOCK_PRICE)
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockPrice extends BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double costPrice;
	private Double markupPercent;
	private Double sellingPrice;
	private Double expensesAmount;

}
