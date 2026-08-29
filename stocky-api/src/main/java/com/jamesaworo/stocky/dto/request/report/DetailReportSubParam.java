package com.jamesaworo.stocky.dto.request.report;

import com.jamesaworo.stocky.entity.sale.SaleTransactionItem;
import lombok.Data;

@Data
public class DetailReportSubParam {
    private String itemName;
    private String itemQty;
    private Double itemSubTotal;
    private Double itemTotal;
    private Double itemPrice;

    public static DetailReportSubParam fromTransactionItem(SaleTransactionItem saleItem) {
        DetailReportSubParam param = new DetailReportSubParam();
        param.setItemName(saleItem.getProduct().getBasic().title());
        param.setItemQty(saleItem.getQuantity().toString());
        param.setItemSubTotal(saleItem.getSubTotal());
        param.setItemTotal(saleItem.getGrandTotal());
        param.setItemPrice(saleItem.getPrice());
        return param;
    }
}
