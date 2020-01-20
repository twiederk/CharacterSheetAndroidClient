package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.AbilityConfigRowMapper;
import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.RowMapper;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.ExtraFeatsAbility;
import com.d20charactersheet.framework.boc.model.ExtraSkillPointsAbility;
import com.d20charactersheet.framework.boc.model.KnownSpellsTable;
import com.d20charactersheet.framework.boc.model.School;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.d20charactersheet.framework.boc.model.SpellsPerDayTable;
import com.d20charactersheet.framework.dac.abilitybuilder.AbilityBuilder;
import com.d20charactersheet.framework.dac.abilitybuilder.AbilityBuilderFactory;
import com.d20charactersheet.framework.dac.abilitybuilder.AbilityConfig;
import com.d20charactersheet.framework.dac.abilitybuilder.DefaultAbilityBuilder;
import com.d20charactersheet.framework.dac.abilitybuilder.ExtraFeatsAbilityBuilder;
import com.d20charactersheet.framework.dac.abilitybuilder.ExtraSkillPointsAbilityBuilder;
import com.d20charactersheet.framework.dac.abilitybuilder.SpelllistAbilityBuilder;
import com.d20charactersheet.framework.dac.dao.AbilityDao;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.d20charactersheet.framework.dac.abilitybuilder.ExtraFeatsAbilityBuilder.KEY_NUMBER_OF_EXTRA_FEATS;
import static com.d20charactersheet.framework.dac.abilitybuilder.ExtraSkillPointsAbilityBuilder.KEY_NUMBER_OF_SKILL_POINTS;
import static com.d20charactersheet.framework.dac.abilitybuilder.SpelllistAbilityBuilder.ANY_SCHOOL_ID;
import static com.d20charactersheet.framework.dac.abilitybuilder.SpelllistAbilityBuilder.KEY_CASTING_TYPE_ID;
import static com.d20charactersheet.framework.dac.abilitybuilder.SpelllistAbilityBuilder.KEY_KNOWN_SPELLS_TABLE_ID;
import static com.d20charactersheet.framework.dac.abilitybuilder.SpelllistAbilityBuilder.KEY_SCHOOL_ID;
import static com.d20charactersheet.framework.dac.abilitybuilder.SpelllistAbilityBuilder.KEY_SPELLLIST_ID;
import static com.d20charactersheet.framework.dac.abilitybuilder.SpelllistAbilityBuilder.KEY_SPELLS_PER_DAY_TABLE_ID;
import static com.d20charactersheet.framework.dac.abilitybuilder.SpelllistAbilityBuilder.KEY_SPELL_ATTRIBUTE_ID;
import static com.d20charactersheet.framework.dac.abilitybuilder.SpelllistAbilityBuilder.KEY_SPELL_SOURCE_ID;

/**
 * Provides access to the ability tables in the SQLite database.
 */
public class SQLiteAbilityDao extends BaseSQLiteDao implements AbilityDao {

    private static final String SQL_GET_ID = SELECT + "id FROM " + TABLE_ABILITY + " WHERE rowid = ?";

    private static final String SQL_WHERE_ABILITY_ID_AND_PROPERTY_KEY = COLUMN_ABILITY_ID + " = ? AND "
            + COLUMN_PROPERTY_KEY + " = ?";

    private final RowMapper abilityConfigRowMapper;

    /**
     * Creates SQLiteAbilityDao accessin the given database.
     * 
     * @param db
     *            The database to access.
     */
    public SQLiteAbilityDao(final SQLiteDatabase db) {
        super(db);
        abilityConfigRowMapper = new AbilityConfigRowMapper();
    }

