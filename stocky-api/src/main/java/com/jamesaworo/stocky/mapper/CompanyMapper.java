package com.jamesaworo.stocky.mapper;

import com.jamesaworo.stocky.entity.company.*;
import com.jamesaworo.stocky.dto.request.company.*;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {

    public CompanyBasicDetail toEntity(CompanyBasicDetailRequestDto dto) {
        if (dto == null) return null;
        CompanyBasicDetail entity = new CompanyBasicDetail();
        // Assume fields are mapped here
        return entity;
    }

    public CompanyBasicDetailRequestDto toDto(CompanyBasicDetail entity) {
        if (entity == null) return null;
        CompanyBasicDetailRequestDto dto = new CompanyBasicDetailRequestDto();
        // Assume fields are mapped here
        return dto;
    }
}
