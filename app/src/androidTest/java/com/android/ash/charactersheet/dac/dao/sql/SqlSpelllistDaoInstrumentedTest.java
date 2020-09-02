package com.android.ash.charactersheet.dac.dao.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase;
import com.d20charactersheet.framework.dac.dao.BaseSpelllistDaoTest;
import com.d20charactersheet.framework.dac.dao.sql.Database;
import com.d20charactersheet.framework.dac.dao.sql.SqlSpelllistDao;

import org.junit.Before;

import androidx.test.platform.app.InstrumentationRegistry;

public class SqlSpelllistDaoInstrumentedTest extends BaseSpelllistDaoTest {

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final DBHelper dbHelper = new DBHelper(context, GameSystemType.DNDV35.getDatabaseName(), BuildConfig.VERSION_CODE,
                GameSystemType.DNDV35.getCreateScripts(), GameSystemType.DNDV35.getUpdateScripts(),
                GameSystemType.DNDV35.getImages());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        final Database sqliteDatabase = new SqliteDatabase(db);
        spelllistDao = new SqlSpelllistDao(sqliteDatabase);
    }

}
