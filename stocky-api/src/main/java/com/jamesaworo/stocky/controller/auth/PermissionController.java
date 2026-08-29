package com.jamesaworo.stocky.controller.auth;

import com.jamesaworo.stocky.dto.response.auth.PermissionGroupResponseDto;
import com.jamesaworo.stocky.service.auth.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "/auth/permission")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService service;

    @GetMapping(value = "/all")
    public ResponseEntity<List<PermissionGroupResponseDto>> getAllPermissionsGroupedByModule() {
        return service.getAllGroupedByModule();
    }
}
