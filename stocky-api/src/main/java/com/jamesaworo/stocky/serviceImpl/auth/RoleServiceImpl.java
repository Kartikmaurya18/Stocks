package com.jamesaworo.stocky.serviceImpl.auth;

import com.jamesaworo.stocky.dao.auth.RoleDao;
import com.jamesaworo.stocky.dto.request.auth.PermissionRequestDto;
import com.jamesaworo.stocky.dto.request.auth.RoleRequestDto;
import com.jamesaworo.stocky.entity.auth.Permission;
import com.jamesaworo.stocky.entity.auth.Role;
import com.jamesaworo.stocky.mapper.AuthMapper;
import com.jamesaworo.stocky.service.auth.PermissionService;
import com.jamesaworo.stocky.service.auth.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static com.jamesaworo.stocky.core.constants.Setting.DEFAULT_SYS_ROLE;
import static org.springframework.http.ResponseEntity.ok;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    
    private final RoleDao repository;
    private final PermissionService permissionService;
    private final AuthMapper mapper;

    @Override
    public Role save(Role role) {
        role.setPermissions(new HashSet<>(this.getFullPermissionObjectsFromRole(role)));
        return this.repository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return this.repository.findAll();
    }

    @Override
    public boolean isNotSystemRole(Role role) {
        return !role.getName().equals(DEFAULT_SYS_ROLE);
    }

    @Override
    public Optional<Role> getOne(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Set<Permission> getRolePermissions(Long id) {
        Optional<Role> optional = this.getOne(id);
        return optional.map(Role::getPermissions).orElse(new HashSet<>());
    }

    @Override
    public Optional<Role> update(Role roleToUpdate) {
        Optional<Role> optionalRole = this.getOne(roleToUpdate.getId());
        return optionalRole.map(role -> mapRoleBeforeUpdate(roleToUpdate, role));
    }

    private Role mapRoleBeforeUpdate(Role roleToUpdate, Role existingRole) {
        existingRole.setName(roleToUpdate.getName());
        existingRole.setDescription(roleToUpdate.getDescription());
        existingRole.setPermissions(roleToUpdate.getPermissions());
        return this.save(existingRole);
    }

    @Override
    public Optional<Boolean> updateActiveStatus(Long id) {
        Optional<Role> optional = this.getOne(id);
        return optional.map(role -> this.updateRoleActiveStatus(role.getId(), !role.getIsActiveStatus()));
    }

    private Collection<Permission> getFullPermissionObjectsFromRole(Role role) {
        LongStream streamOfPermissionIds = role.getPermissions().stream().mapToLong(Permission::getId);
        return permissionService.getPermissionsByIds(streamOfPermissionIds);
    }

    private Boolean updateRoleActiveStatus(Long id, Boolean status) {
        int result = this.repository.updateActiveStatus(status, id);
        return result == 1;
    }
    
    // Interactor equivalent methods
    @Override
    public ResponseEntity<RoleRequestDto> create(RoleRequestDto request) {
        Role role = this.save(mapper.toRole(request));
        return ok().body(mapper.toRoleRequestDto(role));
    }

    @Override
    public ResponseEntity<List<RoleRequestDto>> getAllRoles() {
        List<Role> roles = this.getAll();
        List<RoleRequestDto> requests = roles.stream()
                .filter(this::isNotSystemRole).map(mapper::toRoleRequestDto).collect(Collectors.toList());
        return ok().body(requests);
    }

    @Override
    public ResponseEntity<Optional<RoleRequestDto>> getOneRole(Long id) {
        Optional<Role> optional = this.getOne(id);
        return ok().body(optional.map(mapper::toRoleRequestDto));
    }

    @Override
    public ResponseEntity<List<PermissionRequestDto>> getRolePermissionsDto(Long id) {
        Set<Permission> permissions = this.getRolePermissions(id);
        List<PermissionRequestDto> permissionRequests = permissions.stream().map(mapper::toPermissionRequestDto).collect(Collectors.toList());
        return ok().body(permissionRequests);
    }

    @Override
    public ResponseEntity<Optional<RoleRequestDto>> updateRole(RoleRequestDto request) {
        Role model = mapper.toRole(request);
        Optional<Role> optional = this.update(model);
        return ok().body(optional.map(mapper::toRoleRequestDto));
    }

    @Override
    public ResponseEntity<Optional<Boolean>> updateRoleActiveStatus(Long id) {
        return ok().body(this.updateActiveStatus(id));
    }
}
