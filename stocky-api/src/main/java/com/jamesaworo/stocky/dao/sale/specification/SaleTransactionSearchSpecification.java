package com.jamesaworo.stocky.dao.sale.specification;

import com.jamesaworo.stocky.core.params.DateRangeParam;
import com.jamesaworo.stocky.features.company.data.request.CompanyCustomerRequest;
import com.jamesaworo.stocky.entity.company.CompanyCustomer;
import com.jamesaworo.stocky.dto.request.report.SaleReportRequestDto;
import com.jamesaworo.stocky.dto.request.sale.SaleTransactionInstallmentRequestDto;
import com.jamesaworo.stocky.dto.request.sale.SaleTransactionSearchRequestDto;
import com.jamesaworo.stocky.entity.sale.SaleTransaction;
import com.jamesaworo.stocky.entity.sale.SaleTransactionInstallment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static com.jamesaworo.stocky.core.predicates.SearchPredicates.*;
import static com.jamesaworo.stocky.core.utils.Util.parseToLocalDate;
import static org.springframework.util.ObjectUtils.isEmpty;


@Component
public class SaleTransactionSearchSpecification {

    private static Root<SaleTransaction> mainRoot;


    public static Specification<SaleTransaction> salesSaleTransactionSpecification(SaleTransactionSearchRequestDto request) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            mainRoot = root;
            List<Predicate> predicates = new ArrayList<>();

            // id
            if (!isEmpty(request) && !isEmpty(request.getId())) {
                predicates.add(criteriaBuilder.equal(root.get("id"), request.getId()));
                return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            }

            // reference and serial
            if (!isEmpty(request) && !isEmpty(request.getReference()) && !isEmpty(request.getSerial())) {
                referenceAndSerialPredicate(predicates, request, criteriaBuilder);
                return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            }

            // serial
            if (!isEmpty(request) && !isEmpty(request.getSerial())) {
                predicates.add(serialPredicate(criteriaBuilder, request.getSerial()));
            }

            // single date
            if (!isEmpty(request) && !isEmpty(request.getDate())) {
                predicates.add(createdAtPredicate(criteriaBuilder, parseToLocalDate(request.getDate()), mainRoot));
            }

            // customer
            if (!isEmpty(request) && !isEmpty(request.getCustomer()) && !isEmpty(request.getCustomer().getId())) {
                predicates.add(joinOnCustomerPredicate(criteriaBuilder, request.getCustomer()));
            }

            // user
            if (!isEmpty(request) && !isEmpty(request.getUser()) && !isEmpty(request.getUser().getUsername())) {
                predicates.add(createdByPredicate(criteriaBuilder, request.getUser().getUsername(), mainRoot));
            }

            // installment payment
            if (!isEmpty(request) && !isEmpty(request.getInstallment())) {
                predicates.add(joinOnInstallmentPredicate(criteriaBuilder, request.getInstallment()));
            }

            // date range
            if (!isEmpty(request) && !isEmpty(request.getDateRange())) {
                predicates.addAll(dateRangeParamPredicates(criteriaBuilder, request.getDateRange(), mainRoot));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }


    public static Specification<SaleTransaction> salesReportSpecification(SaleReportRequestDto request) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            mainRoot = root;
            List<Predicate> predicates = new ArrayList<>();

            // date range
            if (!isEmpty(request) && !isEmpty(request.getStartDate()) && !isEmpty(request.getEndDate())) {
                predicates.addAll(dateRangeParamPredicates(
                        criteriaBuilder,
                        new DateRangeParam(request.getStartDate(), request.getEndDate()),
                        mainRoot)
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
        };
    }

    private static void referenceAndSerialPredicate(List<Predicate> predicates, SaleTransactionSearchRequestDto request, CriteriaBuilder criteriaBuilder) {
        Predicate productNamePredicate = referencePredicate(criteriaBuilder, request.getReference());
        Predicate brandNamePredicate = serialPredicate(criteriaBuilder, request.getSerial());

        predicates.add(criteriaBuilder.and(productNamePredicate, brandNamePredicate));
    }

    private static Predicate referencePredicate(CriteriaBuilder criteriaBuilder, String reference) {
        return criteriaBuilder.like(criteriaBuilder.lower(mainRoot.get("reference")), "%" + reference.toLowerCase() + "%");
    }

    private static Predicate serialPredicate(CriteriaBuilder criteriaBuilder, String serial) {
        return criteriaBuilder.like(criteriaBuilder.lower(mainRoot.get("serial")), "%" + serial.toLowerCase() + "%");
    }

    private static Predicate joinOnCustomerPredicate(CriteriaBuilder criteriaBuilder, CompanyCustomerRequest customer) {
        Join<SaleTransaction, CompanyCustomer> customerJoin = mainRoot.join("customer");
        return criteriaBuilder.equal(customerJoin.get("id"), customer.getId());
    }

    private static Predicate joinOnInstallmentPredicate(CriteriaBuilder criteriaBuilder, SaleTransactionInstallmentRequestDto request) {
        Join<SaleTransaction, SaleTransactionInstallment> installmentJoin = mainRoot.join("installment");
        return criteriaBuilder.equal(installmentJoin.get("id"), request.getId());
    }

}
