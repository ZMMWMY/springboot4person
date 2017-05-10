package com.security.config.core;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * Created by ZMM on 2017/5/9.
 */
public class SecurityUser implements UserDetails {

    private String id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    private Date lastRefreshPassword;

    private boolean locked;

    private boolean enable;


    public SecurityUser(String id, String username,
                        String password, Collection<? extends GrantedAuthority> authorities,
                        Date lastRefreshPassword, boolean locked, boolean enable) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.lastRefreshPassword = lastRefreshPassword;
        this.locked = locked;
        this.enable = enable;

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
