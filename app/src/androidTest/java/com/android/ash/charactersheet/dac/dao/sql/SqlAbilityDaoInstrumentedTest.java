package com.android.ash.charactersheet.dac.dao.sql;

import android.content.Context;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase;
import com.d20charactersheet.framework.dac.dao.BaseAbilityDaoTest;
import com.d20charactersheet.framework.dac.dao.sql.Database;
import com.d20charactersheet.framework.dac.dao.sql.SqlAbilityDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlSpelllistDao;

import org.junit.Before;

import androidx.test.platform.app.InstrumentationRegistry;

public class SqlAbilityDaoInstrumentedTest extends BaseAbilityDaoTest {

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final int dbVersion = BuildConfig.VERSION_CODE;
        final DBHelper dbHelper = new DBHelper(context, dbVersion,
                GameSystemType.DNDV35);
        final Database sqliteDatabase = new SqliteDatabase(dbHelper.getWritableDatabase());
        abilityDao = new SqlAbilityDao(sqliteDatabase);
        spelllistDao = new SqlSpelllistDao(sqliteDatabase);
    }

}
