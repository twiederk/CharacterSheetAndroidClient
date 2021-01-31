@file:Suppress("unused")

package com.android.ash.charactersheet

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * The application of the Character Sheet application.
 */
class CharacterSheetApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@CharacterSheetApplication)
            modules(appModule)
        }
    }

}