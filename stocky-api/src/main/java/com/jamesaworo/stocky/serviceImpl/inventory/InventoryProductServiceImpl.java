package com.jamesaworo.stocky.serviceImpl.inventory;

import com.jamesaworo.stocky.dao.inventory.InventoryCategoryDao;
import com.jamesaworo.stocky.dao.inventory.InventoryProductDao;
import com.jamesaworo.stocky.dto.request.inventory.InventoryProductRequestDto;
import com.jamesaworo.stocky.dto.response.inventory.InventoryCategoryResponseDto;
import com.jamesaworo.stocky.dto.response.inventory.InventoryProductResponseDto;
import com.jamesaworo.stocky.entity.inventory.InventoryCategory;
import com.jamesaworo.stocky.entity.inventory.InventoryProduct;
import com.jamesaworo.stocky.exception.BadRequestException;
import com.jamesaworo.stocky.exception.ResourceNotFoundException;
import com.jamesaworo.stocky.service.inventory.InventoryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryProductServiceImpl implements InventoryProductService {
    private final InventoryProductDao productDao;
    private final InventoryCategoryDao categoryDao;

    @Override
    @Transactional
    public InventoryProductResponseDto create(InventoryProductRequestDto request) {
        if (productDao.existsBySku(request.getSku().trim())) {
            throw new BadRequestException("A product with this SKU already exists");
        }
        InventoryProduct product = new InventoryProduct();
        applyRequest(product, request);
        return toResponse(productDao.save(product));
    }

    @Override
    public InventoryProductResponseDto findById(Long id) {
        return toResponse(findEntity(id));
    }

    @Override
    public List<InventoryProductResponseDto> findAll(Long categoryId) {
        List<InventoryProduct> products = categoryId == null
                ? productDao.findAll()
                : productDao.findAllByCategoryId(categoryId);
        return products.stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public InventoryProductResponseDto update(Long id, InventoryProductRequestDto request) {
        InventoryProduct product = findEntity(id);
        String sku = request.getSku().trim();
        if (!product.getSku().equals(sku) && productDao.existsBySku(sku)) {
            throw new BadRequestException("A product with this SKU already exists");
        }
        applyRequest(product, request);
        return toResponse(productDao.save(product));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        productDao.delete(findEntity(id));
    }

    private InventoryProduct findEntity(Long id) {
        return productDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory product", id));
    }

    private void applyRequest(InventoryProduct product, InventoryProductRequestDto request) {
        InventoryCategory category = categoryDao.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Inventory category", request.getCategoryId()));
        product.setSku(request.getSku().trim());
        product.setName(request.getName().trim());
        product.setUnitPrice(request.getUnitPrice());
        product.setQuantityOnHand(request.getQuantityOnHand());
        product.setCategory(category);
    }

    private InventoryProductResponseDto toResponse(InventoryProduct product) {
        InventoryCategory category = product.getCategory();
        return new InventoryProductResponseDto(
                product.getId(), product.getSku(), product.getName(), product.getUnitPrice(), product.getQuantityOnHand(),
                new InventoryCategoryResponseDto(category.getId(), category.getName()));
    }
}
