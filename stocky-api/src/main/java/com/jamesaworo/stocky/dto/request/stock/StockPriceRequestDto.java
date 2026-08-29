package com.jamesaworo.stocky.dto.request.stock;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockPriceRequestDto {
	private Long id;
	private Double costPrice;
	private Double markupPercent;
	private Double sellingPrice;
	private Double expensesAmount;
	private String createdAt;
	private Boolean isActiveStatus;

}
