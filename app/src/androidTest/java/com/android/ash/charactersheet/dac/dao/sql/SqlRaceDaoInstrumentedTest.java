package com.android.ash.charactersheet.dac.dao.sql;

import android.content.Context;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase;
import com.d20charactersheet.framework.dac.dao.BaseRaceDaoTest;
import com.d20charactersheet.framework.dac.dao.sql.SqlAbilityDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlClassDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlRaceDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlSkillDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlSpelllistDao;

import org.junit.Before;

import androidx.test.platform.app.InstrumentationRegistry;

public class SqlRaceDaoInstrumentedTest extends BaseRaceDaoTest {

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final DBHelper dbHelper = new DBHelper(context, GameSystemType.DNDV35.getDatabaseName(), BuildConfig.VERSION_CODE,
                GameSystemType.DNDV35.getCreateScripts(), GameSystemType.DNDV35.getUpdateScripts(),
                GameSystemType.DNDV35.getImages());
        final SqliteDatabase sqliteDatabase = new SqliteDatabase(dbHelper.getWritableDatabase());

        skillDao = new SqlSkillDao(sqliteDatabase);
        characterClassDao = new SqlClassDao(sqliteDatabase);
        raceDao = new SqlRaceDao(sqliteDatabase);
        abilityDao = new SqlAbilityDao(sqliteDatabase);
        spelllistDao = new SqlSpelllistDao(sqliteDatabase);
    }

}
