package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.d20charactersheet.framework.dac.dao.BaseSpelllistDaoTest;

public class SQLiteSpelllistDaoTest extends BaseSpelllistDaoTest {

    private final Context context;

    public SQLiteSpelllistDaoTest(final Context context) {
        super();
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
        spelllistDao = new SQLiteSpelllistDao(db);
    }

}
