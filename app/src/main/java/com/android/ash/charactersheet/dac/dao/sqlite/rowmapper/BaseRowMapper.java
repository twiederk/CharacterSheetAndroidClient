package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.androidash.memorydb.DaoUtil;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.QualityType;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.model.XpTable;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

/**
 * Base class of row mapper implemations. Contains util function to map enumerations.
 */
public abstract class BaseRowMapper implements RowMapper {

    private final DaoUtil daoUtil;

    /**
     * Instantiates BaseRowMapper with DaoUtil.
     */
    public BaseRowMapper() {
        super();
        daoUtil = new DaoUtil();
    }

    /**
     * @see com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.RowMapper#mapRow(android.database.Cursor)
     */
    @Override
    public abstract Object mapRow(Cursor cursor) throws SQLException;

    boolean getBoolean(final int booleanId) {
        if (booleanId == 0) {
            return false;
        } else if (booleanId == 1) {
            return true;
        }
        throw new IllegalArgumentException("Can't determine boolean of: " + booleanId);
    }

    /**
     * Returns the race enum of the given race id.
     * 
     * @param raceId
     *            The race id.
     * @return The race enum of the given race id.
     */
    Race getRace(final int raceId, final List<Race> allRaces) {
        for (final Race race : allRaces) {
            if (race.getId() == raceId) {
                return race;
            }
        }
        throw new IllegalArgumentException("Can't determine Race of raceId: " + raceId);
    }

    CharacterClass getCharacterClass(final int characterClassId, final List<CharacterClass> allCharacterClasses) {
        for (final CharacterClass characterClass : allCharacterClasses) {
            if (characterClass.getId() == characterClassId) {
                return characterClass;
            }
        }
        return null;
    }

    Item getItem(final int itemId, final List<? extends Item> allItems) {
        for (final Item item : allItems) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        throw new IllegalArgumentException("Can't determine item with id: " + itemId);
    }

    Date parseDate(final String dateParameter, final DateFormat dateFormat) {
        Date date;
        try {
            date = dateFormat.parse(dateParameter);
        } catch (final ParseException parseException) {
            parseException.printStackTrace();
            throw new RuntimeException(parseException);
        }
        return date;
    }

    QualityType getQualityType(final int qualityTypeId) {
        for (final QualityType qualityType : QualityType.values()) {
            if (qualityType.ordinal() == qualityTypeId) {
                return qualityType;
            }
        }
        throw new IllegalArgumentException("Can't determine QualityType of qualityTypeId: " + qualityTypeId);
    }

    <U extends Enum<U>> EnumSet<U> getEnums(final int ids, final Class<U> enumClass) {
        final EnumSet<U> enumSet = EnumSet.noneOf(enumClass);
        for (final U currentEnum : Objects.requireNonNull(enumClass.getEnumConstants())) {
            final int mask = 1 << currentEnum.ordinal();
            if ((ids & mask) > 0) {
                enumSet.add(currentEnum);
            }
        }
        return enumSet;
    }

    /**
     * Returns the enum with the proper id.
     * 
     * @param id
     *            The id of the enum to get.
     * @param values
     *            The enum values to get the enum from.
     * @return The enum with the proper id.
     */
    public Enum<?> getEnum(final int id, final Enum<?>[] values) {
        return daoUtil.getEnum(id, values);
    }

    XpTable getXpTable(final int xpTableId, final List<XpTable> allXpTables) {
        for (final XpTable xpTable : allXpTables) {
            if (xpTable.getId() == xpTableId) {
                return xpTable;
            }
        }
        throw new IllegalArgumentException("Can't find xp table with id: " + xpTableId);
    }

}
