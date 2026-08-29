package com.jamesaworo.stocky.dto.request.sale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jamesaworo.stocky.core.request.CommonRequest;
import com.jamesaworo.stocky.features.company.data.request.CompanyCustomerRequest;
import com.jamesaworo.stocky.features.company.data.request.CompanyEmployeeRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleTransactionRequestDto {
    private Long id;
    private String reference;
    private String serial;
    private String time;
    private String date;
    private CompanyCustomerRequest customer;
    private CompanyEmployeeRequest employee;
    private SaleTransactionAmountRequestDto amount;
    private SaleTransactionInstallmentRequestDto installment;
    private List<SaleTransactionItemRequestDto> items;
    private String other;
    private String receiptUrl;
    private CommonRequest paymentOption;
}
