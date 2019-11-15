package com.joy.securitywithjwt.authentication.service;

import com.joy.securitywithjwt.authentication.model.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * introduce: This is security get user methods.
 * @author joy black on 2019/11/15 15:07
 * @version 1.0
 */
@Component
public class JwtUserDetailService implements UserDetailsService {
    /**
     * Password encoder
     */
    private PasswordEncoder passwordEncoder;
    @Autowired
    public JwtUserDetailService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * get user by username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if("admin".equals(username)){
            return new JwtUser(username, passwordEncoder.encode("123456"), "ROLE_ADMIN");
        }
        if("user".equals(username)){
            return new JwtUser(username, passwordEncoder.encode("123456"), "ROLE_USER");
        }
        throw new UsernameNotFoundException("用户名不能为空。");
    }
}
