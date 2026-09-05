package com.jamesaworo.stocky.dto.request.stock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jamesaworo.stocky.features.company.data.request.CompanySupplierRequest;
import com.jamesaworo.stocky.dto.request.product.ProductRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockItemRequestDto {
	private Long id;
	private String recordedDate;
	private Integer productQuantity;
	private Integer productQuantitySold;
	private String other;
	private CompanySupplierRequest supplier;
	private ProductRequestDto product;
	private List<StockExpensesRequestDto> expenses;
	private StockSettlementRequestDto settlement;
	private StockPriceRequestDto price;
	private String createdAt;
	private Boolean isActiveStatus;
}
