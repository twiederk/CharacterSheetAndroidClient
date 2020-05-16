package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.d20charactersheet.framework.dac.dao.BaseCharacterDaoTest;

import androidx.test.platform.app.InstrumentationRegistry;

public class SQLiteCharacterDaoInstrumentedTest extends BaseCharacterDaoTest {

    @Override
    public void setUp() throws Exception {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final DBHelper dbHelper = new DBHelper(context, GameSystemType.DNDV35.getDatabaseName(), BuildConfig.VERSION_CODE,
                GameSystemType.DNDV35.getCreateScripts(), GameSystemType.DNDV35.getUpdateScripts(),
                GameSystemType.DNDV35.getImages());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        skillDao = new SQLiteSkillDao(db);
        characterDao = new SQLiteCharacterDao(db);
        characterClassDao = new SQLiteClassDao(db);
        raceDao = new SQLiteRaceDao(db);
        abilityDao = new SQLiteAbilityDao(db);
        spelllistDao = new SQLiteSpelllistDao(db);
        xpDao = new SQLiteXpDao(db);
        featDao = new SQLiteFeatDao(db);
        super.setUp();
    }

}