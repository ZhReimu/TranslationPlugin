package com.mrx.translator.model.cytranslator.dto;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.annotation.JSONField;
import com.auth0.jwt.JWT;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author Mr.X
 * @since 2023-12-07 下午 10:21:37
 */
@Data
public final class CYToken {
    private Integer rc;
    @JSONField(serialize = false, deserialize = false)
    private LocalDateTime expireTime;
    private String jwt;

    public Payload decodeJwt() {
        return JSON.parseObject(new String(Base64.decodeBase64(JWT.decode(jwt).getPayload())), Payload.class);
    }

    @SuppressWarnings("unused")
    public void setExpire_time(Long expire_time) {
        if (expire_time != null) {
            expireTime = LocalDateTime.from(new Date(expire_time * 1000).toInstant().atOffset(ZoneOffset.ofHours(8)));
        }
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