package cn.yiiguxing.plugin.translate.trans.caiyun.model.payload;

import com.mrx.translator.model.payload.BasePayload;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * @author Mr.X
 * @since 2023-12-07 下午 10:20:15
 */
public final class CYTokenPayload extends BasePayload {
    private final String browser_id = DigestUtils.md5Hex(UUID.randomUUID().toString().replace("-", ""));

    public static CYTranslatePayload newPayload() {
        return new CYTranslatePayload();
    }
}
