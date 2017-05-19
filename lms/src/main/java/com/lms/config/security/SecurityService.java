package com.lms.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Created by bhushan on 18/5/17.
 */
@Component
public class SecurityService {
    @Autowired
    private SecUserServiceImpl userService;

    public void reauthenticate(String username) {
        UserDetails userDetails = userService.loadUserByUsername(username);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
    }
}
