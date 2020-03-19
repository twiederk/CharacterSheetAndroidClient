package com.android.ash.charactersheet

import com.android.ash.charactersheet.boc.service.PreferenceService
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.service.GameSystem
import org.koin.dsl.module

class GameSystemHolder {
    var gameSystem: GameSystem? = null
}

class PreferenceServiceHolder {
    var preferenceService: PreferenceService? = null
}

class CharacterHolder {
    var character: Character? = null
}

val appModule = module {
    single { GameSystemHolder() }
    single { PreferenceServiceHolder() }
    single { CharacterHolder() }
}