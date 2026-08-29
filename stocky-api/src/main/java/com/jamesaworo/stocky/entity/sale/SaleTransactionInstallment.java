package com.jamesaworo.stocky.entity.sale;

import com.jamesaworo.stocky.entity.sale.enums.SaleTransactionInstallmentType;
import lombok.*;

import javax.persistence.*;

import static com.jamesaworo.stocky.core.constants.Table.SALES_TRANSACTION_INSTALLMENT;

@Entity
@Table(name = SALES_TRANSACTION_INSTALLMENT)
@Getter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleTransactionInstallment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private SaleTransactionInstallmentType installmentType;

    @OneToOne(mappedBy = "installment", optional = false)
    private SaleTransaction transaction;

}
