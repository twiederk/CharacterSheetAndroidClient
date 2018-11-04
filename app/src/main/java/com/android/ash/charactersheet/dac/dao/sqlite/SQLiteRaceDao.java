package com.android.ash.charactersheet.dac.dao.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.RaceRowMapper;
import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.RowMapper;
import com.android.ash.charactersheet.gui.util.Logger;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.dac.dao.RaceDao;

/**
 * Provides access to the tables storing the races in a SQLite database.
 */
public class SQLiteRaceDao extends BaseSQLiteDao implements RaceDao {

    private static final String SQL_GET_ID = SELECT + "id FROM " + TABLE_RACE + " WHERE rowid = ?";

    private static final String SQL_WHERE_RACE_ID = COLUMN_RACE_ID + " = ?";

    /**
     * Create SQLiteRaceDao accessing the given database.
     * 
     * @param db
     *            The database to access.
     */
    public SQLiteRaceDao(final SQLiteDatabase db) {
        super(db);
    }

    @Override
    public List<Race> getAllRaces(final List<CharacterClass> allCharacterClasses, final List<Ability> allAbilities) {
        final List<Race> allRaces = selectAllRaces(allCharacterClasses);
        selectAbilitiesOfRaces(allRaces, allAbilities);
        return allRaces;
    }

    private List<Race> selectAllRaces(final List<CharacterClass> allCharacterClasses) {
        final List<Race> allRaces = new ArrayList<Race>();
        Cursor cursor = null;
        final RowMapper raceRowMapper = new RaceRowMapper(allCharacterClasses);
        try {
            cursor = db.rawQuery(SQL_GET_ALL_RACES, new String[0]);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final Race race = (Race) raceRowMapper.mapRow(cursor);
                allRaces.add(race);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get all races", sqlException);
        } finally {
            close(cursor);
        }
        return allRaces;
    }

    private void selectAbilitiesOfRaces(final List<Race> allRaces, final List<Ability> allAbilities) {
        Cursor cursor = null;
        for (final Race race : allRaces) {
            try {
                final String[] params = new String[] { Integer.toString(race.getId()) };
                cursor = db.rawQuery(SQL_GET_ABILITY_IDS_OF_RACE, params);
                final List<Ability> abilities = getAbilities(cursor, allAbilities);
                race.setAbilities(abilities);
            } catch (final SQLException sqlException) {
                Logger.error("Can't get ability ids of race", sqlException);
            } finally {
                close(cursor);
            }
        }
    }

    private List<Ability> getAbilities(final Cursor cursor, final List<Ability> allAbilities) {
        final List<Ability> abilities = new ArrayList<Ability>();
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            final Ability ability = getAbility(cursor.getInt(0), allAbilities);
            abilities.add(ability);
        }
        return abilities;
    }

    private Ability getAbility(final int abilityId, final List<Ability> allAbilities) {
        for (final Ability ability : allAbilities) {
            if (ability.getId() == abilityId) {
                return ability;
            }
        }
        throw new IllegalArgumentException("Can't determine ability of " + abilityId);
    }

    @Override
    public Race createRace(final Race race) {
        insertRaceTable(race);
        insertRaceAbilityTable(race);
        return race;
    }

    private void insertRaceTable(final Race race) {
        final ContentValues contentValues = getRaceContentValues(race);
        contentValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_RACE, null, contentValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert race in race table");
            }
            race.setId(getId(SQL_GET_ID, rowId));
        }
    }

    private ContentValues getRaceContentValues(final Race race) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, race.getName());
        contentValues.put(COLUMN_SIZE_ID, race.getSize().ordinal());
        contentValues.put(COLUMN_BASE_LAND_SPEED, race.getBaseLandSpeed());
        contentValues.put(COLUMN_FAV_CLASS_ID, race.getFavoredCharacterClass().getId());
        return contentValues;
    }

    private void insertRaceAbilityTable(final Race race) {
        synchronized (DBHelper.DB_LOCK) {
            for (final Ability ability : race.getAbilities()) {
                final ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_RACE_ID, race.getId());
                contentValues.put(COLUMN_ABILITY_ID, ability.getId());
                final long rowId = db.insertOrThrow(TABLE_RACE_ABILITY, null, contentValues);
                if (rowId == -1) {
                    throw new SQLException("Can't insert ability in race ability table");
                }
            }
        }
    }

    @Override
    public void deleteRace(final Race race) {
        final String[] raceId = new String[] { Integer.toString(race.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_RACE_ABILITY, COLUMN_RACE_ID + " = ?", raceId);
            db.delete(TABLE_RACE, COLUMN_ID + " = ?", raceId);
        }
    }

    @Override
    public void updateRace(final Race race) {
        updateRaceTable(race);
        updateRaceAbilityTable(race);
    }

    private void updateRaceTable(final Race race) {
        final ContentValues raceValues = getRaceContentValues(race);
        final String[] bindVariables = new String[] { Integer.toString(race.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.update(TABLE_RACE, raceValues, SQL_WHERE_ID, bindVariables);
            if (numberOfAffectedRows != 1) {
                throw new SQLException("Can't update race: " + race);
            }
        }
    }

    private void updateRaceAbilityTable(final Race race) {
        final String[] raceId = new String[] { Integer.toString(race.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_RACE_ABILITY, SQL_WHERE_RACE_ID, raceId);
        }
        insertRaceAbilityTable(race);
    }

}
