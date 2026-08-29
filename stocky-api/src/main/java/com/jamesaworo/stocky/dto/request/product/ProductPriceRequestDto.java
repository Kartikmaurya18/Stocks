package com.jamesaworo.stocky.dto.request.product;

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
public class ProductPriceRequestDto {
    private Long id;
    private Double markup;
    private Double costPrice;
    private Double sellingPrice;
    private Long productId;
    private Double discount;
}
