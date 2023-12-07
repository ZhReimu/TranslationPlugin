package com.mrx.translator.model.cytranslator.dto;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.auth0.jwt.JWT;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;

import java.time.LocalDateTime;

/**
 * @author Mr.X
 * @since 2023-12-07 下午 10:21:37
 */
@Data
public final class CYToken {
    private Integer rc;
    @JSONField(name = "expire_time")
    private LocalDateTime expireTime;
    private String jwt;

    public Payload decodeJwt() {
        return JSON.parseObject(new String(Base64.decodeBase64(JWT.decode(jwt).getPayload())), Payload.class);
    }

    @Data
    public static final class Payload {
        private String browser_id;
        private String ip_address;
        private String token;
        private String version;
        private LocalDateTime exp;
    }
}