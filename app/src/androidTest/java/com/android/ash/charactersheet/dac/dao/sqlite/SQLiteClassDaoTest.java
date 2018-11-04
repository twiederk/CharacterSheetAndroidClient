package com.android.ash.charactersheet.dac.dao.sqlite;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.R;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.d20charactersheet.framework.boc.model.KnownSpellsTable;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.model.SpellsPerDayTable;
import com.d20charactersheet.framework.dac.dao.BaseClassDaoTest;

public class SQLiteClassDaoTest extends BaseClassDaoTest {

    private final Context context;

    public SQLiteClassDaoTest(final Context context) {
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
        skillDao = new SQLiteSkillDao(db);
        abilityDao = new SQLiteAbilityDao(db);
        characterClassDao = new SQLiteClassDao(db);
        spelllistDao = new SQLiteSpelllistDao(db);

        final List<Spelllist> allSpelllists = spelllistDao.getAllSpelllists(spelllistDao.getAllSpells());
        final List<KnownSpellsTable> allKnownSpellsTables = spelllistDao.getAllKnownSpellsTables();
        final List<SpellsPerDayTable> allSpellsPerDayTables = spelllistDao.getAllSpellsPerDayTables();

        allCharacterClasses = characterClassDao.getAllCharacterClasses(skillDao.getAllSkills(),
                abilityDao.getAllAbilities(allSpelllists, allKnownSpellsTables, allSpellsPerDayTables));
    }

}
