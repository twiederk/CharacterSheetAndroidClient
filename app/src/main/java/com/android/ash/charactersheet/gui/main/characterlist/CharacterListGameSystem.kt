package com.android.ash.charactersheet.gui.main.characterlist

import android.content.Context
import com.android.ash.charactersheet.BuildConfig
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.android.ash.charactersheet.boc.service.PreferenceServiceImpl
import com.android.ash.charactersheet.dac.dao.android.AndroidPreferenceDao
import com.android.ash.charactersheet.dac.dao.sqlite.DBHelper
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.java.KoinJavaComponent

class CharacterListGameSystem : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferenceServiceHolder: PreferenceServiceHolder by inject()
    private val firebaseAnalytics = KoinJavaComponent.inject(FirebaseAnalytics::class.java)

    fun onCreateGameSystem(context: Context) {
        if (gameSystemHolder.gameSystem == null) {
            val dbVersion = BuildConfig.VERSION_CODE
            gameSystemHolder.dndDbHelper = DBHelper(context, GameSystemType.DNDV35.databaseName, dbVersion,
                    GameSystemType.DNDV35.createScripts, GameSystemType.DNDV35.updateScripts,
                    GameSystemType.DNDV35.images)
            gameSystemHolder.pathfinderDbHelper = DBHelper(context, GameSystemType.PATHFINDER.databaseName, dbVersion,
                    GameSystemType.PATHFINDER.createScripts, GameSystemType.PATHFINDER.updateScripts,
                    GameSystemType.PATHFINDER.images)
            preferenceServiceHolder.preferenceService = PreferenceServiceImpl(AndroidPreferenceDao(context))
        }

    }

    fun onResumeGameSystem(characterListActivity: CharacterListActivity, characterListLayout: CharacterListLayout) {
        val gameSystem: GameSystem? = gameSystemHolder.gameSystem
        if (gameSystem == null) {
            gameSystemHolder.gameSystemType = getGameSystemTypeFromPreferences()
            CreateDatabaseAndGameSystemAsyncTask(characterListActivity, characterListActivity).execute()
        } else if (!gameSystem.isLoaded) {
            LoadGameSystemAsyncTask(characterListActivity, characterListActivity).execute()
        } else {
            characterListLayout.resumeLayout()
        }
    }

    private fun getGameSystemTypeFromPreferences(): GameSystemType? {
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