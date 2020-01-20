package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.RowMapper;
import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.SkillRowMapper;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.dac.dao.SkillDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of data interface SkillDao using SQLite3 database.
 */
public class SQLiteSkillDao extends BaseSQLiteDao implements SkillDao {

    private static final String SQL_GET_ID = SELECT + "id " //
            + FROM + TABLE_SKILL + " " //
            + WHERE + "rowid = ?";

    private final RowMapper skillRowMapper;

    /**
     * Creates SQLite3 dependent implementation of SkillDao.
     * 
     * @param db
     *            The database to access.
     */
    public SQLiteSkillDao(final SQLiteDatabase db) {
        super(db);
        skillRowMapper = new SkillRowMapper();
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.SkillDao#getAllSkills()
     */
    @Override
    public List<Skill> getAllSkills() {
        final List<Skill> skills = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(SQL_GET_ALL_SKILLS, new String[0]);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final Skill skill = (Skill) skillRowMapper.mapRow(cursor);
                skills.add(skill);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get all skills", sqlException);
        } finally {
            close(cursor);
        }
        return skills;
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.SkillDao#createSkill(com.d20charactersheet.framework.boc.model.Skill)
     */
    @Override
    public Skill createSkill(final Skill skill) {
        insertSkillTable(skill);
        skill.setId(skill.getId());
        return skill;
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.SkillDao#updateSkill(com.d20charactersheet.framework.boc.model.Skill)
     */
    @Override
    public Skill updateSkill(final Skill skill) {
        updateSkillTable(skill);
        return skill;
    }

    private void updateSkillTable(final Skill skill) {
        final ContentValues contentValues = getSkillTableContentValues(skill);
        final String[] bindVariables = new String[1];
        bindVariables[0] = Integer.toString(skill.getId());
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.update(TABLE_SKILL, contentValues, SQL_WHERE_ID, bindVariables);
            if (numberOfAffectedRows != 1) {
                throw new SQLException("More or Less than 1 row affected: " + numberOfAffectedRows);
            }
        }
    }

    private ContentValues getSkillTableContentValues(final Skill skill) {
        final ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, skill.getName());
        values.put(COLUMN_DESCRIPTION, skill.getDescription());
        values.put(COLUMN_ATTRIBUTE_ID, skill.getAttribute().ordinal());
        values.put(COLUMN_UNTRAINED, skill.isUntrained());
        return values;
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.SkillDao#deleteSkill(com.d20charactersheet.framework.boc.model.Skill)
     */
    @Override
    public void deleteSkill(final Skill skill) {

        // delete skill (table skill)
        final String[] skillId = new String[] { Integer.toString(skill.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.delete(TABLE_SKILL, COLUMN_ID + " = ?", skillId);
            if (numberOfAffectedRows == 0) {
                throw new SQLException("Can't delete skill: " + skill);
            }
        }

    }

    private void insertSkillTable(final Skill skill) {
        final ContentValues skillValues = getSkillTableContentValues(skill);
        skillValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_SKILL, null, skillValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert skill in skill table");
            }
            skill.setId(getId(SQL_GET_ID, rowId));
        }
    }

    @Override
    public String getSkillDescription(final int skillId) {
        Cursor cursor = null;
        String description = "";
        try {
            cursor = db.rawQuery(SQL_GET_SKILL_DESCRIPTION, new String[] { Integer.toString(skillId) });
            cursor.moveToFirst();
            description = cursor.getString(0);
        } catch (final SQLException sqlException) {
            Logger.error("Can't get description of skill with id: " + skillId, sqlException);
        } finally {
            close(cursor);
        }
        return description;
    }
}
