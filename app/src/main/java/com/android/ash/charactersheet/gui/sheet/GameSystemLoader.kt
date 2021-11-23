package com.android.ash.charactersheet.gui.sheet

import android.content.Context
import com.android.ash.charactersheet.BuildConfig
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.boc.model.GameSystemType.DND5E
import com.android.ash.charactersheet.boc.model.GameSystemType.DNDV35
import com.android.ash.charactersheet.boc.model.GameSystemType.PATHFINDER
import com.android.ash.charactersheet.boc.service.AndroidImageServiceImpl
import com.android.ash.charactersheet.boc.service.PreferenceServiceImpl
import com.android.ash.charactersheet.boc.service.ServiceFactory
import com.android.ash.charactersheet.dac.dao.android.AndroidPreferenceDao
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase
import com.d20charactersheet.framework.boc.service.AbilityService
import com.d20charactersheet.framework.boc.service.AbilityServiceImpl
import com.d20charactersheet.framework.boc.service.BodyService
import com.d20charactersheet.framework.boc.service.CharacterClassService
import com.d20charactersheet.framework.boc.service.CharacterClassServiceImpl
import com.d20charactersheet.framework.boc.service.CharacterCreatorService
import com.d20charactersheet.framework.boc.service.CharacterCreatorServiceImpl
import com.d20charactersheet.framework.boc.service.CharacterService
import com.d20charactersheet.framework.boc.service.CharacterServiceImpl
import com.d20charactersheet.framework.boc.service.DisplayService
import com.d20charactersheet.framework.boc.service.ExportImportService
import com.d20charactersheet.framework.boc.service.ExportImportServiceImpl
import com.d20charactersheet.framework.boc.service.FeatService
import com.d20charactersheet.framework.boc.service.FeatServiceImpl
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.boc.service.GameSystemCacheImpl
import com.d20charactersheet.framework.boc.service.ImageService
import com.d20charactersheet.framework.boc.service.ItemService
import com.d20charactersheet.framework.boc.service.ItemServiceImpl
import com.d20charactersheet.framework.boc.service.RaceService
import com.d20charactersheet.framework.boc.service.RaceServiceImpl
import com.d20charactersheet.framework.boc.service.RuleService
import com.d20charactersheet.framework.boc.service.SkillService
import com.d20charactersheet.framework.boc.service.SkillServiceImpl
import com.d20charactersheet.framework.boc.service.SpelllistService
import com.d20charactersheet.framework.boc.service.SpelllistServiceImpl
import com.d20charactersheet.framework.boc.service.XpService
import com.d20charactersheet.framework.boc.service.XpServiceImpl
import com.d20charactersheet.framework.dac.dao.AbilityDao
import com.d20charactersheet.framework.dac.dao.CharacterDao
import com.d20charactersheet.framework.dac.dao.ClassDao
import com.d20charactersheet.framework.dac.dao.FeatDao
import com.d20charactersheet.framework.dac.dao.ImageDao
import com.d20charactersheet.framework.dac.dao.ItemDao
import com.d20charactersheet.framework.dac.dao.RaceDao
import com.d20charactersheet.framework.dac.dao.SkillDao
import com.d20charactersheet.framework.dac.dao.SpelllistDao
import com.d20charactersheet.framework.dac.dao.XpDao
import com.d20charactersheet.framework.dac.dao.sql.Database
import com.d20charactersheet.framework.dac.dao.sql.SqlAbilityDao
import com.d20charactersheet.framework.dac.dao.sql.SqlCharacterDao
import com.d20charactersheet.framework.dac.dao.sql.SqlClassDao
import com.d20charactersheet.framework.dac.dao.sql.SqlFeatDao
import com.d20charactersheet.framework.dac.dao.sql.SqlImageDao
import com.d20charactersheet.framework.dac.dao.sql.SqlItemDao
import com.d20charactersheet.framework.dac.dao.sql.SqlRaceDao
import com.d20charactersheet.framework.dac.dao.sql.SqlSkillDao
import com.d20charactersheet.framework.dac.dao.sql.SqlSpelllistDao
import com.d20charactersheet.framework.dac.dao.sql.SqlXpDao
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GameSystemLoader : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferencesServiceHolder: PreferenceServiceHolder by inject()

    fun connectDatabases(context: Context) {
        val dbVersion = BuildConfig.VERSION_CODE
        gameSystemHolder.dndv35DbHelper = DBHelper(
            context, dbVersion, DNDV35
        )
        gameSystemHolder.pathfinderDbHelper = DBHelper(
            context, dbVersion, PATHFINDER
        )
        gameSystemHolder.dnd5eDbHelper = DBHelper(
            context, dbVersion, DND5E
        )
        preferencesServiceHolder.preferenceService =
            PreferenceServiceImpl(AndroidPreferenceDao(context))
    }

    fun load(context: Context, gameSystemType: GameSystemType): GameSystem {

        gameSystemHolder.gameSystemType = gameSystemType

        val serviceFactory = ServiceFactory()
        val sqliteDatabase: Database = getDatabase(gameSystemType)

        val skillDao: SkillDao = SqlSkillDao(sqliteDatabase)
        val featDao: FeatDao = SqlFeatDao(sqliteDatabase)
        val itemDao: ItemDao = SqlItemDao(sqliteDatabase)
        val classDao: ClassDao = SqlClassDao(sqliteDatabase)
        val raceDao: RaceDao = SqlRaceDao(sqliteDatabase)
        val abilityDao: AbilityDao = SqlAbilityDao(sqliteDatabase)
        val characterDao: CharacterDao = SqlCharacterDao(sqliteDatabase)
        val imageDao: ImageDao = SqlImageDao(sqliteDatabase)
        val spelllistDao: SpelllistDao = SqlSpelllistDao(sqliteDatabase)
        val xpDao: XpDao = SqlXpDao(sqliteDatabase)
        val displayService: DisplayService =
            serviceFactory.getDisplayService(gameSystemType, context)
        val imageService: ImageService = AndroidImageServiceImpl(imageDao)
        val featService: FeatService = FeatServiceImpl(featDao)
        val skillService: SkillService = SkillServiceImpl(skillDao)
        val characterClassService: CharacterClassService = CharacterClassServiceImpl(classDao)
        val itemService: ItemService = ItemServiceImpl(itemDao)
        val raceService: RaceService = RaceServiceImpl(raceDao)
        val abilityService: AbilityService = AbilityServiceImpl(abilityDao)
        val characterService: CharacterService = CharacterServiceImpl(characterDao)
        val spelllistService: SpelllistService = SpelllistServiceImpl(spelllistDao)
        val xpService: XpService = XpServiceImpl(xpDao)
        val exportImportService: ExportImportService = ExportImportServiceImpl()
        val bodyService = BodyService()
        val characterCreatorService: CharacterCreatorService = CharacterCreatorServiceImpl()
        val ruleService: RuleService = serviceFactory.getRuleService(gameSystemType)
        val gameSystem: GameSystem =
            GameSystemCacheImpl(gameSystemType.id, gameSystemType.getName())

        gameSystem.displayService = displayService
        gameSystem.imageService = imageService
        gameSystem.characterClassService = characterClassService
        gameSystem.raceService = raceService
        gameSystem.characterService = characterService
        gameSystem.skillService = skillService
        gameSystem.featService = featService
        gameSystem.itemService = itemService
        gameSystem.abilityService = abilityService
        gameSystem.spelllistService = spelllistService
        gameSystem.xpService = xpService
        gameSystem.exportImportService = exportImportService
        gameSystem.bodyService = bodyService
        gameSystem.characterCreatorService = characterCreatorService
        gameSystem.ruleService = ruleService
        gameSystem.load()

        gameSystemHolder.gameSystem = gameSystem
        return gameSystem
    }

    private fun getDatabase(gameSystemType: GameSystemType): Database {
        val database = when (gameSystemType) {
            DNDV35 -> gameSystemHolder.dndv35DbHelper?.writableDatabase
            PATHFINDER -> gameSystemHolder.pathfinderDbHelper?.writableDatabase
            DND5E -> gameSystemHolder.dnd5eDbHelper?.writableDatabase
        }
        return SqliteDatabase(database!!)
    }

}
