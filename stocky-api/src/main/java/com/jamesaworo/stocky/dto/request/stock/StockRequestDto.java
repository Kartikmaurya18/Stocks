package com.jamesaworo.stocky.dto.request.stock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jamesaworo.stocky.entity.stock.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockRequestDto {
    private Long id;
    private String createdAt;
    private Boolean isActiveStatus;
    private String code;
    private Boolean isGroupedExpenses;
    private Boolean isGroupedSettlement;
    private String recordDate;
    private String openDate;
    private String closedDate;
    private StockStatus status;
    private StockSettlementRequestDto settlement;
    private Set<StockExpensesRequestDto> expenses;
    private Set<StockItemRequestDto> stockItems;
}
