package org.example.bankingportal.concurrency;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MutableUser implements MutableUserDetails {

    private  String password;

    private String username;

    private final UserDetails userDetails;

    MutableUser(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.userDetails.getAuthorities();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return MutableUserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return MutableUserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return MutableUserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return MutableUserDetails.super.isEnabled();
    }
}
