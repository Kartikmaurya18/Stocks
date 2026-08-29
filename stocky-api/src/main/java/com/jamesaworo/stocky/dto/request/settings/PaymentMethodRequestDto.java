package com.jamesaworo.stocky.dto.request.settings;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Aworo James
 * @since 4/22/23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PaymentMethodRequestDto extends SettingRequest {
    private Long id;
    private String title;
    private String description;

    public PaymentMethodRequestDto() {
    }
}