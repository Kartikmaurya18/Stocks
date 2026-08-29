package com.jamesaworo.stocky.dto.request.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductCategoryRequestDto {
    private Long id;

    @NotNull(message = "Title cannot be empty")
    @NotBlank(message = "Title cannot be blank")
    private String title;
    
    private String description;
    private ProductCategoryRequestDto parent;
    private Boolean isActiveStatus;
    private String createdAt;
}
