package com.jamesaworo.stocky.serviceImpl.auth;

import com.jamesaworo.stocky.dao.auth.PermissionDao;
import com.jamesaworo.stocky.dto.request.auth.PermissionRequestDto;
import com.jamesaworo.stocky.dto.response.auth.PermissionGroupResponseDto;
import com.jamesaworo.stocky.entity.auth.Permission;
import com.jamesaworo.stocky.mapper.AuthMapper;
import com.jamesaworo.stocky.service.auth.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.springframework.http.ResponseEntity.ok;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    private final PermissionDao repository;
    private final AuthMapper mapper;

    @Override
    public List<Permission> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Optional<Permission> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Collection<Permission> getPermissionsByIds(LongStream list) {
        List<Permission> permissions = new ArrayList<>();
        list.forEach(id -> this.getById(id).ifPresent(permissions::add));
        return permissions;
    }

    @Override
    public ResponseEntity<List<PermissionRequestDto>> getAllPermissions() {
        List<Permission> permissions = this.getAll();
        return ok().body(permissions.stream().map(mapper::toPermissionRequestDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Optional<PermissionRequestDto>> getOne(Long id) {
        Optional<Permission> optional = this.getById(id);
        return ok().body(optional.map(mapper::toPermissionRequestDto));
    }

    @Override
    public ResponseEntity<List<PermissionGroupResponseDto>> getAllGroupedByModule() {
        List<Permission> permissions = this.getAll();
        List<PermissionGroupResponseDto> collect = permissions.stream()
                .map(mapper::toPermissionResponseDto)
                .collect(Collectors.groupingBy(com.jamesaworo.stocky.dto.response.auth.PermissionResponseDto::getModule))
                .entrySet()
                .stream()
                .map(entry -> new PermissionGroupResponseDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
        return ok().body(collect);
    }
}
