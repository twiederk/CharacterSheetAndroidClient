package com.android.ash.charactersheet.boc.service

import android.content.Context
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.d20charactersheet.framework.boc.service.*

class ServiceFactory {

    fun getDisplayService(
        gameSystemType: GameSystemType,
        context: Context
    ): DisplayService {
        return when (gameSystemType) {
            GameSystemType.DND5E -> DnD5eAndroidDisplayService(context.resources)
            else -> DnDv35AndroidDisplayService(context.resources)
        }
    }

    fun getRuleService(gameSystemType: GameSystemType): RuleService =
        when (gameSystemType) {
            GameSystemType.DNDV35 -> DnDv35RuleServiceImpl()
            GameSystemType.PATHFINDER -> PathfinderRuleServiceImpl()
            GameSystemType.DND5E -> DnD5eRuleServiceImpl()
        }

}
