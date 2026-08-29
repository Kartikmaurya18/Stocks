package com.jamesaworo.stocky.service.auth;

import com.jamesaworo.stocky.dto.request.auth.PermissionRequestDto;
import com.jamesaworo.stocky.dto.request.auth.RoleRequestDto;
import org.springframework.http.ResponseEntity;

import com.jamesaworo.stocky.entity.auth.Permission;
import com.jamesaworo.stocky.entity.auth.Role;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleService {
    Role save(Role role);
    List<Role> getAll();
    boolean isNotSystemRole(Role role);
    Optional<Role> getOne(Long id);
    Set<Permission> getRolePermissions(Long id);
    Optional<Role> update(Role role);
    Optional<Boolean> updateActiveStatus(Long id);

    ResponseEntity<RoleRequestDto> create(RoleRequestDto request);
    ResponseEntity<List<RoleRequestDto>> getAllRoles();
    ResponseEntity<Optional<RoleRequestDto>> getOneRole(Long id);
    ResponseEntity<List<PermissionRequestDto>> getRolePermissionsDto(Long id);
    ResponseEntity<Optional<RoleRequestDto>> updateRole(RoleRequestDto request);
    ResponseEntity<Optional<Boolean>> updateRoleActiveStatus(Long id);
}
