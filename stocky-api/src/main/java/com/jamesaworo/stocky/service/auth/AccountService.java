package com.jamesaworo.stocky.service.auth;

import com.jamesaworo.stocky.core.params.PageSearchRequest;
import com.jamesaworo.stocky.core.params.PageSearchResult;
import com.jamesaworo.stocky.dto.request.auth.AccountRequestDto;
import com.jamesaworo.stocky.features.company.data.request.CompanyEmployeeSearchRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {
    ResponseEntity<PageSearchResult<List<AccountRequestDto>>> search(PageSearchRequest<CompanyEmployeeSearchRequest> request);
    ResponseEntity<Boolean> updateExpiryDate(Long userId, AccountRequestDto request);
    ResponseEntity<Boolean> updateRoles(Long userId, AccountRequestDto request);
    ResponseEntity<Boolean> updatePassword(Long userId, AccountRequestDto request);
    ResponseEntity<Boolean> toggleStatus(Long userId);
}
