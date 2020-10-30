package com.android.ash.charactersheet.dac.dao.sql;

import android.content.Context;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper;
import com.android.ash.charactersheet.dac.dao.sql.sqlite.SqliteDatabase;
import com.d20charactersheet.framework.dac.dao.BaseImageDaoTest;
import com.d20charactersheet.framework.dac.dao.sql.Database;
import com.d20charactersheet.framework.dac.dao.sql.SqlImageDao;

import org.junit.Before;

import androidx.test.platform.app.InstrumentationRegistry;

public class SqlImageDaoInstrumentedTest extends BaseImageDaoTest {

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final DBHelper dbHelper = new DBHelper(context, BuildConfig.VERSION_CODE,
                GameSystemType.DNDV35);
        final Database sqLiteDatabase = new SqliteDatabase(dbHelper.getWritableDatabase());
        imageDao = new SqlImageDao(sqLiteDatabase);
    }

}
