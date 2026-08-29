package com.jamesaworo.stocky.entity.stock;

import com.jamesaworo.stocky.entity.BaseModel;
import lombok.*;

import javax.persistence.*;

import static com.jamesaworo.stocky.core.constants.Table.STOCK_SETTLEMENT;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = STOCK_SETTLEMENT)
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockSettlement extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double amount;
	private Double paid;
	private Double balance;
	
}
