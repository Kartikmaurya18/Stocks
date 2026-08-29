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
public class ProductRequestDto {
    private Long id;
    private ProductBasicRequestDto basic;
    private ProductPriceRequestDto price;
    private String createdAt;
    private Boolean isActiveStatus;
}
