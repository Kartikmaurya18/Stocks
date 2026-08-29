package com.jamesaworo.stocky.service.report;

import com.jamesaworo.stocky.dto.request.report.SaleReportRequestDto;

public interface ReportSaleService {
    byte[] getDailyCollectionReport(SaleReportRequestDto request);
}
