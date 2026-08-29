package com.jamesaworo.stocky.mapper;

import com.jamesaworo.stocky.dto.request.stock.StockExpensesRequestDto;
import com.jamesaworo.stocky.dto.request.stock.StockItemRequestDto;
import com.jamesaworo.stocky.dto.request.stock.StockPriceRequestDto;
import com.jamesaworo.stocky.dto.request.stock.StockRequestDto;
import com.jamesaworo.stocky.dto.request.stock.StockSettlementRequestDto;
import com.jamesaworo.stocky.entity.stock.Stock;
import com.jamesaworo.stocky.entity.stock.StockExpenses;
import com.jamesaworo.stocky.entity.stock.StockItem;
import com.jamesaworo.stocky.entity.stock.StockPrice;
import com.jamesaworo.stocky.entity.stock.StockSettlement;

import java.util.stream.Collectors;

public class StockMapper {

    public static Stock toEntity(StockRequestDto dto) {
        if (dto == null) return null;
        Stock entity = new Stock();
        entity.setId(dto.getId());
        entity.setCodePrefix(dto.getCode() != null ? dto.getCode().substring(0, Math.min(3, dto.getCode().length())) : null);
        try {
            entity.setCode(dto.getCode() != null ? Integer.parseInt(dto.getCode().replaceAll("[^0-9]", "")) : null);
        } catch (Exception e) {}
        entity.setIsGroupedExpenses(dto.getIsGroupedExpenses());
        entity.setIsGroupedSettlement(dto.getIsGroupedSettlement());
        entity.setStatus(dto.getStatus());
        if (dto.getSettlement() != null) {
            entity.setSettlement(toEntity(dto.getSettlement()));
        }
        if (dto.getExpenses() != null) {
            entity.setExpenses(dto.getExpenses().stream().map(StockMapper::toEntity).collect(Collectors.toList()));
        }
        if (dto.getStockItems() != null) {
            entity.setStockItems(dto.getStockItems().stream().map(StockMapper::toEntity).collect(Collectors.toList()));
        }
        return entity;
    }

    public static StockRequestDto toDto(Stock entity) {
        if (entity == null) return null;
        StockRequestDto dto = new StockRequestDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCodePrefix() != null ? entity.getCodePrefix() + entity.getCode() : String.valueOf(entity.getCode()));
        dto.setIsGroupedExpenses(entity.getIsGroupedExpenses());
        dto.setIsGroupedSettlement(entity.getIsGroupedSettlement());
        dto.setStatus(entity.getStatus());
        if (entity.getSettlement() != null) {
            dto.setSettlement(toDto(entity.getSettlement()));
        }
        if (entity.getExpenses() != null) {
            dto.setExpenses(entity.getExpenses().stream().map(StockMapper::toDto).collect(Collectors.toSet()));
        }
        if (entity.getStockItems() != null) {
            dto.setStockItems(entity.getStockItems().stream().map(StockMapper::toDto).collect(Collectors.toSet()));
        }
        return dto;
    }

    public static StockExpenses toEntity(StockExpensesRequestDto dto) {
        if (dto == null) return null;
        StockExpenses entity = new StockExpenses();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setAmount(dto.getAmount());
        return entity;
    }

    public static StockExpensesRequestDto toDto(StockExpenses entity) {
        if (entity == null) return null;
        StockExpensesRequestDto dto = new StockExpensesRequestDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setAmount(entity.getAmount());
        return dto;
    }

    public static StockItem toEntity(StockItemRequestDto dto) {
        if (dto == null) return null;
        StockItem entity = new StockItem();
        entity.setId(dto.getId());
        entity.setProductQuantity(dto.getProductQuantity());
        entity.setProductQuantitySold(dto.getProductQuantitySold());
        entity.setOther(dto.getOther());
        if (dto.getSettlement() != null) entity.setSettlement(toEntity(dto.getSettlement()));
        if (dto.getPrice() != null) entity.setStockPrice(toEntity(dto.getPrice()));
        if (dto.getExpenses() != null) entity.setExpenses(dto.getExpenses().stream().map(StockMapper::toEntity).collect(Collectors.toList()));
        return entity;
    }

    public static StockItemRequestDto toDto(StockItem entity) {
        if (entity == null) return null;
        StockItemRequestDto dto = new StockItemRequestDto();
        dto.setId(entity.getId());
        dto.setProductQuantity(entity.getProductQuantity());
        dto.setProductQuantitySold(entity.getProductQuantitySold());
        dto.setOther(entity.getOther());
        if (entity.getSettlement() != null) dto.setSettlement(toDto(entity.getSettlement()));
        if (entity.getStockPrice() != null) dto.setPrice(toDto(entity.getStockPrice()));
        if (entity.getExpenses() != null) dto.setExpenses(entity.getExpenses().stream().map(StockMapper::toDto).collect(Collectors.toList()));
        return dto;
    }

    public static StockPrice toEntity(StockPriceRequestDto dto) {
        if (dto == null) return null;
        StockPrice entity = new StockPrice();
        entity.setId(dto.getId());
        entity.setCostPrice(dto.getCostPrice());
        entity.setMarkupPercent(dto.getMarkupPercent());
        entity.setSellingPrice(dto.getSellingPrice());
        entity.setExpensesAmount(dto.getExpensesAmount());
        return entity;
    }

    public static StockPriceRequestDto toDto(StockPrice entity) {
        if (entity == null) return null;
        StockPriceRequestDto dto = new StockPriceRequestDto();
        dto.setId(entity.getId());
        dto.setCostPrice(entity.getCostPrice());
        dto.setMarkupPercent(entity.getMarkupPercent());
        dto.setSellingPrice(entity.getSellingPrice());
        dto.setExpensesAmount(entity.getExpensesAmount());
        return dto;
    }

    public static StockSettlement toEntity(StockSettlementRequestDto dto) {
        if (dto == null) return null;
        StockSettlement entity = new StockSettlement();
        entity.setId(dto.getId());
        entity.setAmount(dto.getAmount());
        entity.setPaid(dto.getPaid());
        entity.setBalance(dto.getBalance());
        return entity;
    }

    public static StockSettlementRequestDto toDto(StockSettlement entity) {
        if (entity == null) return null;
        StockSettlementRequestDto dto = new StockSettlementRequestDto();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setPaid(entity.getPaid());
        dto.setBalance(entity.getBalance());
        return dto;
    }
}
