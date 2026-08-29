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
public class StockSettlementRequestDto {
	private Long id;
	private Double amount;
	private Double paid;
	private Double balance;
	private String createdAt;
	private Boolean isActiveStatus;

}
