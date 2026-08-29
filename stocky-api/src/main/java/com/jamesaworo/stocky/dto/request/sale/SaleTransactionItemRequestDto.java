package com.jamesaworo.stocky.dto.request.sale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jamesaworo.stocky.features.product.data.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleTransactionItemRequestDto {
    private Long id;
    private ProductRequest product;
    private Integer quantity;
    private Double grandTotal;
    private Double discount;
    private Double tax;
    private Double subTotal;
    private Double price;
}
