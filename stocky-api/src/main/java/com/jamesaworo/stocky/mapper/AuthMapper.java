package com.jamesaworo.stocky.mapper;

import com.jamesaworo.stocky.dto.request.auth.PermissionRequestDto;
import com.jamesaworo.stocky.dto.request.auth.RoleRequestDto;
import com.jamesaworo.stocky.dto.response.auth.PermissionResponseDto;
import com.jamesaworo.stocky.dto.response.auth.RoleResponseDto;
import com.jamesaworo.stocky.entity.auth.Permission;
import com.jamesaworo.stocky.entity.auth.Role;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class AuthMapper {

    public PermissionResponseDto toPermissionResponseDto(Permission permission) {
        if (permission == null) return null;
        PermissionResponseDto dto = new PermissionResponseDto();
        dto.setId(permission.getId());
        dto.setName(permission.getName());
        dto.setModule(permission.getModule());
        return dto;
    }
    
    public PermissionRequestDto toPermissionRequestDto(Permission permission) {
        if (permission == null) return null;
        PermissionRequestDto dto = new PermissionRequestDto();
        dto.setId(permission.getId());
        dto.setName(permission.getName());
        dto.setModule(permission.getModule());
        return dto;
    }

    public Permission toPermission(PermissionRequestDto dto) {
        if (dto == null) return null;
        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());
        permission.setModule(dto.getModule());
        return permission;
    }
    
    public Permission toPermission(PermissionResponseDto dto) {
        if (dto == null) return null;
        Permission permission = new Permission();
        permission.setId(dto.getId());
        permission.setName(dto.getName());
        permission.setModule(dto.getModule());
        return permission;
    }

    public RoleResponseDto toRoleResponseDto(Role role) {
        if (role == null) return null;
        RoleResponseDto dto = new RoleResponseDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        if (role.getPermissions() != null) {
            dto.setPermissions(role.getPermissions().stream().map(this::toPermissionResponseDto).collect(Collectors.toList()));
        }
        dto.setIsActiveStatus(role.getIsActiveStatus());
        return dto;
    }
    
    public RoleRequestDto toRoleRequestDto(Role role) {
        if (role == null) return null;
        RoleRequestDto dto = new RoleRequestDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        if (role.getPermissions() != null) {
            dto.setPermissions(role.getPermissions().stream().map(this::toPermissionRequestDto).collect(Collectors.toList()));
        }
        dto.setIsActiveStatus(role.getIsActiveStatus());
        return dto;
    }

    public Role toRole(RoleRequestDto dto) {
        if (dto == null) return null;
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        if (dto.getPermissions() != null) {
            role.setPermissions(dto.getPermissions().stream().map(this::toPermission).collect(Collectors.toSet()));
        }
        return role;
    }
}
