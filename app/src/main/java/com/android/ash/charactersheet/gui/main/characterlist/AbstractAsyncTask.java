package com.android.ash.charactersheet.gui.main.characterlist;

import android.app.Activity;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.android.ash.charactersheet.GameSystemHolder;
import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.boc.service.AndroidDisplayServiceImpl;
import com.android.ash.charactersheet.boc.service.AndroidImageServiceImpl;
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
import com.android.ash.charactersheet.gui.util.Logger;
import com.android.ash.charactersheet.gui.widget.waitanimation.WaitAnimation;
import com.d20charactersheet.framework.boc.service.AbilityService;
import com.d20charactersheet.framework.boc.service.AbilityServiceImpl;
import com.d20charactersheet.framework.boc.service.CharacterClassService;
import com.d20charactersheet.framework.boc.service.CharacterClassServiceImpl;
import com.d20charactersheet.framework.boc.service.CharacterService;
import com.d20charactersheet.framework.boc.service.CharacterServiceImpl;
import com.d20charactersheet.framework.boc.service.DisplayService;
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
import com.d20charactersheet.framework.boc.service.PathfinderRuleServiceImpl;
import com.d20charactersheet.framework.boc.service.RaceService;
import com.d20charactersheet.framework.boc.service.RaceServiceImpl;
import com.d20charactersheet.framework.boc.service.RuleService;
import com.d20charactersheet.framework.boc.service.SkillService;
import com.d20charactersheet.framework.boc.service.SkillServiceImpl;
import com.d20charactersheet.framework.boc.service.SpelllistService;
import com.d20charactersheet.framework.boc.service.SpelllistServiceImpl;
import com.d20charactersheet.framework.boc.service.XpService;
import com.d20charactersheet.framework.boc.service.XpServiceImpl;
import com.d20charactersheet.framework.dac.dao.AbilityDao;
import com.d20charactersheet.framework.dac.dao.CharacterDao;
import com.d20charactersheet.framework.dac.dao.ClassDao;
import com.d20charactersheet.framework.dac.dao.FeatDao;
import com.d20charactersheet.framework.dac.dao.ImageDao;
import com.d20charactersheet.framework.dac.dao.ItemDao;
import com.d20charactersheet.framework.dac.dao.RaceDao;
import com.d20charactersheet.framework.dac.dao.SkillDao;
import com.d20charactersheet.framework.dac.dao.SpelllistDao;
import com.d20charactersheet.framework.dac.dao.XpDao;

import kotlin.Lazy;

import static org.koin.java.KoinJavaComponent.inject;

/**
 * Create/Upgrade database and load game system in an asynchronous task, while displaying a wait animation.
 */
abstract class AbstractAsyncTask extends AsyncTask<Object, String, TaskResult> {

    private final Activity activity;
    final Resources resources;
    final GameSystemType gameSystemType;
    private final GameSystemLoadable gameSystemLoadable;
    WaitAnimation waitAnimation;

    /**
     * Creates an instance to execute the async task.
     *
     * @param activity           The activity to display the wait animation.
     * @param gameSystemLoadable Class called if the game system is loaded.
     */
    AbstractAsyncTask(final Activity activity, final GameSystemLoadable gameSystemLoadable) {
        super();
        this.activity = activity;
        this.gameSystemLoadable = gameSystemLoadable;
        Lazy<GameSystemHolder> gameSystemHolder = inject(GameSystemHolder.class);
        this.gameSystemType = gameSystemHolder.getValue().getGameSystemType();
        resources = activity.getResources();
    }

    @Override
    protected void onPreExecute() {
        activity.setContentView(R.layout.waitanimation);
        waitAnimation = activity.findViewById(R.id.wait_animation);
    }

    @Override
    protected void onProgressUpdate(final String... values) {
        final String text = values[0];
        waitAnimation.setText(text);
    }

    @Override
    protected void onPostExecute(final TaskResult result) {
        gameSystemLoadable.onGameSystemLoaded();
    }

    String getLoadGameSystemText() {
        String text;
        if (GameSystemType.DNDV35.equals(gameSystemType)) {
            text = resources.getString(R.string.character_list_wait_load_dndv35);
        } else {
            text = resources.getString(R.string.character_list_wait_load_pathfinder);
        }
        return text;
    }

