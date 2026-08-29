package com.jamesaworo.stocky.dto.request.sale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jamesaworo.stocky.entity.sale.enums.SaleTransactionInstallmentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleTransactionInstallmentRequestDto {
    private Long id;
    private SaleTransactionInstallmentType installmentType;
}
