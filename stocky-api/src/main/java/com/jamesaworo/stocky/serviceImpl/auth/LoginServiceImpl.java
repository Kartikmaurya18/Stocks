package com.jamesaworo.stocky.serviceImpl.auth;

import com.jamesaworo.stocky.configuration.security.JwtAuthenticationTokenUtil;
import com.jamesaworo.stocky.dto.request.auth.LoginRequestDto;
import com.jamesaworo.stocky.dto.response.auth.LoginResponseDto;
import com.jamesaworo.stocky.dto.response.auth.LoginUserDto;
import com.jamesaworo.stocky.dto.response.auth.MenuDto;
import com.jamesaworo.stocky.entity.auth.User;
import com.jamesaworo.stocky.entity.auth.enums.AppModuleEnum;
import com.jamesaworo.stocky.service.auth.LoginService;
import com.jamesaworo.stocky.service.auth.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    public static final String USER_NOT_FOUND = " %s not found";
    private static final String INVALID_USERNAME = "Invalid login credentials";
    private static final String ACCOUNT_EXPIRED = "Error! Account Is Expired";
    private static final String ACCOUNT_DISABLED = "Error! Account Is Currently Disabled";
    private static final String INVALID_LOGIN = "Invalid Login Credentials";

    private final UserService userService;
    private final JwtAuthenticationTokenUtil jwtAuthenticationTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto request) {
        User user = findByUsernameOrThrow(request.getUsername());
        LoginResponseDto loginResponse = this.authenticateUser(user, request.getPassword());
        return ResponseEntity.ok().body(loginResponse);
    }

    private User findByUsernameOrThrow(String username) {
        Optional<User> optional = this.userService.findByUsername(username);
        return optional.orElseThrow(() -> new ResponseStatusException(BAD_REQUEST, INVALID_USERNAME));
    }

    private LoginResponseDto authenticateUser(User user, String password) {
        this.validateUserAccount(user, password);
        String token = this.jwtAuthenticationTokenUtil.generateToken(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, token, user.getGrantedAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return this.setLoginResponse(user, token);
    }

    private void validateUserAccount(User user, String password) {
        this.throwsInvalidIfPasswordNotMatch(password, user);
        this.throwsInvalidIfAccountIsExpired(user);
        this.throwsInvalidIfAccountIsDisabled(user);
    }

    private void throwsInvalidIfPasswordNotMatch(String password, User user) {
        if (isEmpty(password) || !this.passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(BAD_REQUEST, INVALID_LOGIN);
        }
    }

    private void throwsInvalidIfAccountIsExpired(User user) {
        if (user.isAccountExpired()) {
            throw new ResponseStatusException(BAD_REQUEST, ACCOUNT_EXPIRED);
        }
    }

    private void throwsInvalidIfAccountIsDisabled(User user) {
        if (user.getIsActiveStatus() == null || !user.getIsActiveStatus()) {
            throw new ResponseStatusException(BAD_REQUEST, ACCOUNT_DISABLED);
        }
    }

    private LoginResponseDto setLoginResponse(User user, String token) {
        LoginResponseDto response = new LoginResponseDto();
        response.setUser(this.user(user, token));
        response.setMenu(this.menu(user.getPermissionsTitleAsMap()));
        response.setApp(this.app());
        return response;
    }

    private LoginUserDto user(User user, String token) {
        LoginUserDto loginUser = new LoginUserDto();
        loginUser.setUsername(user.getUsername());
        loginUser.setEnabled(user.getIsActiveStatus() != null && user.getIsActiveStatus());
        loginUser.setToken(token);
        loginUser.setId(user.getId());
        loginUser.setAccess(new ArrayList<>(user.getPermissionsTitleAsSet()));
        return loginUser;
    }

    private List<MenuDto> menu(Map<String, String> usersPermissions) {
        MenuDto main = new MenuDto("MENU", true);
        for (AppModuleEnum module : AppModuleEnum.values()) {
            MenuDto sub = MenuDto.parentWithChildren(module.name(), module.pageIcon(), usersPermissions, module.pageRoute());
            if (sub.getChildren() != null && sub.getChildren().size() > 0) {
                MenuDto.appendChild(main, sub);
            }
        }
        return List.of(main);
    }

    private Map<String, String> app() {
        Map<String, String> details = new HashMap<>();
        details.put("name", "Stocky");
        details.put("description", "Stocky, A store management software");
        details.put("version", "1.0.0");
        return details;
    }
}
