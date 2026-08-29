package com.jamesaworo.stocky.serviceImpl.report;

import com.jamesaworo.stocky.core.params.BiParam;
import com.jamesaworo.stocky.core.params.DateRangeParam;
import com.jamesaworo.stocky.serviceImpl.report.export.DetailedSalesReportExporter;
import com.jamesaworo.stocky.serviceImpl.report.export.SummarizedSalesReportExporter;
import com.jamesaworo.stocky.service.report.ReportSaleService;
import com.jamesaworo.stocky.dto.request.report.SaleReportRequestDto;
import com.jamesaworo.stocky.entity.sale.SaleTransaction;
import com.jamesaworo.stocky.service.sale.SaleTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.jamesaworo.stocky.dao.sale.specification.SaleTransactionSearchSpecification.salesReportSpecification;

@Service
@RequiredArgsConstructor
public class ReportSaleServiceImpl implements ReportSaleService {

    private final SummarizedSalesReportExporter summarizedExporter;
    private final DetailedSalesReportExporter detailedSalesReportExporter;
    private final SaleTransactionService usecase;

    @Override
    public byte[] getDailyCollectionReport(SaleReportRequestDto request) {
        List<SaleTransaction> sales = this.usecase.findMany(salesReportSpecification(request));
        return this.getBytesByReportType(request, sales);
    }

    private byte[] getBytesByReportType(SaleReportRequestDto request, List<SaleTransaction> sales) {
        BiParam<List<SaleTransaction>, DateRangeParam> param = new BiParam<>(
                sales,
                new DateRangeParam(request.getStartDate(), request.getEndDate())
        );

        switch (request.getReportType()) {
            case SUMMARIZED:
                return this.summarizedExporter.export(param);
            case DETAILED:
                return this.detailedSalesReportExporter.export(param);
            default:
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Report Type");
        }
    }
}
