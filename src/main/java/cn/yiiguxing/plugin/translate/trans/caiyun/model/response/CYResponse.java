package cn.yiiguxing.plugin.translate.trans.caiyun.model.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Mr.X
 * @since 2023-12-07 下午 10:22:33
 */
public class CYResponse {
    private List<String> target;
    private Integer rc;
    private BigDecimal confidence;

    public List<String> getTarget() {
        return target;
    }

    public CYResponse setTarget(List<String> target) {
        this.target = target;
        return this;
    }

    public Integer getRc() {
        return rc;
    }

    public CYResponse setRc(Integer rc) {
        this.rc = rc;
        return this;
    }

    public BigDecimal getConfidence() {
        return confidence;
    }

    public CYResponse setConfidence(BigDecimal confidence) {
        this.confidence = confidence;
        return this;
    }
}
