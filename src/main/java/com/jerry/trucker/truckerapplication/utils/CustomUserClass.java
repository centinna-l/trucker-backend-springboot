package com.jerry.trucker.truckerapplication.utils;

import com.jerry.trucker.truckerapplication.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;



public class CustomUserClass implements UserDetails {

    private User user;

    public CustomUserClass(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user.getIsAdmin()) {
            return Collections.singletonList(() -> "ROLE_ADMIN");
        } else {
            return Collections.singletonList(() -> "ROLE_USER");
        }
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
