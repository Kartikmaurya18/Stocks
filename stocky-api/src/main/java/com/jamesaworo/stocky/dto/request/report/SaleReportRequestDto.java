package com.jamesaworo.stocky.dto.request.report;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jamesaworo.stocky.entity.report.enums.SaleReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleReportRequestDto {
    private SaleReportType reportType;
    private LocalDate endDate;
    private LocalDate startDate;
}
