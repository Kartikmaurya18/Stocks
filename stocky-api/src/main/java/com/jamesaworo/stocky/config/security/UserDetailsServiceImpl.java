package com.jamesaworo.stocky.config.security;

import com.jamesaworo.stocky.dao.auth.UserDao;
import com.jamesaworo.stocky.entity.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final String NO_USER_FOUND = "No user found ";

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optional = this.userDao.findByUsernameEqualsIgnoreCase(username);
        return optional.map(UserPrincipalImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException(NO_USER_FOUND + username));
    }
}
