package com.jamesaworo.stocky.dto.request.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariantRequestDto {
    private Long id;

    @NotNull(message = "value cannot be empty")
    private String variantValue;

    @NotNull(message = "type cannot be empty")
    private String variantType;
}
