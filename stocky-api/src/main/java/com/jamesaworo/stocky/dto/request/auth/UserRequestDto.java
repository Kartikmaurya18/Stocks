package com.jamesaworo.stocky.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequestDto {
    private Long id;
    private String username;
    private String password;
    private LocalDate expirationDate;
    private Collection<RoleRequestDto> roles;
    private Boolean isActiveStatus;
    private String name;
}
