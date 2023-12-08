package cn.yiiguxing.plugin.translate.trans.caiyun.model.payload;

import com.mrx.translator.model.payload.BasePayload;

import java.util.List;

/**
 * @author Mr.X
 * @since 2023-12-07 下午 10:20:15
 */
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

    public static CYTranslatePayload builder() {
        return new CYTranslatePayload();
    }

    public List<String> getSource() {
        return source;
    }

    public CYTranslatePayload setSource(List<String> source) {
        this.source = source;
        return this;
    }

    public String getTrans_type() {
        return trans_type;
    }

    public CYTranslatePayload setTrans_type(String trans_type) {
        this.trans_type = trans_type;
        return this;
    }

    public String getRequest_id() {
        return request_id;
    }

    public CYTranslatePayload setRequest_id(String request_id) {
        this.request_id = request_id;
        return this;
    }

    public String getMedia() {
        return media;
    }

    public CYTranslatePayload setMedia(String media) {
        this.media = media;
        return this;
    }

    public String getOs_type() {
        return os_type;
    }

    public CYTranslatePayload setOs_type(String os_type) {
        this.os_type = os_type;
        return this;
    }

    public Boolean getDict() {
        return dict;
    }

    public CYTranslatePayload setDict(Boolean dict) {
        this.dict = dict;
        return this;
    }

    public Boolean getCached() {
        return cached;
    }

    public CYTranslatePayload setCached(Boolean cached) {
        this.cached = cached;
        return this;
    }

    public Boolean getReplaced() {
        return replaced;
    }

    public CYTranslatePayload setReplaced(Boolean replaced) {
        this.replaced = replaced;
        return this;
    }

    public String getStyle() {
        return style;
    }

    public CYTranslatePayload setStyle(String style) {
        this.style = style;
        return this;
    }

    public String getModel() {
        return model;
    }

    public CYTranslatePayload setModel(String model) {
        this.model = model;
        return this;
    }

    public Boolean getDetect() {
        return detect;
    }

    public CYTranslatePayload setDetect(Boolean detect) {
        this.detect = detect;
        return this;
    }

    public String getBrowser_id() {
        return browser_id;
    }

    public CYTranslatePayload setBrowser_id(String browser_id) {
        this.browser_id = browser_id;
        return this;
    }

    public CYTranslatePayload build() {
        return this;
    }
}