package com.jamesaworo.stocky.controller.auth;

import com.jamesaworo.stocky.dto.request.auth.PermissionRequestDto;
import com.jamesaworo.stocky.dto.request.auth.RoleRequestDto;
import com.jamesaworo.stocky.service.auth.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "/auth/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService service;

    @PostMapping(value = "/create")
    public ResponseEntity<RoleRequestDto> create(@RequestBody @Valid RoleRequestDto role) {
        return this.service.create(role);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<RoleRequestDto>> getAll() {
        return this.service.getAllRoles();
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<Optional<RoleRequestDto>> getOne(@PathVariable Long id) {
        return this.service.getOneRole(id);
    }

    @GetMapping(value = "/find-role-permission/{id}")
    public ResponseEntity<List<PermissionRequestDto>> getRolePermissions(@PathVariable Long id) {
        return this.service.getRolePermissionsDto(id);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Optional<RoleRequestDto>> update(@RequestBody RoleRequestDto request) {
        if (ObjectUtils.isEmpty(request.getId())) {
            return ResponseEntity.badRequest().body(Optional.empty());
        }
        return this.service.updateRole(request);
    }

    @PutMapping(value = "/status/{id}")
    public ResponseEntity<Optional<Boolean>> updateActiveStatus(@PathVariable Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return ResponseEntity.badRequest().body(Optional.empty());
        }
        return this.service.updateRoleActiveStatus(id);
    }
}
