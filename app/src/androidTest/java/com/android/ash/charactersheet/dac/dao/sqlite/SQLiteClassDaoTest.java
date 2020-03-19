package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.d20charactersheet.framework.boc.model.KnownSpellsTable;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.model.SpellsPerDayTable;
import com.d20charactersheet.framework.dac.dao.BaseClassDaoTest;

import org.junit.Before;

import java.util.List;

import androidx.test.platform.app.InstrumentationRegistry;

public class SQLiteClassDaoTest extends BaseClassDaoTest {


    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final DBHelper dbHelper = new DBHelper(context, GameSystemType.DNDV35.getDatabaseName(), BuildConfig.VERSION_CODE,
                GameSystemType.DNDV35.getCreateScripts(), GameSystemType.DNDV35.getUpdateScripts(),
                GameSystemType.DNDV35.getImages());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        skillDao = new SQLiteSkillDao(db);
        abilityDao = new SQLiteAbilityDao(db);
        characterClassDao = new SQLiteClassDao(db);
        spelllistDao = new SQLiteSpelllistDao(db);

        final List<Spelllist> allSpelllists = spelllistDao.getAllSpelllists(spelllistDao.getAllSpells());
        final List<KnownSpellsTable> allKnownSpellsTables = spelllistDao.getAllKnownSpellsTables();
        final List<SpellsPerDayTable> allSpellsPerDayTables = spelllistDao.getAllSpellsPerDayTables();

        allCharacterClasses = characterClassDao.getAllCharacterClasses(skillDao.getAllSkills(),
                abilityDao.getAllAbilities(allSpelllists, allKnownSpellsTables, allSpellsPerDayTables));
    }

}
