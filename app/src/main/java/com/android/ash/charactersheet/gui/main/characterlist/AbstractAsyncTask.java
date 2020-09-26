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
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase;
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
import com.d20charactersheet.framework.dac.dao.sql.Database;
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

        final Database sqliteDatabase = new SqliteDatabase(database);

        final SkillDao skillDao = new SqlSkillDao(sqliteDatabase);
        final FeatDao featDao = new SqlFeatDao(sqliteDatabase);
        final ItemDao itemDao = new SqlItemDao(sqliteDatabase);
        final ClassDao classDao = new SqlClassDao(sqliteDatabase);
        final RaceDao raceDao = new SqlRaceDao(sqliteDatabase);
        final AbilityDao abilityDao = new SqlAbilityDao(sqliteDatabase);
        final CharacterDao characterDao = new SqlCharacterDao(sqliteDatabase);
        final ImageDao imageDao = new SqlImageDao(sqliteDatabase);
        final SpelllistDao spelllistDao = new SqlSpelllistDao(sqliteDatabase);
        final XpDao xpDao = new SqlXpDao(sqliteDatabase);

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
