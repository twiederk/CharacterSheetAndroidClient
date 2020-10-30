package com.android.ash.charactersheet.dac.dao.sql;

import android.content.Context;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase;
import com.d20charactersheet.framework.dac.dao.BaseItemDaoTest;
import com.d20charactersheet.framework.dac.dao.sql.Database;
import com.d20charactersheet.framework.dac.dao.sql.SqlCharacterDao;
import com.d20charactersheet.framework.dac.dao.sql.SqlItemDao;

import org.junit.Before;

import androidx.test.platform.app.InstrumentationRegistry;

public class SqlItemDaoInstrumentedTest extends BaseItemDaoTest {

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final DBHelper dbHelper = new DBHelper(context, BuildConfig.VERSION_CODE,
                GameSystemType.DNDV35);
        final Database db = new SqliteDatabase(dbHelper.getWritableDatabase());
        itemDao = new SqlItemDao(db);
        characterDao = new SqlCharacterDao(db);
    }

}
