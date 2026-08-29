package com.jamesaworo.stocky.controller.sale;

import com.jamesaworo.stocky.service.sale.SaleTransactionService;
import com.jamesaworo.stocky.dto.request.sale.SaleTransactionRequestDto;
import com.jamesaworo.stocky.dto.request.sale.SaleTransactionSearchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jamesaworo.stocky.core.constants.Global.SALES_TRANSACTION_ENDPOINT;
import static com.jamesaworo.stocky.core.constants.ReportConstant.*;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = SALES_TRANSACTION_ENDPOINT)
@RequiredArgsConstructor
public class SaleTransactionController {

    private final SaleTransactionService service;

    @PostMapping("/create")
    public ResponseEntity<SaleTransactionRequestDto> saveTransaction(@RequestBody SaleTransactionRequestDto transaction) {
        return ok(this.service.saveTransaction(transaction));
    }

    @GetMapping("/search-receipt")
    public ResponseEntity<byte[]> searchReceiptBySerial(
            @RequestParam(value = "serial") String serial
    ) {
        byte[] bytes = this.service.searchReceiptBySerial(serial);
        return ok()
                .header(HttpHeaders.CONTENT_TYPE, PDF_CONTENT_TYPE)
                .header(HttpHeaders.CONTENT_DISPOSITION, RECEIPT_FILE_NAME)
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(bytes.length))
                .body(bytes);
    }

    @PostMapping(value = "/search-report")
    public ResponseEntity<byte[]> searchTransactionReport(@RequestBody SaleTransactionSearchRequestDto request) {
        byte[] pdfData = this.service.searchSaleTransactionReport(request);
        return ok()
                .header(HttpHeaders.CONTENT_TYPE, PDF_CONTENT_TYPE)
                .header(HttpHeaders.CONTENT_DISPOSITION, REPORT_FILE_NAME)
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(pdfData.length))
                .body(pdfData);
    }

}
