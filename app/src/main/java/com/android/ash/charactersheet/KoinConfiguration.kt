package com.android.ash.charactersheet

import com.android.ash.charactersheet.billing.Billing
import com.android.ash.charactersheet.billing.MessageDisplay
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import com.android.ash.charactersheet.gui.main.charactercreator.CharacterCreator
import com.android.ash.charactersheet.gui.main.charactercreator.CharacterCreatorViewModel
import com.android.ash.charactersheet.gui.sheet.FragmentPagerFactory
import com.android.ash.charactersheet.gui.sheet.SheetPanelFactory
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class GameSystemHolder {
    var gameSystem: GameSystem? = null
    var gameSystemType: GameSystemType? = null
    var dndDbHelper: DBHelper? = null
    var pathfinderDbHelper: DBHelper? = null
    var dnd5eDbHelper: DBHelper? = null
}

class PreferenceServiceHolder {
    var preferenceService: PreferenceService? = null
}

class CharacterHolder {
    var character: Character? = null
}

// tag::koinConfiguration[]
val appModule = module {
    single { GameSystemHolder() }
    single { PreferenceServiceHolder() }
    single { CharacterHolder() }
    single { FirebaseAnalytics.getInstance(androidContext()) }
    single { Billing() }
    single { MessageDisplay(androidContext()) }
    single { CharacterCreator() }
    single { SheetPanelFactory() }
    single { FragmentPagerFactory() }
    single { CharacterCreatorViewModel() }
}
// end::koinConfiguration[]
