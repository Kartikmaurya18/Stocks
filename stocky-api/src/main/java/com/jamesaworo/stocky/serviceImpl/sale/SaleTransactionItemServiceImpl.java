package com.jamesaworo.stocky.serviceImpl.sale;

import com.jamesaworo.stocky.dao.sale.SaleTransactionItemDao;
import com.jamesaworo.stocky.entity.sale.SaleTransactionItem;
import com.jamesaworo.stocky.service.sale.SaleTransactionItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SaleTransactionItemServiceImpl implements SaleTransactionItemService {
    private final SaleTransactionItemDao repository;

    @Override
    public SaleTransactionItem save(SaleTransactionItem item) {
        return this.repository.save(item);
    }

    @Override
    public Optional<SaleTransactionItem> find(Long id) {
        return this.repository.findById(id);
    }
}
