package com.android.ash.charactersheet;

import android.content.Context;

import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.boc.service.AndroidImageServiceImpl;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase;
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
import com.d20charactersheet.framework.dac.dao.sql.SqlAbilityDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlCharacterDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlClassDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlFeatDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlImageDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlItemDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlRaceDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlSkillDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlSpelllistDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlXpDao;

public class AndroidObjectMother {

    private final GameSystem gameSystem;

    public AndroidObjectMother(final Context context) {
        final DBHelper dndv35DbHelper = new DBHelper(context, BuildConfig.VERSION_CODE,
                GameSystemType.DNDV35);
        final SqliteDatabase db = new SqliteDatabase(dndv35DbHelper.getWritableDatabase());

        final SkillService skillService = new SkillServiceImpl(new SqlSkillDao(db));
        final FeatService featService = new FeatServiceImpl(new SqlFeatDao(db));
        final CharacterClassService characterClassService = new CharacterClassServiceImpl(new SqlClassDao(db));
        final RaceService raceService = new RaceServiceImpl(new SqlRaceDao(db));
        final AbilityService abilityService = new AbilityServiceImpl(new SqlAbilityDao(db));
        final ItemService itemService = new ItemServiceImpl(new SqlItemDao(db));
        final CharacterService characterService = new CharacterServiceImpl(new SqlCharacterDao(db));
        final ImageService imageService = new AndroidImageServiceImpl(new SqlImageDao(db));
        final RuleService ruleService = new DnDv35RuleServiceImpl();
        final SpelllistService spelllistService = new SpelllistServiceImpl(new SqlSpelllistDao(db));
        final XpService xpService = new XpServiceImpl(new SqlXpDao(db));
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
