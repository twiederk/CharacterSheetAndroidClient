package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.d20charactersheet.framework.dac.dao.BaseFeatDaoTest;

public class SQLiteFeatDaoTest extends BaseFeatDaoTest {

    private final Context context;

    public SQLiteFeatDaoTest(final Context context) {
        this.context = context;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final int dbVersion = Integer.parseInt(context.getString(R.string.app_version_code));
        final DBHelper dbHelper = new DBHelper(context, GameSystemType.DNDV35.getDatabaseName(), dbVersion,
                GameSystemType.DNDV35.getCreateScripts(), GameSystemType.DNDV35.getUpdateScripts(),
                GameSystemType.DNDV35.getImages());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        featDao = new SQLiteFeatDao(db);
        characterDao = new SQLiteCharacterDao(db);

    }

}
