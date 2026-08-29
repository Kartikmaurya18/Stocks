package com.jamesaworo.stocky.dto.request.sale;

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
public class SaleTransactionAmountRequestDto {
    private Long id;
    private Double grandTotal;
    private Double discountTotal;
    private Double taxTotal;
    private Double subTotal;
}
