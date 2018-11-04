package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.Alignment;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.Money;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.model.Sex;
import com.d20charactersheet.framework.boc.model.XpTable;

/**
 * Maps a database row to a Character instance.
 */
public class CharacterRowMapper extends BaseRowMapper {

    private final List<Race> allRaces;
    private final List<XpTable> allXpTables;

    /**
     * Maps a datarow to an Character instance.
     * 
     * @param allRaces
     *            All races of the game system.
     * @param allXpTables
     *            All xp tables of the game system.
     */
    public CharacterRowMapper(final List<Race> allRaces, final List<XpTable> allXpTables) {
        super();
        this.allRaces = allRaces;
        this.allXpTables = allXpTables;
    }

    /**
     * Maps a database row to a Character instance.
     * 
     * @see com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.BaseRowMapper#mapRow(android.database.Cursor)
     */
    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        final Character character = new Character();
        character.setId(cursor.getInt(0));
        character.setPlayer(cursor.getString(1));
        character.setName(cursor.getString(2));
        character.setRace(getRace(cursor.getInt(3), allRaces));
        character.setSex((Sex) getEnum(cursor.getInt(4), Sex.values()));
        character.setAlignment((Alignment) getEnum(cursor.getInt(5), Alignment.values()));
        character.setXpTable(getXpTable(cursor.getInt(6), allXpTables));
        character.setExperiencePoints(cursor.getInt(7));
        character.setStrength(cursor.getInt(8));
        character.setDexterity(cursor.getInt(9));
        character.setConstitution(cursor.getInt(10));
        character.setIntelligence(cursor.getInt(11));
        character.setWisdom(cursor.getInt(12));
        character.setCharisma(cursor.getInt(13));
        character.setHitPoints(cursor.getInt(14));
        character.setMaxHitPoints(cursor.getInt(15));
        character.setArmorClass(cursor.getInt(16));
        character.setInitiativeModifier(cursor.getInt(17));
        character.setCmbModifier(cursor.getInt(18));
        character.setCmdModifier(cursor.getInt(19));
        character.setFortitudeModifier(cursor.getInt(20));
        character.setReflexModifier(cursor.getInt(21));
        character.setWillModifier(cursor.getInt(22));
        character.setImageId(cursor.getInt(23));
        character.setThumbImageId(cursor.getInt(24));
        character.setMoney(getMoney(cursor));
        return character;
    }

    private Money getMoney(final Cursor cursor) {
        final Money money = new Money();
        money.setPlatinum(cursor.getInt(25));
        money.setGold(cursor.getInt(26));
        money.setSilver(cursor.getInt(27));
        money.setCopper(cursor.getInt(28));
        return money;
    }

}
