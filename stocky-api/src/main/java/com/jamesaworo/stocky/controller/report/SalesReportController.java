package com.jamesaworo.stocky.controller.report;

import com.jamesaworo.stocky.service.report.ReportSaleService;
import com.jamesaworo.stocky.dto.request.report.SaleReportRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;
import static com.jamesaworo.stocky.core.constants.ReportConstant.PDF_CONTENT_TYPE;
import static com.jamesaworo.stocky.core.constants.ReportConstant.REPORT_FILE_NAME;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = API_PREFIX + "/report/sales")
@RequiredArgsConstructor
public class SalesReportController {

    private final ReportSaleService service;

    @PostMapping(value = "/collection-report")
    public ResponseEntity<byte[]> searchTransactionReport(@RequestBody SaleReportRequestDto request) {
        byte[] bytes = this.service.getDailyCollectionReport(request);
        return ok()
                .header(HttpHeaders.CONTENT_TYPE, PDF_CONTENT_TYPE)
                .header(HttpHeaders.CONTENT_DISPOSITION, REPORT_FILE_NAME)
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(bytes.length))
                .body(bytes);
    }

}
