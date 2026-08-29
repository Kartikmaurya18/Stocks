package com.jamesaworo.stocky.dto.response.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jamesaworo.stocky.entity.auth.enums.AppModuleEnum;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionGroupResponseDto {
    private AppModuleEnum module;
    private List<PermissionResponseDto> permissions;
}
