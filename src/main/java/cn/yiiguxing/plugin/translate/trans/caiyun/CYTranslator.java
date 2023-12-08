package cn.yiiguxing.plugin.translate.trans.caiyun;

import cn.yiiguxing.plugin.translate.trans.caiyun.model.dto.CYToken;
import cn.yiiguxing.plugin.translate.trans.caiyun.model.payload.CYTokenPayload;
import cn.yiiguxing.plugin.translate.trans.caiyun.model.payload.CYTranslatePayload;
import cn.yiiguxing.plugin.translate.trans.caiyun.model.response.CYResponse;
import com.alibaba.fastjson2.JSON;
import com.google.gson.Gson;
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

    private static final String TRANSLATOR_API = "https://api.interpreter.caiyunai.com/v1/translator";

    private static final String TOKEN_API = "https://api.interpreter.caiyunai.com/v1/user/jwt/generate";

    private static final String[] CY_HEADERS = new String[]{
            "X-Authorization", "token:qgemv4jr1y38jyq6vhvi", "origin", "https://fanyi.caiyunapp.com"
    };

    private static final String TRANSLATE_TOKEN = "T-Authorization";

    private static final ThreadLocal<CYToken> TOKEN_HOLDER = new ThreadLocal<>();

    private static final Logger logger = LoggerFactory.getLogger(CYTranslator.class);

    public static String translate(String... source) {
        CYToken token = getToken();
        CYToken.Payload jwt = token.decodeJwt();
        logger.info("jwt: {}", JSON.toJSONString(jwt));
        CYTranslatePayload payload = CYTranslatePayload.builder()
                .setSource(List.of(source))
                .setTrans_type("auto2zh")
                .setRequest_id("web_fanyi")
                .setMedia("text")
                .setOs_type("web")
                .setDict(true)
                .setCached(true)
                .setStyle("formal")
                .setModel("")
                .setDetect(true)
                .setBrowser_id(jwt.getBrowser_id())
                .build();
        String response = HttpUtils.sendPost(TRANSLATOR_API, payload.toPayload(), TRANSLATE_TOKEN, token.getJwt()).trim();
        logger.info("response: {}", response);
        CYResponse cyResponse = JSON.parseObject(response, CYResponse.class);
        return cyResponse.getTarget().stream().map(Decrypt::decrypt).collect(Collectors.joining());
    }

    private static CYToken getToken() {
        CYToken token = TOKEN_HOLDER.get();
        // 没有 token 或者 token 过期都会刷新 token
        if (token == null || token.getExpireTime().isBefore(LocalDateTime.now())) {
            synchronized (TOKEN_HOLDER) {
                String response = HttpUtils.sendPost(TOKEN_API, CYTokenPayload.newPayload().toPayload(), CY_HEADERS).trim();
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
