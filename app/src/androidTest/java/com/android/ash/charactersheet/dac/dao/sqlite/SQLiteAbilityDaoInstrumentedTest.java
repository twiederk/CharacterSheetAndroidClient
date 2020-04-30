package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.d20charactersheet.framework.dac.dao.BaseAbilityDaoTest;

import org.junit.Before;

import androidx.test.platform.app.InstrumentationRegistry;

public class SQLiteAbilityDaoInstrumentedTest extends BaseAbilityDaoTest {

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final int dbVersion = BuildConfig.VERSION_CODE;
        final DBHelper dbHelper = new DBHelper(context, GameSystemType.DNDV35.getDatabaseName(), dbVersion,
                GameSystemType.DNDV35.getCreateScripts(), GameSystemType.DNDV35.getUpdateScripts(),
                GameSystemType.DNDV35.getImages());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        abilityDao = new SQLiteAbilityDao(db);
        spelllistDao = new SQLiteSpelllistDao(db);
    }

}
