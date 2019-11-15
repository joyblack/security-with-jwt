package com.joy.securitywithjwt.controller;

import com.joy.securitywithjwt.authentication.jwt.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class TestController {
    @Autowired
    private JwtProperties jwtProperties;

    @RequestMapping("getJwtProperties")
    public JwtProperties getJwtProperties(){
        return jwtProperties;
    }
}
