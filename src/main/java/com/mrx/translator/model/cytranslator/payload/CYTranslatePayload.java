package com.mrx.translator.model.cytranslator.payload;

import com.mrx.translator.model.payload.BasePayload;
import lombok.*;

import java.util.List;

/**
 * @author Mr.X
 * @since 2023-12-07 下午 10:20:15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public final class CYTranslatePayload extends BasePayload {
    private List<String> source;
    private String trans_type;
    private String request_id;
    private String media;
    private String os_type;
    private Boolean dict;
    private Boolean cached;
    private Boolean replaced;
    private String style;
    private String model;
    private Boolean detect;
    private String browser_id;
}