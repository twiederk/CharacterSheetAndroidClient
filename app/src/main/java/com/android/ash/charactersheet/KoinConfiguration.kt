package com.android.ash.charactersheet

import com.android.ash.charactersheet.billing.Billing6
import com.android.ash.charactersheet.billing.MessageDisplay
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.boc.service.PreferenceService
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import com.android.ash.charactersheet.gui.main.charactercreator.CharacterCreator
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AbilityScoresScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.AppearanceScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.ClassScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.EquipmentScreenViewModel
import com.android.ash.charactersheet.gui.main.charactercreator.viewmodel.RaceScreenViewModel
import com.android.ash.charactersheet.gui.main.characterlist.CharacterListViewModel
import com.android.ash.charactersheet.gui.sheet.FragmentPagerFactory
import com.android.ash.charactersheet.gui.sheet.SheetPanelFactory
import com.android.ash.charactersheet.gui.sheet.skill.SkillEditArrayAdapterFactory
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.service.GameSystem
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class GameSystemHolder {
    var gameSystem: GameSystem? = null
    var gameSystemType: GameSystemType? = null
    var dndv35DbHelper: DBHelper? = null
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
    single { Billing6() }
    single { MessageDisplay(androidContext()) }
    single { CharacterCreator() }
    single { SheetPanelFactory() }
    single { FragmentPagerFactory() }
    single { RaceScreenViewModel(get()) }
    single { ClassScreenViewModel(get()) }
    single { AppearanceScreenViewModel(get()) }
    single { AbilityScoresScreenViewModel(get(), get()) }
    single { EquipmentScreenViewModel(get()) }
    single { CharacterListViewModel() }
    single { SkillEditArrayAdapterFactory() }
}
// end::koinConfiguration[]
