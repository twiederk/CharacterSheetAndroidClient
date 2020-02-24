package com.android.ash.charactersheet

import android.app.Application
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.service.GameSystem
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * The application of the Character Sheet application.
 */
class CharacterSheetApplication : Application() {

    var gameSystem: GameSystem? = null
    var preferenceService: PreferenceService? = null
    var character: Character? = null

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CharacterSheetApplication)
            modules(appModule)
        }
    }


    /**
     * Sets all instance variables to null to release the resources.
     */
    fun close() {
        gameSystem = null
        preferenceService = null
        character = null
    }
}