package com.jamesaworo.stocky.dto.request.auth;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequestDto {
    @NotNull(message = "Username or email cannot be null")
    @NotEmpty(message = "Username or email cannot be empty")
    private String username;

    @NotNull(message = "Password cannot be null")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
}
