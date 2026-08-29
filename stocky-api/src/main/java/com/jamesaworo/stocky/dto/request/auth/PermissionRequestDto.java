package com.jamesaworo.stocky.dto.request.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jamesaworo.stocky.entity.auth.enums.AppModuleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PermissionRequestDto {
	private Long id;
	private String name;
	private AppModuleEnum module;
}
