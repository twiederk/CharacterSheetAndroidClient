package com.android.ash.charactersheet.gui.main.characterlist

import android.content.Context
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.android.ash.charactersheet.gui.sheet.GameSystemLoader
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.java.KoinJavaComponent

@KoinApiExtension
class CharacterListGameSystem : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferenceServiceHolder: PreferenceServiceHolder by inject()
    private val firebaseAnalytics = KoinJavaComponent.inject(FirebaseAnalytics::class.java)

    fun onCreateGameSystem(context: Context) {
        if (gameSystemHolder.gameSystem == null) {
            GameSystemLoader().connectDatabases(context)
        }

    }

    fun onResumeGameSystem(characterListActivity: CharacterListActivity, characterListLayout: CharacterListLayout) {
        val gameSystem: GameSystem? = gameSystemHolder.gameSystem
        if (gameSystem == null) {
            val gameSystemType = getGameSystemTypeFromPreferences()
            CreateDatabaseAndGameSystemAsyncTask(characterListActivity, characterListActivity, gameSystemType).execute()
        } else if (!gameSystem.isLoaded) {
            val gameSystemType = getGameSystemTypeFromPreferences()
            LoadGameSystemAsyncTask(characterListActivity, characterListActivity, gameSystemType).execute()
        } else {
            characterListLayout.resumeLayout()
        }
    }

    private fun getGameSystemTypeFromPreferences(): GameSystemType {
        val gameSystemTypeId: Int? = preferenceServiceHolder.preferenceService?.getInt(PreferenceService.GAME_SYSTEM_TYPE)
        for (gameSystemType in GameSystemType.values()) {
            if (gameSystemType.ordinal == gameSystemTypeId) {
                return gameSystemType
            }
        }
        throw IllegalStateException("Can't determine game system with id: $gameSystemTypeId")
    }

    fun setUserProperties() {
        val gameSystemName: String? = gameSystemHolder.gameSystem?.name
        firebaseAnalytics.value.setUserProperty(FBAnalytics.UserProperty.GAME_SYSTEM, gameSystemName)
        val numberOfCharacters: Int? = gameSystemHolder.gameSystem?.allCharacters?.size
        firebaseAnalytics.value.setUserProperty(FBAnalytics.UserProperty.CHARACTER_COUNT, numberOfCharacters.toString())
    }

    fun onDestroy() {
        gameSystemHolder.dndDbHelper?.close()
        gameSystemHolder.pathfinderDbHelper?.close()
        gameSystemHolder.gameSystem = null
    }

}