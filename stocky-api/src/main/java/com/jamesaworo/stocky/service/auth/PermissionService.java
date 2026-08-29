package com.jamesaworo.stocky.service.auth;

import com.jamesaworo.stocky.dto.request.auth.PermissionRequestDto;
import com.jamesaworo.stocky.dto.response.auth.PermissionGroupResponseDto;
import com.jamesaworo.stocky.entity.auth.Permission;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

public interface PermissionService {
    List<Permission> getAll();
    Optional<Permission> getById(Long id);
    Collection<Permission> getPermissionsByIds(LongStream list);
    
    ResponseEntity<List<PermissionRequestDto>> getAllPermissions();
    ResponseEntity<Optional<PermissionRequestDto>> getOne(Long id);
    ResponseEntity<List<PermissionGroupResponseDto>> getAllGroupedByModule();
}
