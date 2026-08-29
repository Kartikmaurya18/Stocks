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
public class StockExpensesRequestDto {
	private Long id;
	private String title;
	private Double amount;
	private String createdAt;
	private Boolean isActiveStatus;
}
