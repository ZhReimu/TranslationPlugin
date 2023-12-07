package com.mrx.translator.model.cytranslator.payload;

import com.mrx.translator.model.payload.BasePayload;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * @author Mr.X
 * @since 2023-12-07 下午 10:20:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(staticName = "newPayload")
public final class CYTokenPayload extends BasePayload {
    private final String browser_id = DigestUtils.md5Hex(UUID.randomUUID().toString().replace("-", ""));
}
