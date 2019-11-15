package com.joy.securitywithjwt.authentication.model;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author joy black on 2019/11/15 15:16
 * @version 1.0
 */
@Data
public class JwtUser implements UserDetails {
    private String username;
    private String password;
    private Collection<SimpleGrantedAuthority> authorities;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    public JwtUser(String username, String password, String... roles) {
        this.username = username;
        this.password = password;
        this.authorities= Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
