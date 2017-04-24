package com.lms.config.security;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lms.models.MemberShip;
import com.lms.models.User;
import com.lms.services.membership.MembershipService;
import com.lms.services.user.UserService;

/**
 * Created by bhushan on 10/4/17.
 */
@Service
public class SecUserServiceImpl  implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private MembershipService membershipService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.loadByUsername(userName);
        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        if(user.isSuperAdmin()) {
            grantedAuthorities.add(new SimpleGrantedAuthority("SUPER_ADMIN"));
        }
        MemberShip memberShip = membershipService.getLastUserMembership(user);
        if (memberShip != null) {
            grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
            if(memberShip.isAdmin()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("LIBRARY_ADMIN"));
            }
            if (memberShip.isLibrarian()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("LIBRARIAN"));
            }
        }
        SecUser secUser = new SecUser(user.getUsername(),
                user.getPassword(), user.isEnabled(), !user.isAccountExpired(),! user.isPasswordExpired(), !user.isAccountLocked(), grantedAuthorities,memberShip.getLibrary().getUuid());
        return secUser;
    }
}