    GameSystem createGameSystem(final SQLiteDatabase database) {
        Logger.debug("createGameSystem");
        Logger.debug("gameSystemType: " + gameSystemType);

        // final SkillDao skillDao = new DummySkillDao(SKILL);
        // final FeatDao featDao = new DummyFeatDao(FEAT);
        // final ItemDao itemDao = new DummyItemDao(WEAPON_FAMILY, WEAPON, ARMOR, GOOD);
        // final ClassDao classDao = new DummyClassDao(CLASS, CLASS_SKILL, CLASS_ABILITY);
        // final RaceDao raceDao = new DummyRaceDao(RACE, RACE_ABILITY);
        // final AbilityDao abilityDao = new DummyAbilityDao(ABILITY, ABILITY_PROPERTY);
        // final CharacterDao characterDao = new DummyCharacterDao(CHARACTER, CHARACTER_CLASS_LEVEL, CHARACTER_ABILITY,
        // CHARACTER_SKILL, CHARACTER_FEAT, CHARACTER_WEAPON, CHARACTER_ARMOR, CHARACTER_GOOD, CHARACTER_NOTE,
        // CHARACTER_ATTACK, CHARACTER_KNOWN_SPELL, CHARACTER_SPELL_SLOT, CHARACTER_SPELL_SLOT_ABILITY,
        // CHARACTER_SPELL_SLOT_FEAT);
        //
        // final ImageDao imageDao = new DummyImageDao(activity);
        // final SpelllistDao spelllistDao = new DummySpelllistDao(SPELL, SPELLLIST, SPELLLIST_SPELL,
        // KNOWN_SPELLS_TABLE,
        // KNOWN_SPELLS_LEVEL, SPELLS_PER_DAY_TABLE, SPELLS_PER_DAY_LEVEL);
        // final XpDao xpDao = new DummyXpDao(XP_TABLE, XP_LEVEL);

        final SkillDao skillDao = new SQLiteSkillDao(database);
        final FeatDao featDao = new SQLiteFeatDao(database);
        final ItemDao itemDao = new SQLiteItemDao(database);
        final ClassDao classDao = new SQLiteClassDao(database);
        final RaceDao raceDao = new SQLiteRaceDao(database);
        final AbilityDao abilityDao = new SQLiteAbilityDao(database);
        final CharacterDao characterDao = new SQLiteCharacterDao(database);
        final ImageDao imageDao = new SQLiteImageDao(database);
        final SpelllistDao spelllistDao = new SQLiteSpelllistDao(database);
        final XpDao xpDao = new SQLiteXpDao(database);

        final DisplayService displayService = new AndroidDisplayServiceImpl(resources);
        final ImageService imageService = new AndroidImageServiceImpl(imageDao);
        final FeatService featService = new FeatServiceImpl(featDao);
        final SkillService skillService = new SkillServiceImpl(skillDao);
        final CharacterClassService characterClassService = new CharacterClassServiceImpl(classDao);
        final ItemService itemService = new ItemServiceImpl(itemDao);
        final RaceService raceService = new RaceServiceImpl(raceDao);
        final AbilityService abilityService = new AbilityServiceImpl(abilityDao);
        final CharacterService characterService = new CharacterServiceImpl(characterDao);
        final SpelllistService spelllistService = new SpelllistServiceImpl(spelllistDao);
        final XpService xpService = new XpServiceImpl(xpDao);
        final ExportImportService exportImportService = new ExportImportServiceImpl();
        final RuleService ruleService = getRuleService();

        final GameSystem gameSystem = new GameSystemCacheImpl(gameSystemType.getId(), gameSystemType.getName());
        gameSystem.setDisplayService(displayService);
        gameSystem.setImageService(imageService);
        gameSystem.setCharacterClassService(characterClassService);
        gameSystem.setRaceService(raceService);
        gameSystem.setCharacterService(characterService);
        gameSystem.setSkillService(skillService);
        gameSystem.setFeatService(featService);
        gameSystem.setItemService(itemService);
        gameSystem.setAbilityService(abilityService);
        gameSystem.setSpelllistService(spelllistService);
        gameSystem.setXpService(xpService);
        gameSystem.setExportImportService(exportImportService);
        gameSystem.setRuleService(ruleService);

        gameSystem.load();
        return gameSystem;
    }

    private RuleService getRuleService() {
        RuleService ruleService;
        if (gameSystemType.equals(GameSystemType.DNDV35)) {
            ruleService = new DnDv35RuleServiceImpl();
        } else {
            ruleService = new PathfinderRuleServiceImpl();
        }
        return ruleService;
    }

    /**
     * Listener interface for the event game system is loaded.
     */
    public interface GameSystemLoadable {

        /**
         * Called if the game system is loaded.
         */
        void onGameSystemLoaded();
    }
}
