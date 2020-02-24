package com.android.ash.charactersheet

import com.d20charactersheet.framework.boc.service.GameSystem
import org.koin.dsl.module

class GameSystemHolder {
    var gameSystem: GameSystem? = null
}

val appModule = module {
    single { GameSystemHolder() }
}
