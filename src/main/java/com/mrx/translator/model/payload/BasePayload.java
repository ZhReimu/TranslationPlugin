package com.mrx.translator.model.payload;

import com.alibaba.fastjson2.JSON;

/**
 * @author Mr.X
 * @since 2023-12-07 下午 10:19:06
 */
public class BasePayload {
    public String toPayload() {
        return JSON.toJSONString(this);
    }
}
