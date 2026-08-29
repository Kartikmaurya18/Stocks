package com.jamesaworo.stocky.mapper;

import com.jamesaworo.stocky.dto.request.sale.*;
import com.jamesaworo.stocky.entity.company.CompanyCustomer;
import com.jamesaworo.stocky.entity.company.CompanyPaymentOption;
import com.jamesaworo.stocky.entity.product.Product;
import com.jamesaworo.stocky.entity.sale.*;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.stream.Collectors;

import static com.jamesaworo.stocky.core.utils.Util.*;
import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class SaleMapper {

    public SaleTransaction toModel(SaleTransactionRequestDto request) {
        SaleTransaction transaction = new SaleTransaction();
        transaction.setId(request.getId());
        transaction.setDate(!isEmpty(request.getDate()) ? parseToLocalDate(request.getDate()) : null);
        transaction.setTime(!isEmpty(request.getTime()) ? LocalTime.parse(request.getTime()) : null);
        transaction.setReference(request.getReference());
        transaction.setSerial(request.getSerial());

        if (!isEmpty(request.getCustomer())) {
            transaction.setCustomer(new CompanyCustomer(request.getCustomer().getId()));
        } else {
            transaction.setCustomer(null);
        }

        if (!isEmpty(request.getPaymentOption()) && !isEmpty(request.getPaymentOption().getId())) {
            transaction.setPaymentOption(new CompanyPaymentOption(request.getPaymentOption().getId()));
        }

        if (!isEmpty(request.getAmount())) {
            SaleTransactionAmount amount = new SaleTransactionAmount();
            amount.setGrandTotal(request.getAmount().getGrandTotal());
            amount.setDiscountTotal(request.getAmount().getDiscountTotal());
            amount.setSubTotal(request.getAmount().getSubTotal());
            amount.setTaxTotal(request.getAmount().getTaxTotal());
            amount.setId(request.getAmount().getId());
            transaction.setAmount(amount);
        }

        if (!isEmpty(request.getInstallment())) {
            SaleTransactionInstallment installment = new SaleTransactionInstallment();
            installment.setId(request.getInstallment().getId());
            installment.setInstallmentType(request.getInstallment().getInstallmentType());
            transaction.setInstallment(installment);
        }

        transaction.setOther(request.getOther());
        return transaction;
    }

    public SaleTransactionRequestDto toRequest(SaleTransaction transaction) {
        SaleTransactionRequestDto request = new SaleTransactionRequestDto();
        request.setId(transaction.getId());
        request.setReference(transaction.getReference());
        request.setSerial(transaction.getSerial());
        request.setTime(formatTime(transaction.getTime()));
        request.setDate(formatDate(transaction.getDate()));
        request.setOther(transaction.getOther());
        return request;
    }

    public SaleTransactionItem toModel(SaleTransactionItemRequestDto request) {
        SaleTransactionItem item = new SaleTransactionItem();
        item.setId(request.getId());
        if(request.getProduct() != null) {
            item.setProduct(new Product(request.getProduct().getId()));
        }
        item.setQuantity(request.getQuantity());
        item.setGrandTotal(request.getGrandTotal());
        item.setDiscount(request.getDiscount());
        item.setTax(request.getTax());
        item.setSubTotal(request.getSubTotal());
        item.setPrice(request.getPrice());
        return item;
    }
}
