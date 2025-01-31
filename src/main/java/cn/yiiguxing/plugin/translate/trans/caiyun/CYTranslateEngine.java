package cn.yiiguxing.plugin.translate.trans.caiyun;

import cn.yiiguxing.plugin.translate.trans.AbstractTranslator;
import cn.yiiguxing.plugin.translate.trans.ErrorInfo;
import cn.yiiguxing.plugin.translate.trans.Lang;
import cn.yiiguxing.plugin.translate.trans.Translation;
import cn.yiiguxing.plugin.translate.ui.settings.TranslationEngine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;

/**
 * @author Mr.X
 * @since 2023-12-08 09:10
 **/
public class CYTranslateEngine extends AbstractTranslator {

    public static final CYTranslateEngine INSTANCE = new CYTranslateEngine();

    private CYTranslateEngine() {}

    @NotNull
    @Override
    protected Translation doTranslate(@NotNull String text, @NotNull Lang srcLang, @NotNull Lang targetLang) {
        // FIXME 暂时支支持英译中
        String translated = CYTranslator.translate(text);
        return new Translation(text,translated,srcLang,targetLang);
    }

    @NotNull
    @Override
    public String getId() {
        return TranslationEngine.CAI_YUN.getId();
    }

    @NotNull
    @Override
    public String getName() {
        return TranslationEngine.CAI_YUN.getTranslatorName();
    }

    @NotNull
    @Override
    public Icon getIcon() {
        return TranslationEngine.CAI_YUN.getIcon();
    }

    @NotNull
    @Override
    public Lang getPrimaryLanguage() {
        return TranslationEngine.CAI_YUN.getPrimaryLanguage();
    }

    @NotNull
    @Override
    public List<Lang> getSupportedSourceLanguages() {
        return List.of(Lang.values());
    }

    @NotNull
    @Override
    public List<Lang> getSupportedTargetLanguages() {
        return List.of(Lang.values());
    }

    @Override
    public int getIntervalLimit() {
        return TranslationEngine.CAI_YUN.getIntervalLimit();
    }

    @Override
    public int getContentLengthLimit() {
        return TranslationEngine.CAI_YUN.getContentLengthLimit();
    }

    @Nullable
    @Override
    protected ErrorInfo createErrorInfo(@NotNull Throwable throwable) {
        return new ErrorInfo(throwable.getMessage());
    }

}
