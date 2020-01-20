package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.ClassAbilityRowMapper;
import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.ClassRowMapper;
import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.RowMapper;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassAbility;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.dac.dao.ClassDao;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Provides access to character class data stored in an SQLite database.
 */
public class SQLiteClassDao extends BaseSQLiteDao implements ClassDao {

    private static final String SQL_GET_ID = SELECT + "id FROM " + TABLE_CLASS + " WHERE rowid = ?";

    private final RowMapper classRowMapper;

    /**
     * Creates DAO to persistent character class data, stored in an SQLite database.
     * 
     * @param db
     *            The database to access.
     */
    public SQLiteClassDao(final SQLiteDatabase db) {
        super(db);
        classRowMapper = new ClassRowMapper();
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.ClassDao#deleteSkill(com.d20charactersheet.framework.boc.model.Skill)
     */
    @Override
    public void deleteSkill(final Skill skill) {
        final String[] skillId = { Integer.toString(skill.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CLASS_SKILL, COLUMN_SKILL_ID + " = ?", skillId);
        }
    }

    @Override
    public List<CharacterClass> getAllCharacterClasses(final List<Skill> allSkills, final List<Ability> allAbilities) {
        final List<CharacterClass> characterClasses = selectCharacterClassTable();
        for (final CharacterClass characterClass : characterClasses) {
            final int characterClassId = characterClass.getId();
            final List<Skill> skills = selectCharacterClassSkillTable(characterClassId, allSkills);
            characterClass.setSkills(skills);
            final List<ClassAbility> classAbilities = selectClassAbilityTable(characterClassId, allAbilities);
            characterClass.setClassAbilities(classAbilities);
        }
        return characterClasses;
    }

    private List<CharacterClass> selectCharacterClassTable() {
        final List<CharacterClass> characterClasses = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(SQL_GET_ALL_CLASSES, new String[0]);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final CharacterClass characterClass = (CharacterClass) classRowMapper.mapRow(cursor);
                characterClasses.add(characterClass);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get all classes", sqlException);
        } finally {
            close(cursor);
        }
        return characterClasses;
    }

    private List<Skill> selectCharacterClassSkillTable(final int id, final List<Skill> allSkills) {
        final List<Skill> skills = new ArrayList<>();
        Cursor cursor = null;
        try {
            final String[] params = new String[] { Integer.toString(id) };
            cursor = db.rawQuery(SQL_GET_CLASS_SKILLS, params);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final Skill skill = getSkill(allSkills, cursor.getInt(0));
                skills.add(skill);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get all classes skills", sqlException);
        } finally {
            close(cursor);
        }
        return skills;
    }

    private Skill getSkill(final List<Skill> allSkills, final int skillId) {
        for (final Skill skill : allSkills) {
            if (skill.getId() == skillId) {
                return skill;
            }
        }
        throw new IllegalArgumentException("Can't determine skill of id: " + skillId);
    }

