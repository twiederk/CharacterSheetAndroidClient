package com.android.ash.charactersheet.dac.dao.sql;

import android.content.Context;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase;
import com.d20charactersheet.framework.dac.dao.BaseFeatDaoTest;
import com.d20charactersheet.framework.dac.dao.sql.SqlCharacterDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlFeatDao;

import org.junit.Before;

import androidx.test.platform.app.InstrumentationRegistry;

public class SqlFeatDaoInstrumentedTest extends BaseFeatDaoTest {

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final DBHelper dbHelper = new DBHelper(context, BuildConfig.VERSION_CODE,
                GameSystemType.DNDV35);
        final SqliteDatabase db = new SqliteDatabase(dbHelper.getWritableDatabase());
        featDao = new SqlFeatDao(db);
        characterDao = new SqlCharacterDao(db);
    }

}
