package com.jamesaworo.stocky.serviceImpl.inventory;

import com.jamesaworo.stocky.dao.inventory.InventoryCategoryDao;
import com.jamesaworo.stocky.dao.inventory.InventoryProductDao;
import com.jamesaworo.stocky.dto.request.inventory.InventoryCategoryRequestDto;
import com.jamesaworo.stocky.dto.response.inventory.InventoryCategoryResponseDto;
import com.jamesaworo.stocky.entity.inventory.InventoryCategory;
import com.jamesaworo.stocky.exception.BadRequestException;
import com.jamesaworo.stocky.exception.ResourceNotFoundException;
import com.jamesaworo.stocky.service.inventory.InventoryCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryCategoryServiceImpl implements InventoryCategoryService {
    private final InventoryCategoryDao categoryDao;
    private final InventoryProductDao productDao;

    @Override
    @Transactional
    public InventoryCategoryResponseDto create(InventoryCategoryRequestDto request) {
        String name = request.getName().trim();
        if (categoryDao.existsByNameIgnoreCase(name)) {
            throw new BadRequestException("A category with this name already exists");
        }
        InventoryCategory category = new InventoryCategory();
        category.setName(name);
        return toResponse(categoryDao.save(category));
    }

    @Override
    public InventoryCategoryResponseDto findById(Long id) {
        return toResponse(findEntity(id));
    }

    @Override
    public List<InventoryCategoryResponseDto> findAll() {
        return categoryDao.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public InventoryCategoryResponseDto update(Long id, InventoryCategoryRequestDto request) {
        InventoryCategory category = findEntity(id);
        String name = request.getName().trim();
        if (!category.getName().equalsIgnoreCase(name) && categoryDao.existsByNameIgnoreCase(name)) {
            throw new BadRequestException("A category with this name already exists");
        }
        category.setName(name);
        return toResponse(categoryDao.save(category));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        InventoryCategory category = findEntity(id);
        if (!productDao.findAllByCategoryId(id).isEmpty()) {
            throw new BadRequestException("A category containing products cannot be deleted");
        }
        categoryDao.delete(category);
    }

    private InventoryCategory findEntity(Long id) {
        return categoryDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inventory category", id));
    }

    private InventoryCategoryResponseDto toResponse(InventoryCategory category) {
        return new InventoryCategoryResponseDto(category.getId(), category.getName());
    }
}