    private List<ClassAbility> selectClassAbilityTable(final int characterClassId, final List<Ability> allAbilities) {
        final List<ClassAbility> classAbilities = new ArrayList<>();
        Cursor cursor = null;
        try {
            final String[] params = new String[] { Integer.toString(characterClassId) };
            cursor = db.rawQuery(SQL_GET_CLASS_ABILITIES, params);
            final RowMapper classAbilityRowMapper = new ClassAbilityRowMapper(allAbilities);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final ClassAbility classAbility = (ClassAbility) classAbilityRowMapper.mapRow(cursor);
                classAbilities.add(classAbility);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get all class abilities", sqlException);
        } finally {
            close(cursor);
        }
        return classAbilities;
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.ClassDao#updateCharacterClass(com.d20charactersheet.framework.boc.model.CharacterClass)
     */
    @Override
    public void updateCharacterClass(final CharacterClass characterClass) {
        deleteCharacterClass(characterClass);
        updateCharacterClassTable(characterClass);
        insertClassAbilityTable(characterClass);
        insertCharacterClassSkillTable(characterClass);
    }

    private void deleteCharacterClass(final CharacterClass characterClass) {
        final String[] characterClassId = new String[] { Integer.toString(characterClass.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CLASS, COLUMN_ID + " = ?", characterClassId);
            db.delete(TABLE_CLASS_ABILITY, COLUMN_CLASS_ID + " = ?", characterClassId);
            db.delete(TABLE_CLASS_SKILL, COLUMN_CLASS_ID + " = ?", characterClassId);
        }

    }

    private void updateCharacterClassTable(final CharacterClass characterClass) {
        final ContentValues contentValues = getContentValues(characterClass);
        contentValues.put(COLUMN_ID, characterClass.getId());
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_CLASS, null, contentValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert class in class table");
            }
        }
    }

    private ContentValues getContentValues(final CharacterClass characterClass) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, characterClass.getName());
        contentValues.put(COLUMN_SAVES, setEnum(characterClass.getSaves()));
        contentValues.put(COLUMN_ALIGNMENTS, setEnum(characterClass.getAlignments()));
        contentValues.put(COLUMN_BASE_ATTACK_BONUS, characterClass.getBaseAttackBonus().ordinal());
        contentValues.put(COLUMN_SKILL_POINTS_PER_LEVEL, characterClass.getSkillPointsPerLevel());
        contentValues.put(COLUMN_HIT_DIE_ID, characterClass.getHitDie().ordinal());
        return contentValues;
    }

    private void insertClassAbilityTable(final CharacterClass characterClass) {
        synchronized (DBHelper.DB_LOCK) {
            for (final ClassAbility classAbility : characterClass.getClassAbilities()) {
                final ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_CLASS_ID, characterClass.getId());
                contentValues.put(COLUMN_ABILITY_ID, classAbility.getAbility().getId());
                contentValues.put(COLUMN_LEVEL, classAbility.getLevel());
                final long rowId = db.insertOrThrow(TABLE_CLASS_ABILITY, null, contentValues);
                if (rowId == -1) {
                    throw new SQLException("Can't insert ability in class ability table");
                }
            }
        }
    }

    private void insertCharacterClassSkillTable(final CharacterClass characterClass) {
        synchronized (DBHelper.DB_LOCK) {
            for (final Skill skill : characterClass.getSkills()) {
                final ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_CLASS_ID, characterClass.getId());
                contentValues.put(COLUMN_SKILL_ID, skill.getId());
                final long rowId = db.insertOrThrow(TABLE_CLASS_SKILL, null, contentValues);
                if (rowId == -1) {
                    throw new SQLException("Can't insert skill in class skill table");
                }
            }
        }
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.ClassDao#createCharacterClass(com.d20charactersheet.framework.boc.model.CharacterClass)
     */
    @Override
    public CharacterClass createCharacterClass(final CharacterClass characterClass) {
        insertCharacterClassTable(characterClass);
        insertClassAbilityTable(characterClass);
        insertCharacterClassSkillTable(characterClass);
        return characterClass;
    }

    private void insertCharacterClassTable(final CharacterClass characterClass) {
        final ContentValues contentValues = getContentValues(characterClass);
        contentValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_CLASS, null, contentValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert class in class table");
            }
            characterClass.setId(getId(SQL_GET_ID, rowId));
        }
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.ClassDao#addSkill(com.d20charactersheet.framework.boc.model.Skill,
     *      java.util.List)
     */
    @Override
    public void addSkill(final Skill skill, final List<CharacterClass> characterClasses) {
        synchronized (DBHelper.DB_LOCK) {
            for (final CharacterClass characterClass : characterClasses) {
                final ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_CLASS_ID, characterClass.getId());
                contentValues.put(COLUMN_SKILL_ID, skill.getId());
                final long rowId = db.insertOrThrow(TABLE_CLASS_SKILL, null, contentValues);
                if (rowId == -1) {
                    throw new SQLException("Can't insert skill (" + skill + ") to class (" + characterClass + ")");
                }
            }
        }
    }

    private int setEnum(final EnumSet<?> enumset) {
        int result = 0;
        for (final Enum<?> currentEnum : enumset) {
            result += 1 << currentEnum.ordinal();
        }
        return result;
    }

}
