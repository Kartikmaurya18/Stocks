package com.jamesaworo.stocky.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jamesaworo.stocky.entity.auth.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleRequestDto {
    private Long id;
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    private String name;
    private String description;
    private List<PermissionRequestDto> permissions;
    private String createdAt;
    private Boolean isActiveStatus;

    public static RoleRequestDto toPartialRequest(Role role) {
        RoleRequestDto request = new RoleRequestDto();
        request.setId(role.getId());
        request.setName(role.getName());
        request.setDescription(role.getDescription());
        return request;
    }
}
