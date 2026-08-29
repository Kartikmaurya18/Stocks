package com.jamesaworo.stocky.service.auth;

import com.jamesaworo.stocky.dto.request.auth.LoginRequestDto;
import com.jamesaworo.stocky.dto.response.auth.LoginResponseDto;
import org.springframework.http.ResponseEntity;

public interface LoginService {
    ResponseEntity<LoginResponseDto> login(LoginRequestDto request);
}
