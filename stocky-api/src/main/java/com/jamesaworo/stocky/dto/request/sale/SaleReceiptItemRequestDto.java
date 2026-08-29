package com.jamesaworo.stocky.dto.request.sale;

import lombok.Data;

@Data
public class SaleReceiptItemRequestDto {
    private String quantity;
    private String name;
    private String price;
}
