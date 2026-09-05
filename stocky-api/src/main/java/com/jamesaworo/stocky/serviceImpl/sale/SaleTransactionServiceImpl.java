package com.jamesaworo.stocky.serviceImpl.sale;

import com.jamesaworo.stocky.core.constants.Setting;
import com.jamesaworo.stocky.core.params.PageSearchRequest;
import com.jamesaworo.stocky.core.params.PageSearchResult;
import com.jamesaworo.stocky.core.utils.Util;
import com.jamesaworo.stocky.features.company.domain.entity.CompanyCustomer;
import com.jamesaworo.stocky.features.company.domain.usecase.ICompanyCustomerUsecase;
import com.jamesaworo.stocky.features.product.domain.usecase.IProductUsecase;
import com.jamesaworo.stocky.dao.sale.SaleTransactionDao;
import com.jamesaworo.stocky.dto.request.sale.SaleTransactionRequestDto;
import com.jamesaworo.stocky.dto.request.sale.SaleTransactionItemRequestDto;
import com.jamesaworo.stocky.dto.request.sale.SaleTransactionSearchRequestDto;
import com.jamesaworo.stocky.entity.sale.SaleTransaction;
import com.jamesaworo.stocky.entity.sale.SaleTransactionItem;
import com.jamesaworo.stocky.service.sale.SaleTransactionAmountService;
import com.jamesaworo.stocky.service.sale.SaleTransactionInstallmentService;
import com.jamesaworo.stocky.service.sale.SaleTransactionItemService;
import com.jamesaworo.stocky.service.sale.SaleTransactionService;
import com.jamesaworo.stocky.serviceImpl.sale.export.SalesReceiptExporter;
import com.jamesaworo.stocky.serviceImpl.sale.export.SalesReportExporter;
import com.jamesaworo.stocky.features.settings.data.usecases_impl.SettingStockUsecase;
import com.jamesaworo.stocky.mapper.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static com.jamesaworo.stocky.core.params.PageParam.toPageSearchResult;
import static com.jamesaworo.stocky.core.utils.Util.receiptSerial;
import static com.jamesaworo.stocky.dao.sale.specification.SaleTransactionSearchSpecification.salesSaleTransactionSpecification;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class SaleTransactionServiceImpl implements SaleTransactionService {

    private static final Logger log = LoggerFactory.getLogger(SaleTransactionServiceImpl.class);

    private final SaleMapper mapper;
    private final SaleTransactionDao repository;
    private final SaleTransactionAmountService amountUsecase;
    private final SaleTransactionInstallmentService installmentUsecase;
    private final SaleTransactionItemService itemUsecase;
    private final ICompanyCustomerUsecase customerUsecase;
    private final IProductUsecase productUsecase;
    private final SettingStockUsecase stockSetting;
    private final SalesReceiptExporter receiptExporter;
    private final SalesReportExporter reportExporter;


    @Override
    @Transactional
    public SaleTransactionRequestDto saveTransaction(SaleTransactionRequestDto request) {
        SaleTransaction transaction = mapper.toModel(request);
        List<SaleTransactionItem> transactionItems = request.getItems().stream().map(mapper::toModel).collect(toList());
        SaleTransaction savedTransaction = this.save(transaction, transactionItems);
        return mapper.toRequest(savedTransaction);
    }

    @Override
    public PageSearchResult<List<SaleTransactionRequestDto>> searchMany(PageSearchRequest<SaleTransactionSearchRequestDto> request) {
        Page<SaleTransaction> page = this.findMany(salesSaleTransactionSpecification(request.getSearchRequest()), request.getPage().toPageable());
        List<SaleTransactionRequestDto> requests = page.getContent().stream().map(mapper::toRequest).collect(toList());
        return toPageSearchResult(requests, page);
    }

    @Override
    public List<SaleTransactionRequestDto> searchMany(SaleTransactionSearchRequestDto request) {
        List<SaleTransaction> sales = this.findMany(salesSaleTransactionSpecification(request));
        return sales.stream().map(mapper::toRequest).collect(toList());
    }

    @Override
    public Optional<SaleTransactionRequestDto> getOne(Long id) {
        Optional<SaleTransaction> optional = this.findOne(id);
        return optional.map(mapper::toRequest);
    }

    @Override
    public Optional<byte[]> getTransactionReceipt(String reference, String serial) {
        Optional<SaleTransaction> optional = this.findOne(reference, serial);
        return optional.map(receiptExporter::export);
    }

    @Override
    public byte[] searchReceiptBySerial(String serial) {
        Optional<SaleTransaction> optional = this.findOne(serial);
        Optional<byte[]> optionalBytes = optional.map(receiptExporter::export);
        return optionalBytes.orElseThrow(() -> new RuntimeException("Receipt not found"));
    }

    @Override
    public byte[] searchSaleTransactionReport(SaleTransactionSearchRequestDto request) {
        List<SaleTransaction> sales = this.findMany(salesSaleTransactionSpecification(request));
        return this.reportExporter.export(sales);
    }

    @Override
    @Transactional
    public SaleTransaction save(SaleTransaction transaction, List<SaleTransactionItem> transactionItems) {
        try {
            this.findAndSetCustomer(transaction);
            this.findAndSetEmployee(transaction);
            this.saveAndSetReceiptDateAndTime(transaction);

            this.amountUsecase.saveAndSet(transaction);
            this.installmentUsecase.saveAndSet(transaction);

            SaleTransaction savedTransaction = this.repository.save(transaction);
            this.saveAndSetReceiptReferenceSerial(savedTransaction);
            this.saveAndSetItems(savedTransaction, transactionItems);
            this.deductProductQuantityAfterSales(transactionItems);

            return savedTransaction;
        } catch (Exception e) {
            log.error("Error while saving the transaction.", e);
            throw new RuntimeException("Error while saving the transaction.", e);
        }
    }

    private void findAndSetCustomer(SaleTransaction transaction) {
        if (!isEmpty(transaction.getCustomer()) && transaction.getCustomer().getId() != null) {
            Optional<CompanyCustomer> optionalCustomer = this.customerUsecase.findOne(transaction.getCustomer().getId());
            optionalCustomer.ifPresent(transaction::setCustomer);
        } else {
            transaction.setCustomer(null);
        }
    }

    private void findAndSetEmployee(SaleTransaction transaction) {
        /* todo:: add user to transaction not employee */
    }

    private void saveAndSetItems(SaleTransaction transaction, List<SaleTransactionItem> items) {
        if (!isEmpty(items)) {
            for (SaleTransactionItem item : items) {
                item.setTransaction(transaction);
                SaleTransactionItem save = itemUsecase.save(item);
                item.setId(save.getId());
            }
        }
    }

    private void saveAndSetReceiptReferenceSerial(SaleTransaction transaction) {
        String id = transaction.getId() != null ? transaction.getId().toString() : "";
        transaction.setReference(Util.randomNumeric(10) + id);
        String serial = receiptSerial(5) + id;
        transaction.setSerial(serial);
        this.repository.save(transaction);
    }

    private void saveAndSetReceiptDateAndTime(SaleTransaction transaction) {
        transaction.setDate(LocalDate.now());
        transaction.setTime(LocalTime.now());
    }

    private void deductProductQuantityAfterSales(List<SaleTransactionItem> items) {
        Boolean asBool = this.stockSetting.getAsBool(Setting.SETTING_STOCK_ENABLE_STOCK);
        if (!asBool) return;

        for (SaleTransactionItem item : items) {
            Optional<SaleTransactionItem> optionalItem = this.itemUsecase.find(item.getId());
            optionalItem.ifPresent(savedItem -> {
                com.jamesaworo.stocky.features.product.domain.entity.Product product =
                        com.jamesaworo.stocky.features.product.domain.entity.Product.builder()
                                .id(savedItem.getProduct().getId())
                                .build();
                this.productUsecase.deductProductQuantityAfterSales(product, savedItem.getQuantity());
            });
        }
    }

    @Override
    public Page<SaleTransaction> findMany(Specification<SaleTransaction> specification, Pageable pageable) {
        return this.repository.findAll(specification, pageable);
    }

    @Override
    public List<SaleTransaction> findMany(Specification<SaleTransaction> specification) {
        return this.repository.findAll(specification);
    }

    @Override
    public Optional<SaleTransaction> findOne(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Optional<SaleTransaction> findOne(String reference, String serial) {
        return this.repository.findByReferenceEqualsAndSerialEquals(reference, serial);
    }

    @Override
    public Optional<SaleTransaction> findOne(String serial) {
        return this.repository.findBySerialEqualsIgnoreCase(serial);
    }
}
