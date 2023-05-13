package cn.yiiguxing.plugin.translate.trans.google

import cn.yiiguxing.plugin.translate.TranslationStorages
import com.intellij.openapi.components.*
import com.intellij.util.xmlb.annotations.OptionTag


/**
 * Google settings.
 */
@Service
@State(name = "Translation.GoogleSettings", storages = [Storage(TranslationStorages.PREFERENCES_STORAGE_NAME)])
class GoogleSettings : BaseState(), PersistentStateComponent<GoogleSettings> {

    @get:OptionTag("USE_MIRROR")
    var useMirror: Boolean by property(false)

    @get:OptionTag("MIRROR_URL")
    var mirrorUrl: String? by string(null)

    override fun getState(): GoogleSettings = this

    override fun loadState(state: GoogleSettings) {
        copyFrom(state)
    }
}