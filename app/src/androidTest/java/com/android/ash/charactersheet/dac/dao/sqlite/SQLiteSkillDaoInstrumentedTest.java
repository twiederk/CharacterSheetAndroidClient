package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.BuildConfig;
import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.dac.dao.BaseSkillDaoTest;

import org.junit.Before;

import java.util.List;

import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SQLiteSkillDaoInstrumentedTest extends BaseSkillDaoTest {

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final DBHelper dbHelper = new DBHelper(context, GameSystemType.DNDV35.getDatabaseName(), BuildConfig.VERSION_CODE,
                GameSystemType.DNDV35.getCreateScripts(), GameSystemType.DNDV35.getUpdateScripts(),
                GameSystemType.DNDV35.getImages());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        skillDao = new SQLiteSkillDao(db);
        characterDao = new SQLiteCharacterDao(db);
    }

    @Override
    public void testGetAllSkills() {
        final List<Skill> allSkills = skillDao.getAllSkills();
        assertEquals(44, allSkills.size());
        final Skill skill = getSkill(allSkills, SKILL_ID_CLIMB);
        assertNotNull(skill);
        assertEquals(skill.getId(), SKILL_ID_CLIMB);
        assertEquals("Climb", skill.getName());
        assertEquals(Attribute.STRENGTH, skill.getAttribute());
        assertTrue(skill.isUntrained());
    }

}
