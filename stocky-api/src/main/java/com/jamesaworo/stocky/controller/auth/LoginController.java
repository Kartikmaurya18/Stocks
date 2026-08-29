package com.jamesaworo.stocky.controller.auth;

import com.jamesaworo.stocky.dto.request.auth.LoginRequestDto;
import com.jamesaworo.stocky.dto.response.auth.LoginResponseDto;
import com.jamesaworo.stocky.service.auth.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @PostMapping(value = "login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto request
    ) {
        return this.service.login(request);
    }
}
