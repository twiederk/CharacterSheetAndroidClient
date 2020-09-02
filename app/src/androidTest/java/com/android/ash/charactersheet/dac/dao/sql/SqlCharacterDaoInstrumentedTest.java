package com.android.ash.charactersheet.dac.dao.sql;

import android.content.Context;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase;
import com.d20charactersheet.framework.dac.dao.BaseCharacterDaoTest;
import com.d20charactersheet.framework.dac.dao.sql.SqlAbilityDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlCharacterDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlClassDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlFeatDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlRaceDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlSkillDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlSpelllistDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlXpDao;

import androidx.test.platform.app.InstrumentationRegistry;

public class SqlCharacterDaoInstrumentedTest extends BaseCharacterDaoTest {

    @Override
    public void setUp() throws Exception {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final DBHelper dbHelper = new DBHelper(context, GameSystemType.DNDV35.getDatabaseName(), BuildConfig.VERSION_CODE,
                GameSystemType.DNDV35.getCreateScripts(), GameSystemType.DNDV35.getUpdateScripts(),
                GameSystemType.DNDV35.getImages());
        final SqliteDatabase db = new SqliteDatabase(dbHelper.getWritableDatabase());

        skillDao = new SqlSkillDao(db);
        characterDao = new SqlCharacterDao(db);
        characterClassDao = new SqlClassDao(db);
        raceDao = new SqlRaceDao(db);
        abilityDao = new SqlAbilityDao(db);
        spelllistDao = new SqlSpelllistDao(db);
        xpDao = new SqlXpDao(db);
        featDao = new SqlFeatDao(db);
        super.setUp();
    }

}
