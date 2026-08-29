package com.jamesaworo.stocky.dto.response.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResponseDto {
    private Long id;
    private String name;
    private String description;
    private List<PermissionResponseDto> permissions;
    private String createdAt;
    private Boolean isActiveStatus;
}
