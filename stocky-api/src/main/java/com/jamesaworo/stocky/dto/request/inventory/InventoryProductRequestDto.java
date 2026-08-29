package com.jamesaworo.stocky.dto.request.inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class InventoryProductRequestDto {

    @NotBlank(message = "SKU is required")
    @Size(max = 64, message = "SKU must not exceed 64 characters")
    private String sku;

    @NotBlank(message = "Product name is required")
    @Size(max = 150, message = "Product name must not exceed 150 characters")
    private String name;

    @NotNull(message = "Unit price is required")
    @DecimalMin(value = "0.00", inclusive = true, message = "Unit price must be zero or greater")
    private BigDecimal unitPrice;

    @NotNull(message = "Quantity on hand is required")
    @Min(value = 0, message = "Quantity on hand must be zero or greater")
    private Integer quantityOnHand;

    @NotNull(message = "Category id is required")
    private Long categoryId;
}
