package cn.yiiguxing.plugin.translate.trans.caiyun.model.dto;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWT;
import org.apache.commons.codec.binary.Base64;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * @author Mr.X
 * @since 2023-12-07 下午 10:21:37
 */
public final class CYToken {
    private Integer rc;
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

    public Integer getRc() {
        return rc;
    }

    public CYToken setRc(Integer rc) {
        this.rc = rc;
        return this;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public CYToken setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public String getJwt() {
        return jwt;
    }

    public CYToken setJwt(String jwt) {
        this.jwt = jwt;
        return this;
    }

    public static final class Payload {
        private String browser_id;
        private String ip_address;
        private String token;
        private String version;
        private LocalDateTime exp;

        public String getBrowser_id() {
            return browser_id;
        }

        public Payload setBrowser_id(String browser_id) {
            this.browser_id = browser_id;
            return this;
        }

        public String getIp_address() {
            return ip_address;
        }

        public Payload setIp_address(String ip_address) {
            this.ip_address = ip_address;
            return this;
        }

        public String getToken() {
            return token;
        }

        public Payload setToken(String token) {
            this.token = token;
            return this;
        }

        public String getVersion() {
            return version;
        }

        public Payload setVersion(String version) {
            this.version = version;
            return this;
        }

        public LocalDateTime getExp() {
            return exp;
        }

        public Payload setExp(LocalDateTime exp) {
            this.exp = exp;
            return this;
        }
    }
}