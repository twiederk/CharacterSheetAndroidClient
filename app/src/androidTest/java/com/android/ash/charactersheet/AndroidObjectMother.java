package com.android.ash.charactersheet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.boc.service.AndroidImageServiceImpl;
import com.android.ash.charactersheet.dac.dao.sqlite.DBHelper;
import com.android.ash.charactersheet.dac.dao.sqlite.SQLiteAbilityDao;
import com.android.ash.charactersheet.dac.dao.sqlite.SQLiteCharacterDao;
import com.android.ash.charactersheet.dac.dao.sqlite.SQLiteClassDao;
import com.android.ash.charactersheet.dac.dao.sqlite.SQLiteFeatDao;
import com.android.ash.charactersheet.dac.dao.sqlite.SQLiteImageDao;
import com.android.ash.charactersheet.dac.dao.sqlite.SQLiteItemDao;
import com.android.ash.charactersheet.dac.dao.sqlite.SQLiteRaceDao;
import com.android.ash.charactersheet.dac.dao.sqlite.SQLiteSkillDao;
import com.android.ash.charactersheet.dac.dao.sqlite.SQLiteSpelllistDao;
import com.android.ash.charactersheet.dac.dao.sqlite.SQLiteXpDao;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.service.AbilityService;
import com.d20charactersheet.framework.boc.service.AbilityServiceImpl;
import com.d20charactersheet.framework.boc.service.CharacterClassService;
import com.d20charactersheet.framework.boc.service.CharacterClassServiceImpl;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.CharacterServiceImpl;
import com.d20charactersheet.framework.boc.service.DnDv35RuleServiceImpl;
import com.d20charactersheet.framework.boc.service.ExportImportService;
import com.d20charactersheet.framework.boc.service.ExportImportServiceImpl;
import com.d20charactersheet.framework.boc.service.FeatService;
import com.d20charactersheet.framework.boc.service.FeatServiceImpl;
import com.d20charactersheet.framework.boc.service.GameSystem;
import com.d20charactersheet.framework.boc.service.GameSystemCacheImpl;
import com.d20charactersheet.framework.boc.service.ImageService;
import com.d20charactersheet.framework.boc.service.ItemService;
import com.d20charactersheet.framework.boc.service.ItemServiceImpl;
import com.d20charactersheet.framework.boc.service.RaceService;
import com.d20charactersheet.framework.boc.service.RaceServiceImpl;
import com.d20charactersheet.framework.boc.service.RuleService;
import com.d20charactersheet.framework.boc.service.SkillService;
import com.d20charactersheet.framework.boc.service.SkillServiceImpl;
import com.d20charactersheet.framework.boc.service.SpelllistService;
import com.d20charactersheet.framework.boc.service.SpelllistServiceImpl;
import com.d20charactersheet.framework.boc.service.XpService;
import com.d20charactersheet.framework.boc.service.XpServiceImpl;

public class AndroidObjectMother {

    private final GameSystem gameSystem;

    public AndroidObjectMother(final Context context) {
        final int dbVersion = Integer.parseInt(context.getString(R.string.app_version_code));
        final DBHelper dndv35DbHelper = new DBHelper(context, GameSystemType.DNDV35.getDatabaseName(), dbVersion,
                GameSystemType.DNDV35.getCreateScripts(), GameSystemType.DNDV35.getUpdateScripts(),
                GameSystemType.DNDV35.getImages());
        final SQLiteDatabase db = dndv35DbHelper.getWritableDatabase();
        final SkillService skillService = new SkillServiceImpl(new SQLiteSkillDao(db));
        final FeatService featService = new FeatServiceImpl(new SQLiteFeatDao(db));
        final CharacterClassService characterClassService = new CharacterClassServiceImpl(new SQLiteClassDao(db));
        final RaceService raceService = new RaceServiceImpl(new SQLiteRaceDao(db));
        final AbilityService abilityService = new AbilityServiceImpl(new SQLiteAbilityDao(db));
        final ItemService itemService = new ItemServiceImpl(new SQLiteItemDao(db));
        final CharacterService characterService = new CharacterServiceImpl(new SQLiteCharacterDao(db));
        final ImageService imageService = new AndroidImageServiceImpl(new SQLiteImageDao(db));
        final RuleService ruleService = new DnDv35RuleServiceImpl();
        final SpelllistService spelllistService = new SpelllistServiceImpl(new SQLiteSpelllistDao(db));
        final XpService xpService = new XpServiceImpl(new SQLiteXpDao(db));
        final ExportImportService exportImportService = new ExportImportServiceImpl();

        gameSystem = new GameSystemCacheImpl(GameSystemType.DNDV35.getId(), GameSystemType.DNDV35.getName());
        gameSystem.setSkillService(skillService);
        gameSystem.setFeatService(featService);
        gameSystem.setCharacterClassService(characterClassService);
        gameSystem.setItemService(itemService);
        gameSystem.setRaceService(raceService);
        gameSystem.setAbilityService(abilityService);
        gameSystem.setCharacterService(characterService);
        gameSystem.setSpelllistService(spelllistService);
        gameSystem.setImageService(imageService);
        gameSystem.setRuleService(ruleService);
        gameSystem.setXpService(xpService);
        gameSystem.setExportImportService(exportImportService);
    }

    public GameSystem getGameSystem() {
        return gameSystem;
    }

    public CharacterClass getCharacterClass(final String name) {
        for (final CharacterClass characterClass : gameSystem.getAllCharacterClasses()) {
            if (characterClass.getName().equals(name)) {
                return characterClass;
            }
        }
        throw new IllegalArgumentException("Can't get character class of " + name);
    }

    public Race getRace(final String name) {
        for (final Race race : gameSystem.getAllRaces()) {
            if (race.getName().equals(name)) {
                return race;
            }
        }
        throw new IllegalArgumentException("Can't get race of " + name);
    }
}
