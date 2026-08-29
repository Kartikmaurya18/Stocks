package com.jamesaworo.stocky.dto.request.sale;

import com.jamesaworo.stocky.entity.sale.SaleTransaction;
import com.jamesaworo.stocky.entity.sale.SaleTransactionItem;
import lombok.Data;

import static com.jamesaworo.stocky.core.constants.ReportConstant.EMPTY;
import static com.jamesaworo.stocky.core.utils.Util.formatDate;
import static com.jamesaworo.stocky.core.utils.Util.formatTime;
import static java.lang.String.valueOf;
import static org.springframework.util.ObjectUtils.isEmpty;

@Data
public class SaleTransactionDailyReportDataDto {
    private String customer;
    private String date;
    private String time;
    private String cashier;
    private String receiptSerial;
    private Double taxAmount;
    private Double discountAmount;
    private Double subTotalAmount;
    private Double grandTotalAmount;
    private String qty;
    private String paymentMethod;


    public static SaleTransactionDailyReportDataDto mapFromTransaction(SaleTransaction transaction) {
        SaleTransactionDailyReportDataDto reportData = new SaleTransactionDailyReportDataDto();
        reportData.setDate(formatDate(transaction.getDate()).toUpperCase());
        reportData.setTime(formatTime(transaction.getTime()).toUpperCase());
        reportData.setCashier(!isEmpty(transaction.getCreatedBy()) ? transaction.getCreatedBy().toUpperCase() : EMPTY);
        reportData.setCustomer(!isEmpty(transaction.getCustomer()) ? transaction.getCustomer().getFullName().toUpperCase() : EMPTY);
        reportData.setReceiptSerial(transaction.getSerial().toUpperCase());
        reportData.setTaxAmount(transaction.getAmount().getTaxTotal());
        reportData.setDiscountAmount(transaction.getAmount().getDiscountTotal());
        reportData.setSubTotalAmount(transaction.getAmount().getSubTotal());
        reportData.setGrandTotalAmount(transaction.getAmount().getGrandTotal());
        reportData.setQty(valueOf(transaction.getItems().stream().mapToInt(SaleTransactionItem::getQuantity).sum()));
        reportData.setPaymentMethod(transaction.getPaymentOption().getTitle().toUpperCase());
        return reportData;
    }
}
