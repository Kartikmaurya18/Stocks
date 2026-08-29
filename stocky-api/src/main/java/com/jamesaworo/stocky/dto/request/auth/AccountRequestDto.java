package com.jamesaworo.stocky.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountRequestDto {
    private Long id;
    private Long employeeId;
    private Long userId;
    private String name;
    private String phone;
    private String username;
    private String password;
    private Collection<RoleRequestDto> roles;
    private Boolean isActiveStatus;
    private String expiryDate;
}
