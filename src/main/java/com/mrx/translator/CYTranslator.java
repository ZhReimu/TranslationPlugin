package com.mrx.translator;

import com.alibaba.fastjson2.JSON;
import com.mrx.translator.model.cytranslator.dto.CYToken;
import com.mrx.translator.model.cytranslator.payload.CYTokenPayload;
import com.mrx.translator.model.cytranslator.payload.CYTranslatePayload;
import com.mrx.translator.model.cytranslator.response.CYResponse;
import com.mrx.translator.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 彩云小译
 *
 * @author Mr.X
 * @since 2023-12-07 下午 9:26:38
 */
public class CYTranslator {

    private static final String example = "5Y2t5nJ95YvJ55JZ";

    private static final String TRANSLATOR_API = "https://api.interpreter.caiyunai.com/v1/translator";

    private static final String TOKEN_API = "https://api.interpreter.caiyunai.com/v1/user/jwt/generate";

    private static final String[] CY_HEADERS = new String[]{
            "X-Authorization", "token:qgemv4jr1y38jyq6vhvi", "origin", "https://fanyi.caiyunapp.com"
    };

    private static final String TRANSLATE_TOKEN = "T-Authorization";

    private static final ThreadLocal<CYToken> TOKEN_HOLDER = new ThreadLocal<>();

    private static final Logger logger = LoggerFactory.getLogger(CYTranslator.class);

    public static void main(String[] args) {
        System.out.println(translate("Hello", "World"));
        System.out.println(translate("Hello", "World"));
        System.out.println(translate("Hello", "World"));
        System.out.println(translate("Hello", "World"));
    }

    private static String translate(String... source) {
        CYToken token = getToken();
        CYToken.Payload jwt = token.decodeJwt();
        logger.info("jwt: {}", JSON.toJSONString(jwt));
        CYTranslatePayload payload = CYTranslatePayload.builder()
                .source(List.of(source))
                .trans_type("auto2zh")
                .request_id("web_fanyi")
                .media("text")
                .os_type("web")
                .dict(true)
                .cached(true)
                .style("formal")
                .model("")
                .detect(true)
                .browser_id(jwt.getBrowser_id())
                .build();
        String response = HttpUtils.sendPost(TRANSLATOR_API, payload.toPayload(), TRANSLATE_TOKEN, token.getJwt());
        logger.info("response: {}", response);
        CYResponse cyResponse = JSON.parseObject(response, CYResponse.class);
        return cyResponse.getTarget().stream().map(Decrypt::decrypt).collect(Collectors.joining());
    }

    private static CYToken getToken() {
        CYToken token = TOKEN_HOLDER.get();
        // 没有 token 或者 token 过期都会刷新 token
        if (token == null || token.getExpireTime().isBefore(LocalDateTime.now())) {
            synchronized (TOKEN_HOLDER) {
                String response = HttpUtils.sendPost(TOKEN_API, CYTokenPayload.newPayload().toPayload(), CY_HEADERS);
                CYToken newToken = JSON.parseObject(response, CYToken.class);
                if (newToken.getRc() < 0) {
                    throw new RuntimeException("fetch token failed: " + response);
                }
                logger.info("newToken: {}", response);
                TOKEN_HOLDER.set(newToken);
            }
        }
        return TOKEN_HOLDER.get();
    }

    /**
     * 解密工具类<br/>
     * <pre>{@code
     *     function ot(t) {
     *     const n = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
     *                 , r = "NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm"
     *                 , o = s=>n.indexOf(s)
     *                 , u = s=>o(s) > -1 ? r[o(s)] : s;
     *         return t.split("").map(u).join("")
     *     }
     * }</pre>
     */
    private static final class Decrypt {
        private static final String n = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        private static final String r = "NOPQRSTUVWXYZABCDEFGHIJKLMnopqrstuvwxyzabcdefghijklm";

        public static String decrypt(String enc) {
            StringBuilder sb = new StringBuilder();
            for (char c : enc.toCharArray()) {
                sb.append(u(c));
            }
            byte[] bytes = Base64.getDecoder().decode(sb.toString());
            return new String(bytes);
        }

        private static int o(char s) {
            return n.indexOf(s);
        }

        private static char u(char s) {
            return o(s) > -1 ? r.toCharArray()[o(s)] : s;
        }
    }

}
