package com.jamesaworo.stocky.serviceImpl.auth;

import com.jamesaworo.stocky.core.params.PageSearchRequest;
import com.jamesaworo.stocky.core.params.PageSearchResult;
import com.jamesaworo.stocky.core.utils.Util;
import com.jamesaworo.stocky.dto.request.auth.AccountRequestDto;
import com.jamesaworo.stocky.dto.request.auth.RoleRequestDto;
import com.jamesaworo.stocky.features.authentication.domain.entity.User;
import com.jamesaworo.stocky.service.auth.AccountService;
import com.jamesaworo.stocky.service.auth.UserService;
import com.jamesaworo.stocky.features.company.data.request.CompanyEmployeeSearchRequest;
import com.jamesaworo.stocky.features.company.domain.entity.CompanyEmployee;
import com.jamesaworo.stocky.features.company.domain.usecase.ICompanyEmployeeUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.jamesaworo.stocky.core.params.PageParam.toPageSearchResult;
import static com.jamesaworo.stocky.core.utils.Util.parseToLocalDate;
import static com.jamesaworo.stocky.features.company.data.request.specification.CompanyEmployeeSearchSpecification.companyEmployeeSpecification;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final ICompanyEmployeeUsecase employeeUsecase;
    private final UserService userService;

    @Override
    public ResponseEntity<PageSearchResult<List<AccountRequestDto>>> search(PageSearchRequest<CompanyEmployeeSearchRequest> request) {
        Page<CompanyEmployee> page = this.employeeUsecase.findMany(companyEmployeeSpecification(request.getSearchRequest()), request.getPage().toPageable());
        List<AccountRequestDto> requests = page.getContent().stream().map(this::mapEmployeeToAccountRequest).collect(Collectors.toList());
        return ok().body(toPageSearchResult(requests, page));
    }

    @Override
    public ResponseEntity<Boolean> updateExpiryDate(Long userId, AccountRequestDto request) {
        if (!isEmpty(request.getExpiryDate())) {
            Boolean updated = this.userService.updateExpiryDate(userId, parseToLocalDate(request.getExpiryDate()));
            return ok().body(updated);
        }
        return ok().body(false);
    }

    @Override
    public ResponseEntity<Boolean> updateRoles(Long userId, AccountRequestDto request) {
        if (!isEmpty(request.getRoles())) {
            List<Long> rolesId = request.getRoles().stream().map(RoleRequestDto::getId).collect(Collectors.toList());
            Boolean updated = this.userService.updateRoles(userId, rolesId);
            return ok().body(updated);
        }
        return ok().body(false);
    }

    @Override
    public ResponseEntity<Boolean> updatePassword(Long userId, AccountRequestDto request) {
        if (!isEmpty(request.getPassword())) {
            Boolean updated = this.userService.updatePassword(userId, request.getPassword());
            return ok().body(updated);
        }
        return ok().body(false);
    }

    @Override
    public ResponseEntity<Boolean> toggleStatus(Long userId) {
        Optional<Boolean> optional = this.userService.toggleActiveStatus(userId);
        return ok().body(optional.orElse(false));
    }

    private AccountRequestDto mapEmployeeToAccountRequest(CompanyEmployee employee) {
        User user = employee.getAccountDetail();

        AccountRequestDto request = new AccountRequestDto();
        request.setId(user.getId());
        request.setEmployeeId(employee.getId());
        request.setUserId(user.getId());
        request.setName(employee.getPersonalDetail().getEmployeeFullName());
        request.setUsername(user.getUsername());
        request.setRoles(user.getRoles().stream().map(role -> {
            RoleRequestDto roleRequest = new RoleRequestDto();
            roleRequest.setId(role.getId());
            roleRequest.setName(role.getName());
            roleRequest.setDescription(role.getDescription());
            return roleRequest;
        }).collect(Collectors.toList()));
        request.setPhone(employee.getPersonalDetail().getEmployeePhone());
        request.setIsActiveStatus(user.getIsActiveStatus());
        request.setExpiryDate(Util.formatDate(user.getExpirationDate()));
        return request;
    }
}
