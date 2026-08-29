package com.jamesaworo.stocky.controller.auth;

import com.jamesaworo.stocky.core.params.PageSearchRequest;
import com.jamesaworo.stocky.core.params.PageSearchResult;
import com.jamesaworo.stocky.dto.request.auth.AccountRequestDto;
import com.jamesaworo.stocky.features.company.data.request.CompanyEmployeeSearchRequest;
import com.jamesaworo.stocky.service.auth.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.jamesaworo.stocky.core.constants.Global.API_PREFIX;

@RestController
@RequestMapping(value = API_PREFIX + "/auth/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping(value = "/search")
    public ResponseEntity<PageSearchResult<List<AccountRequestDto>>> searchForUsersAccountInPages(
            @RequestBody PageSearchRequest<CompanyEmployeeSearchRequest> request) {
        return this.service.search(request);
    }

    @PutMapping(value = "/update-expiry-date/{userId}")
    public ResponseEntity<Boolean> updateExpiryDate(@PathVariable Long userId, @RequestBody AccountRequestDto request) {
        return service.updateExpiryDate(userId, request);
    }

    @PutMapping(value = "/update-role/{userId}")
    public ResponseEntity<Boolean> updateRole(@PathVariable Long userId, @RequestBody AccountRequestDto request) {
        return service.updateRoles(userId, request);
    }

    @PutMapping(value = "/update-password/{userId}")
    public ResponseEntity<Boolean> updatePassword(@PathVariable Long userId, @RequestBody AccountRequestDto request) {
        return service.updatePassword(userId, request);
    }

    @PutMapping(value = "/toggle-status/{userId}")
    public ResponseEntity<Boolean> toggleStatus(@PathVariable Long userId) {
        return service.toggleStatus(userId);
    }
}
