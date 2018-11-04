package com.android.ash.charactersheet.dac.dao.sqlite.rowmapper;

import android.database.Cursor;
import android.database.SQLException;

import com.d20charactersheet.framework.boc.model.Armor;
import com.d20charactersheet.framework.boc.model.ArmorType;

/**
 * Maps an armor from the persistent layer to an Armor object.
 */
public class ArmorRowMapper extends BaseRowMapper {

    @Override
    public Object mapRow(final Cursor cursor) throws SQLException {
        // armor.id, armor_text.name, cost, weight, armor_bonus, armor_penalty, armor_type_id
        final Armor armor = new Armor();
        armor.setId(cursor.getInt(0));
        armor.setName(cursor.getString(1));
        armor.setDescription(cursor.getString(2));
        armor.setCost(cursor.getFloat(3));
        armor.setWeight(cursor.getFloat(4));
        armor.setArmorBonus(cursor.getInt(5));
        armor.setArmorCheckPenalty(cursor.getInt(6));
        armor.setArmorType((ArmorType) getEnum(cursor.getInt(7), ArmorType.values()));
        armor.setQualityType(getQualityType(cursor.getInt(8)));
        return armor;
    }

}
