package com.android.ash.charactersheet.dac.dao.sql;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase;
import com.d20charactersheet.framework.boc.model.KnownSpellsTable;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.model.SpellsPerDayTable;
import com.d20charactersheet.framework.dac.dao.BaseClassDaoTest;
import com.d20charactersheet.framework.dac.dao.sql.SqlAbilityDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlClassDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlSkillDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlSpelllistDao;

import org.junit.Before;

import java.util.List;

public class SqlClassDaoInstrumentedTest extends BaseClassDaoTest {


    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final DBHelper dbHelper = new DBHelper(context, BuildConfig.VERSION_CODE,
                GameSystemType.DNDV35);
        final SqliteDatabase sqliteDatabase = new SqliteDatabase(dbHelper.getWritableDatabase());

        skillDao = new SqlSkillDao(sqliteDatabase);
        abilityDao = new SqlAbilityDao(sqliteDatabase);
        classDao = new SqlClassDao(sqliteDatabase);
        spelllistDao = new SqlSpelllistDao(sqliteDatabase);

        final List<Spelllist> allSpelllists = spelllistDao.getAllSpelllists(spelllistDao.getAllSpells());
        final List<KnownSpellsTable> allKnownSpellsTables = spelllistDao.getAllKnownSpellsTables();
        final List<SpellsPerDayTable> allSpellsPerDayTables = spelllistDao.getAllSpellsPerDayTables();

        allCharacterClasses = classDao.getAllCharacterClasses(skillDao.getAllSkills(),
                abilityDao.getAllAbilities(allSpelllists, allKnownSpellsTables, allSpellsPerDayTables));
    }

}
