package com.joy.securitywithjwt.authentication.provider;

import com.joy.securitywithjwt.authentication.token.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * introduce: jwt provider with jwt token type.
 * This object has follow function:
 * 1. set supported authentication type(by our define token).
 * 2. check authentication information(by username and password, usually, but sometimes we used phone, in coal mine institute not used this type)
 * 3. if auth failed, throw exception to filter, it will catch handler to handler it(FailureHandler).
 * 4. if auth success, create a authenticated token, then return to filter, filter get the authenticated token then call handler(SuccessHandler)
 * @author joy black
 * @version 1.0
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

    /**
     * userDetails
     */
    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;



    /**
     * This method will be called by our filter(JwtAuthenticationFilter)
     * so, parameter authentication is not authenticated object(in our custom filter defined)
     * if authenticated success, return a authenticated object to filter.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // Because used methods supports to check, so this cast must be success.
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        System.out.println("name = " + token.getName());

        String username = (String) token.getPrincipal();
        String password = (String) token.getCredentials();

        UserDetails user = userDetailsService.loadUserByUsername(username);
        check(user, token);


    }

    private void check(UserDetails user, JwtAuthenticationToken token) {
        if(token.getCredentials() == null){
            throw new BadCredentialsException("错误的凭证信息");
        }
        // check provide password whether if equal db user password.
        String providePassword = token.getCredentials().toString();
        if(passwordEncoder.matches(providePassword, user.getPassword())){
            throw new BadCredentialsException("密码错误");
        }
    }

    /**
     * set this provider supported authentication type.
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