    @Override
    public List<Ability> getAllAbilities(final List<Spelllist> allSpelllists,
            final List<KnownSpellsTable> allKnownSpellsTables, final List<SpellsPerDayTable> allSpellsPerDayTables) {
        final List<Ability> allAbilities = new ArrayList<>();
        final AbilityBuilderFactory abilityBuilderFactoy = createAbilityBuilderFactory(allSpelllists,
                allKnownSpellsTables, allSpellsPerDayTables);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(SQL_GET_ALL_ABILITIES, new String[0]);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final AbilityConfig abilityConfig = (AbilityConfig) abilityConfigRowMapper.mapRow(cursor);
                selectAbilityProperties(abilityConfig);
                final Ability ability = createAbility(abilityConfig, abilityBuilderFactoy);
                allAbilities.add(ability);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get all abilities", sqlException);
        } finally {
            close(cursor);
        }
        return allAbilities;
    }

    private void selectAbilityProperties(final AbilityConfig abilityConfig) {
        if (!abilityConfig.getClassname().equals(Ability.class.getSimpleName())) {
            final Map<String, String> abilityProperties = new HashMap<>();
            Cursor cursor = null;
            try {
                final String[] params = new String[] { Integer.toString(abilityConfig.getId()) };
                cursor = db.rawQuery(SQL_GET_ABILITY_PROPERTIES, params);
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    final String key = cursor.getString(0);
                    final String value = cursor.getString(1);
                    abilityProperties.put(key, value);
                }
            } catch (final SQLException sqlException) {
                Logger.error("Can't get properties of ability: " + abilityConfig, sqlException);
            } finally {
                close(cursor);
            }
            abilityConfig.setProperties(abilityProperties);
        }
    }

    private Ability createAbility(final AbilityConfig abilityConfig, final AbilityBuilderFactory abilityBuilderFactory) {
        final AbilityBuilder abilityBuilder = abilityBuilderFactory.getAbilityBuilder(abilityConfig.getClassname());
        return abilityBuilder.createAbility(abilityConfig);
    }

    @Override
    public Ability createAbility(final Ability ability) {
        insertAbilityTable(ability);
        insertAbilityPropertyTable(ability);
        return ability;
    }

    private void insertAbilityTable(final Ability ability) {
        final ContentValues abilityValues = getAbilityValues(ability);
        abilityValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_ABILITY, null, abilityValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert ability in ability table");
            }
            ability.setId(getId(SQL_GET_ID, rowId));
        }
    }

    private ContentValues getAbilityValues(final Ability ability) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, ability.getName());
        contentValues.put(COLUMN_DESCRIPTION, ability.getDescription());
        contentValues.put(COLUMN_ABILITY_TYPE_ID, ability.getAbilityType().ordinal());
        contentValues.put(COLUMN_CLASSNAME, ability.getClass().getSimpleName());
        return contentValues;
    }

    private void insertAbilityPropertyTable(final Ability ability) {
        if (ability instanceof ExtraFeatsAbility) {
            insertExtraFeatAbility(ability);
        } else if (ability instanceof ExtraSkillPointsAbility) {
            insertExtraSkillPointsAbility(ability);
        } else if (ability instanceof SpelllistAbility) {
            insertSpelllistAbility(ability);
        }
    }

    private void insertExtraFeatAbility(final Ability ability) {
        final ExtraFeatsAbility extraFeatsAbility = (ExtraFeatsAbility) ability;
        insertAbilityProperty(ability.getId(), KEY_NUMBER_OF_EXTRA_FEATS, extraFeatsAbility.getNumberOfFeats());
    }

    private void insertExtraSkillPointsAbility(final Ability ability) {
        final ExtraSkillPointsAbility extraSkillPointsAbility = (ExtraSkillPointsAbility) ability;
        insertAbilityProperty(ability.getId(), KEY_NUMBER_OF_SKILL_POINTS, extraSkillPointsAbility.getSkillPoints());
    }

    private void insertSpelllistAbility(final Ability ability) {
        final SpelllistAbility spelllistAbility = (SpelllistAbility) ability;
        insertAbilityProperty(ability.getId(), KEY_SPELLLIST_ID, spelllistAbility.getSpelllist().getId());
        insertAbilityProperty(ability.getId(), KEY_SPELL_ATTRIBUTE_ID, spelllistAbility.getSpellAttribute().ordinal());
        insertAbilityProperty(ability.getId(), KEY_CASTING_TYPE_ID, spelllistAbility.getCastingType().ordinal());
        insertAbilityProperty(ability.getId(), KEY_SPELL_SOURCE_ID, spelllistAbility.getSpellSource().ordinal());
        insertAbilityProperty(ability.getId(), KEY_KNOWN_SPELLS_TABLE_ID, spelllistAbility.getKnownSpellsTable()
                .getId());
        insertAbilityProperty(ability.getId(), KEY_SPELLS_PER_DAY_TABLE_ID, spelllistAbility.getSpellsPerDayTable()
                .getId());
        insertAbilityProperty(ability.getId(),
                spelllistAbility.isAttributeBonusSpellSlots());
        insertAbilityProperty(ability.getId(), KEY_SCHOOL_ID, getSchoolId(spelllistAbility.getSchools()));
    }

    private int getSchoolId(final EnumSet<School> schools) {
        if (schools.size() == 1) {
            final School[] school = new School[1];
            schools.toArray(school);
            return school[0].ordinal();
        }
        return ANY_SCHOOL_ID;
    }

    private void insertAbilityProperty(final int abilityId, final boolean property_value) {
        insertAbilityProperty(abilityId, SpelllistAbilityBuilder.KEY_ATTRIBUTE_BONUS_SPELL_SLOTS, Boolean.toString(property_value));
    }

    private void insertAbilityProperty(final int abilityId, final String property_key, final int property_value) {
        insertAbilityProperty(abilityId, property_key, Integer.toString(property_value));
    }

    private void insertAbilityProperty(final int abilityId, final String property_key, final String property_value) {
        final ContentValues contentValues = getAbilityPropertyContentValues(abilityId, property_key, property_value);

        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_ABILITY_PROPERTY, null, contentValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert ability property in ability_property table");
            }
        }
    }

    @Override
    public void updateAbility(final Ability ability) {
        updateAbilityTable(ability);
        updateAbilityPropertyTable(ability);
    }

    private void updateAbilityTable(final Ability ability) {
        final ContentValues abilityValues = getAbilityValues(ability);
        final String[] bindVariables = new String[] { Integer.toString(ability.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.update(TABLE_ABILITY, abilityValues, SQL_WHERE_ID, bindVariables);
            if (numberOfAffectedRows != 1) {
                throw new SQLException("More or Less than 1 row affected: " + numberOfAffectedRows);
            }
        }
    }

    private void updateAbilityPropertyTable(final Ability ability) {
        if (ability instanceof ExtraFeatsAbility) {
            updateExtraFeatAbility(ability);
        } else if (ability instanceof ExtraSkillPointsAbility) {
            updateExtraSkillPointsAbility(ability);
        } else if (ability instanceof SpelllistAbility) {
            updateSpelllistAbility(ability);
        }
    }

    private void updateExtraFeatAbility(final Ability ability) {
        final ExtraFeatsAbility extraFeatsAbility = (ExtraFeatsAbility) ability;
        updateAbilityProperty(ability.getId(), KEY_NUMBER_OF_EXTRA_FEATS, extraFeatsAbility.getNumberOfFeats());
    }

    private void updateExtraSkillPointsAbility(final Ability ability) {
        final ExtraSkillPointsAbility extraSkillPointsAbility = (ExtraSkillPointsAbility) ability;
        updateAbilityProperty(ability.getId(), KEY_NUMBER_OF_SKILL_POINTS, extraSkillPointsAbility.getSkillPoints());
    }

    private void updateSpelllistAbility(final Ability ability) {
        final SpelllistAbility spelllistAbility = (SpelllistAbility) ability;
        updateAbilityProperty(ability.getId(), KEY_SPELLLIST_ID, spelllistAbility.getSpelllist().getId());
        updateAbilityProperty(ability.getId(), KEY_SPELL_ATTRIBUTE_ID, spelllistAbility.getSpellAttribute().ordinal());
        updateAbilityProperty(ability.getId(), KEY_CASTING_TYPE_ID, spelllistAbility.getCastingType().ordinal());
        updateAbilityProperty(ability.getId(), KEY_SPELL_SOURCE_ID, spelllistAbility.getSpellSource().ordinal());
        updateAbilityProperty(ability.getId(), KEY_KNOWN_SPELLS_TABLE_ID, spelllistAbility.getKnownSpellsTable()
                .getId());
        updateAbilityProperty(ability.getId(), KEY_SPELLS_PER_DAY_TABLE_ID, spelllistAbility.getSpellsPerDayTable()
                .getId());
        updateAbilityProperty(ability.getId(),
                spelllistAbility.isAttributeBonusSpellSlots());
        updateAbilityProperty(ability.getId(), KEY_SCHOOL_ID, getSchoolId(spelllistAbility.getSchools()));
    }

    private void updateAbilityProperty(final int abilityId, final boolean property_value) {
        updateAbilityProperty(abilityId, SpelllistAbilityBuilder.KEY_ATTRIBUTE_BONUS_SPELL_SLOTS, Boolean.toString(property_value));
    }

    private void updateAbilityProperty(final int abilityId, final String property_key, final int property_value) {
        updateAbilityProperty(abilityId, property_key, Integer.toString(property_value));
    }

    private void updateAbilityProperty(final int abilityId, final String property_key, final String property_value) {
        final ContentValues contentValues = getAbilityPropertyContentValues(abilityId, property_key, property_value);

        final String[] bindVariables = new String[] { Integer.toString(abilityId), property_key };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.update(TABLE_ABILITY_PROPERTY, contentValues,
                    SQL_WHERE_ABILITY_ID_AND_PROPERTY_KEY, bindVariables);
            if (numberOfAffectedRows == 0) {
                db.insertOrThrow(TABLE_ABILITY_PROPERTY, null, contentValues);
            }
            if (numberOfAffectedRows > 1) {
                throw new SQLException("More than 1 row affected: " + numberOfAffectedRows);
            }
        }
    }

    private ContentValues getAbilityPropertyContentValues(final int abilityId, final String property_key,
            final String property_value) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ABILITY_ID, abilityId);
        contentValues.put(COLUMN_PROPERTY_KEY, property_key);
        contentValues.put(COLUMN_PROPERTY_VALUE, property_value);
        return contentValues;
    }

    @Override
    public void deleteAbility(final Ability ability) {
        final String[] abilityId = new String[] { Integer.toString(ability.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_ABILITY, COLUMN_ID + " = ?", abilityId);
            db.delete(TABLE_ABILITY_PROPERTY, COLUMN_ABILITY_ID + " = ?", abilityId);
        }
    }

    @Override
    public AbilityBuilderFactory createAbilityBuilderFactory(final List<Spelllist> allSpelllists,
            final List<KnownSpellsTable> allKnownSpellsTables, final List<SpellsPerDayTable> allSpellsPerDayTables) {
        final AbilityBuilderFactory abilityBuilderFactory = new AbilityBuilderFactory();
        abilityBuilderFactory.registerAbilityBuilder(Ability.class.getSimpleName(), new DefaultAbilityBuilder());
        abilityBuilderFactory.registerAbilityBuilder(ExtraFeatsAbility.class.getSimpleName(),
                new ExtraFeatsAbilityBuilder());
        abilityBuilderFactory.registerAbilityBuilder(ExtraSkillPointsAbility.class.getSimpleName(),
                new ExtraSkillPointsAbilityBuilder());
        abilityBuilderFactory.registerAbilityBuilder(SpelllistAbility.class.getSimpleName(),
                new SpelllistAbilityBuilder(allSpelllists, allKnownSpellsTables, allSpellsPerDayTables));
        return abilityBuilderFactory;
    }
}
