package com.android.ash.charactersheet.gui.sheet

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.android.ash.charactersheet.BuildConfig
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.boc.service.AndroidDisplayServiceImpl
import com.android.ash.charactersheet.boc.service.AndroidImageServiceImpl
import com.android.ash.charactersheet.boc.service.PreferenceServiceImpl
import com.android.ash.charactersheet.dac.dao.android.AndroidPreferenceDao
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase
import com.d20charactersheet.framework.boc.service.*
import com.d20charactersheet.framework.dac.dao.*
import com.d20charactersheet.framework.dac.dao.sql.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class GameSystemLoader : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferencesServiceHolder: PreferenceServiceHolder by inject()

    fun connectDatabases(context: Context) {
        val dbVersion = BuildConfig.VERSION_CODE
        gameSystemHolder.dndDbHelper = DBHelper(context, GameSystemType.DNDV35.databaseName, dbVersion,
                GameSystemType.DNDV35.createScripts, GameSystemType.DNDV35.updateScripts,
                GameSystemType.DNDV35.images)
        gameSystemHolder.pathfinderDbHelper = DBHelper(context, GameSystemType.PATHFINDER.databaseName, dbVersion,
                GameSystemType.PATHFINDER.createScripts, GameSystemType.PATHFINDER.updateScripts,
                GameSystemType.PATHFINDER.images)
        preferencesServiceHolder.preferenceService = PreferenceServiceImpl(AndroidPreferenceDao(context))
    }

    fun load(context: Context, gameSystemType: GameSystemType): GameSystem {

        gameSystemHolder.gameSystemType = gameSystemType

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
        val displayService: DisplayService = AndroidDisplayServiceImpl(context.resources)
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
        val ruleService: RuleService = getRuleService(gameSystemType)
        val gameSystem: GameSystem = GameSystemCacheImpl(gameSystemType.id, gameSystemType.getName())

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
        gameSystem.ruleService = ruleService
        gameSystem.load()

        gameSystemHolder.gameSystem = gameSystem
        return gameSystem
    }

    private fun getDatabase(gameSystemType: GameSystemType): Database {
        var database: SQLiteDatabase? = gameSystemHolder.pathfinderDbHelper?.writableDatabase
        if (GameSystemType.DNDV35 == gameSystemType) {
            database = gameSystemHolder.dndDbHelper?.writableDatabase
        }
        return SqliteDatabase(database!!)
    }

    private fun getRuleService(gameSystemType: GameSystemType): RuleService =
            if (gameSystemType == GameSystemType.DNDV35) {
                DnDv35RuleServiceImpl()
            } else {
                PathfinderRuleServiceImpl()
            }

}
