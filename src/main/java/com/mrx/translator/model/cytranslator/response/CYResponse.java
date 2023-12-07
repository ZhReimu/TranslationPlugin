package com.mrx.translator.model.cytranslator.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mr.X
 * @since 2023-12-07 下午 10:22:33
 */
@Data
public class CYResponse {
    private List<String> target;
    private Integer rc;
    private BigDecimal confidence;
}
