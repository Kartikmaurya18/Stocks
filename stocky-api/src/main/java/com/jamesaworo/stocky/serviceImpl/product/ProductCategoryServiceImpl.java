package com.jamesaworo.stocky.serviceImpl.product;

import com.jamesaworo.stocky.core.constants.enums.Template;
import com.jamesaworo.stocky.core.utils.FileUtil;
import com.jamesaworo.stocky.dao.product.ProductCategoryDao;
import com.jamesaworo.stocky.dto.request.product.ProductCategoryRequestDto;
import com.jamesaworo.stocky.entity.product.ProductCategory;
import com.jamesaworo.stocky.mapper.ProductMapper;
import com.jamesaworo.stocky.service.product.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static com.jamesaworo.stocky.core.constants.Exception.RECORD_NOT_FOUND;
import static com.jamesaworo.stocky.core.constants.Exception.REQUIRED_ID;
import static com.jamesaworo.stocky.core.constants.ReportConstant.UNEXPECTED_FILE_TYPE;
import static com.jamesaworo.stocky.core.constants.enums.FileType.EXCEL;
import static com.jamesaworo.stocky.core.utils.FileUtil.isFileType;
import static com.jamesaworo.stocky.core.utils.FileUtil.writeProductScrapContentToFile;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCategoryServiceImpl implements ProductCategoryService {

    public static final String DUPLICATE = "CATEGORY WITH SAME TITLE ALREADY EXIST";
    public static final String REQUIRE_CATEGORY_ID = "Product category ID is required";
    private static final String FAILED_TO_DELETE = "UNABLE TO DELETE, THIS ITEM IS CURRENTLY IN USE";
    
    private final ProductCategoryDao repository;

    @Override
    public ResponseEntity<ProductCategoryRequestDto> find(Long id) {
        Optional<ProductCategory> optional = this.repository.findById(id);
        return optional.map(category -> ok(ProductMapper.toProductCategoryDto(category))).orElse(notFound().build());
    }

    @Override
    public ResponseEntity<List<ProductCategoryRequestDto>> findMany() {
        List<ProductCategory> all = this.repository.findAll();
        List<ProductCategoryRequestDto> collect = all.stream().map(ProductMapper::toProductCategoryDto).collect(Collectors.toList());
        return ok().body(collect);
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductCategoryRequestDto>> save(ProductCategoryRequestDto request) {
        var model = ProductMapper.toProductCategoryModel(request);
        throwIfDuplicateEntry(model);
        ProductCategory saved = this.repository.save(model);
        return ok().body(Optional.of(ProductMapper.toProductCategoryDto(saved)));
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductCategoryRequestDto>> update(ProductCategoryRequestDto request) {
        this.throwIfRequestNotValid(request);
        return this.save(request);
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<Boolean>> remove(Long id) {
        Optional<ProductCategory> optionalProductCategory = this.repository.findById(id);
        Optional<Boolean> optional = removeProductCategoryIfPresentAndHasNoProduct(optionalProductCategory);

        return ok().body(optional.map(value -> {
            if (!value) {
                throw new ResponseStatusException(BAD_REQUEST, FAILED_TO_DELETE);
            }
            return value;
        }));
    }

    @Override
    public ResponseEntity<List<ProductCategoryRequestDto>> search(String term) {
        if (term.isEmpty()) {
            return ok().body(new ArrayList<>());
        }

        List<ProductCategory> categories = this.repository.findAllByTitleContainsIgnoreCase(term);
        List<ProductCategoryRequestDto> requests = categories.stream().map(ProductMapper::toProductCategoryDto).collect(Collectors.toList());
        return new ResponseEntity<>(requests, OK);
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<Boolean>> toggleActiveStatus(Long id) {
        Optional<ProductCategory> optional = this.repository.findById(id);
        return ok().body(optional.map(value -> {
            boolean status = !value.getIsActiveStatus();
            int count = this.repository.updateIsActiveStatus(status, value.getId());
            return count == 1;
        }));
    }

    @Override
    public ResponseEntity<Resource> downloadTemplate() throws IOException {
        Resource resource = FileUtil.findResource(Template.PRODUCT_CATEGORY_UPLOAD);
        Path path = resource.getFile().toPath();
        return ok().header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Override
    @Transactional
    public ResponseEntity<?> uploadTemplate(MultipartFile file) {
        if (!isFileType(file, EXCEL)) {
            throw new ResponseStatusException(BAD_REQUEST, format(UNEXPECTED_FILE_TYPE, EXCEL.extension()));
        }

        Map<String, String> map = new HashMap<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(1);
            List<ProductCategory> categoryList = new ArrayList<>();
            Iterator<Row> iterator = sheet.rowIterator();
            for (int index = 0; index < sheet.getPhysicalNumberOfRows(); index++) {
                if (index > 0) {
                    XSSFRow row = (XSSFRow) sheet.getRow(index);
                    if (FileUtil.isEmptyRow(row)) continue;

                    String title = row.getCell(0).getStringCellValue().trim();
                    String description = "";
                    String parent = "";
                    if (row.getCell(1) != null && row.getCell(1).getStringCellValue() != null) {
                        description = row.getCell(1).getStringCellValue();
                    }
                    if (row.getCell(2) != null && row.getCell(2).getStringCellValue() != null) {
                        parent = row.getCell(2).getStringCellValue();
                    }

                    if (this.repository.findByTitle(title).isEmpty()) {
                        ProductCategory category = new ProductCategory();
                        category.setTitle(title);
                        category.setDescription(description);
                        if (!isEmpty(parent)) this.repository.findByTitle(parent).ifPresent(category::setParent);
                        categoryList.add(this.repository.save(category));
                    }
                }
            }
            workbook.close();
            map.put("Total Upload ", String.valueOf(categoryList.size()));
        } catch (IOException e) {
            log.error("Error processing template upload", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

        byte[] content = writeProductScrapContentToFile(map);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=scrap_file.txt");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(content.length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(content);
    }

    private void throwIfRequestNotValid(ProductCategoryRequestDto request) {
        if (isEmpty(request.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, REQUIRED_ID);
        }

        if (this.repository.findById(request.getId()).isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND, RECORD_NOT_FOUND);
        }
    }

    private void throwIfDuplicateEntry(ProductCategory category) {
        if (isEmpty(category.getId())) {
            Optional<ProductCategory> optional = this.repository.findByTitle(category.getTitle());
            if (optional.isPresent()) {
                throw new ResponseStatusException(CONFLICT, DUPLICATE);
            }
        }
    }

    private Optional<Boolean> removeProductCategoryIfPresentAndHasNoProduct(Optional<ProductCategory> optionalProductCategory) {
        return optionalProductCategory.map(this::deleteIfHasNoProducts);
    }

    private boolean deleteIfHasNoProducts(ProductCategory category) {
        if (category.getProducts() != null && category.getProducts().size() > 0) {
            return Boolean.FALSE;
        }

        this.repository.delete(category);
        return Boolean.TRUE;
    }
}
