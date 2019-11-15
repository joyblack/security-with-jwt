package com.joy.securitywithjwt.authentication.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This jwt configure information, see detail in applicationXXX.yml
 * @author joy black
 */
@Component
@ConfigurationProperties(value = "jwt")
@Data
public class JwtProperties {
    /**
     * 签发人
     */
    private String issuer;

    /**
     * token过期时间
     */
    private long expirationTime;

    /**
     * 主题
     */
    private String subject;


    /**
     * 编码串
     */
    private String base64Key;

    /**
     * Header头
     */
    private String headerParameter;

}
