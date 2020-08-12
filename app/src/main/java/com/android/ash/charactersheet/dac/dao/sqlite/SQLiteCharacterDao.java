package com.android.ash.charactersheet.dac.dao.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.android.ash.charactersheet.boc.model.FavoriteCharacterSkill;
import com.android.ash.charactersheet.dac.dao.sqlite.rowmapper.SQLiteCharacterSkillRowMapper;
import com.android.ash.charactersheet.gui.util.Logger;
import com.androidash.memorydb.DaoUtil;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.Armor;
import com.d20charactersheet.framework.boc.model.Body;
import com.d20charactersheet.framework.boc.model.Character;
import com.d20charactersheet.framework.boc.model.CharacterAbility;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.CharacterFeat;
import com.d20charactersheet.framework.boc.model.CharacterSkill;
import com.d20charactersheet.framework.boc.model.ClassAbility;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.model.Feat;
import com.d20charactersheet.framework.boc.model.FeatType;
import com.d20charactersheet.framework.boc.model.Good;
import com.d20charactersheet.framework.boc.model.HumanoidBody;
import com.d20charactersheet.framework.boc.model.ItemGroup;
import com.d20charactersheet.framework.boc.model.KnownSpell;
import com.d20charactersheet.framework.boc.model.Money;
import com.d20charactersheet.framework.boc.model.Note;
import com.d20charactersheet.framework.boc.model.Race;
import com.d20charactersheet.framework.boc.model.Skill;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.SpellSlot;
import com.d20charactersheet.framework.boc.model.Spelllist;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.model.WeaponAttack;
import com.d20charactersheet.framework.boc.model.XpTable;
import com.d20charactersheet.framework.dac.dao.CharacterDao;
import com.d20charactersheet.framework.dac.dao.RowMapper;
import com.d20charactersheet.framework.dac.dao.sql.rowmapper.CharacterAbilityRowMapper;
import com.d20charactersheet.framework.dac.dao.sql.rowmapper.CharacterFeatRowMapper;
import com.d20charactersheet.framework.dac.dao.sql.rowmapper.CharacterRowMapper;
import com.d20charactersheet.framework.dac.dao.sql.rowmapper.ClassLevelRowMapper;
import com.d20charactersheet.framework.dac.dao.sql.rowmapper.KnownSpellRowMapper;
import com.d20charactersheet.framework.dac.dao.sql.rowmapper.NoteRowMapper;
import com.d20charactersheet.framework.dac.dao.sql.rowmapper.SpellSlotRowMapper;
import com.d20charactersheet.framework.dac.dao.sql.rowmapper.WeaponAttackRowMapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_ABILITY_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_ALIGNMENT_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_AMMUNITION;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_ARMOR;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_ATTACK_BONUS_MODIFIER;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_ATTACK_WIELD_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_CAST;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_CATEGORY;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_CHARAKTER_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_CHARISMA;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_CLASS_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_CMB_MOD;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_CMD_MOD;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_CONSTITUTION;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_COPPER;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_DAMAGE_BONUS_MODIFIER;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_DATE;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_DESCRIPTION;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_DEXTERITY;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_EXPERIENCE;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_FAVORITE;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_FEAT_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_FORTITUDE_MISC_MOD;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_GOLD;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_HITPOINTS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_IMAGE_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_INI_MISC_MOD;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_INTELLIGENCE;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_LEVEL;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_MAX_HITPOINTS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_MISC_MODIFIER;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_NAME;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_OWNED;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_PLATINUM;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_PLAYER;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_RACE_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_RANK;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_REFLEX_MISC_MOD;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_SEX_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_SILVER;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_SKILL_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_SPELLLIST_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_SPELL_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_SPELL_SLOT_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_STRENGTH;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_TEXT;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_THUMB_IMAGE_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_WEAPON_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_WILL_MISC_MOD;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_WISDOM;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.COLUMN_XP_TABLE_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.FROM;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SELECT;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_ALL_CHARACTERS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_CHARACTER;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_CHARACTER_ABILITIES;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_CHARACTER_CLASS_LEVELS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_CHARACTER_FEATS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_CHARACTER_KNOWN_SPELLS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_CHARACTER_NOTES;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_CHARACTER_SKILLS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_CHARACTER_SPELL_SLOTS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_CHARACTER_SPELL_SLOT_ABILITIES;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_CHARACTER_SPELL_SLOT_FEATS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_GET_CHARACTER_WEAPON_ATTACKS;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.SQL_WHERE_ID;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_ABILITY;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_ARMOR;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_CLASS_LEVEL;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_FEAT;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_GOOD;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_KNOWN_SPELL;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_NOTE;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_SKILL;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_SPELL_SLOT;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_SPELL_SLOT_ABILITY;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_SPELL_SLOT_FEAT;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_WEAPON;
import static com.d20charactersheet.framework.dac.dao.TableAndColumnNames.TABLE_CHARAKTER_WEAPON_ATTACK;

/**
 * Data access object to access data of a SQLite 3 database.
 */
public class SQLiteCharacterDao extends BaseSQLiteDao implements CharacterDao {

    private static final String WHERE_ROW_ID = " WHERE rowid = ?";
    private static final String SELECT_ID_FROM = SELECT + "id FROM ";

    private static final String SQL_GET_CHARACTER_ID = SELECT_ID_FROM + TABLE_CHARAKTER + WHERE_ROW_ID;
    private static final String SQL_GET_CLASS_LEVEL_ID = SELECT_ID_FROM + TABLE_CHARAKTER_CLASS_LEVEL + WHERE_ROW_ID;
    private static final String SQL_GET_NOTE_ID = SELECT_ID_FROM + TABLE_CHARAKTER_NOTE + WHERE_ROW_ID;
    private static final String SQL_GET_SPELL_SLOT_ID = SELECT_ID_FROM + TABLE_CHARAKTER_SPELL_SLOT + WHERE_ROW_ID;
    private static final String SQL_GET_CHARACTER_ABILITY_ID = SELECT_ID_FROM + TABLE_CHARAKTER_ABILITY + WHERE_ROW_ID;
    private static final String SQL_GET_WEAPON_ATTACK_ID = SELECT_ID_FROM + TABLE_CHARAKTER_WEAPON_ATTACK
            + WHERE_ROW_ID;
    private static final String SQL_GET_CHARACTER_KNOWN_SPELL_ID = SELECT_ID_FROM + TABLE_CHARAKTER_KNOWN_SPELL
            + WHERE_ROW_ID;

    private static final String WHERE_CHARACTER_ABILITY = COLUMN_CLASS_ID + " = ? AND " + COLUMN_ABILITY_ID + " = ?";
    private static final String WHERE_CHARACTER_ID = COLUMN_CHARAKTER_ID + " = ?";
    private static final String WHERE_ID = COLUMN_ID + " = ?";
    private static final String WHERE_SPELLLIST_ID_AND_SPELL_ID = COLUMN_SPELLLIST_ID + " = ? AND " + COLUMN_SPELL_ID
            + " = ?";
    private static final String WHERE_SPELL_SLOT_ID_NOT_IN_TABLE_CHARAKTER_SPELL_SLOT = COLUMN_SPELL_SLOT_ID
            + " NOT IN (" + SELECT + COLUMN_ID + FROM + TABLE_CHARAKTER_SPELL_SLOT + ")";
    private static final String WHERE_CHARAKTER_ID_AND_SKILL_ID = COLUMN_CHARAKTER_ID + " = ? AND " + COLUMN_SKILL_ID
            + " = ?";

    private final WeaponHelper weaponHelper;
    private final ArmorHelper armorHelper;
    private final GoodHelper goodHelper;
    private final DaoUtil daoUtil;

    /**
     * Opens a writeable database in the given context.
     * 
     * @param db
     *            The database to access.
     */
    public SQLiteCharacterDao(final SQLiteDatabase db) {
        super(db);

        final SQLiteItemDaoHelper helper = new SQLiteItemDaoHelper(db);

        weaponHelper = new WeaponHelper(db, helper);
        armorHelper = new ArmorHelper(db, helper);
        goodHelper = new GoodHelper(db, helper);

        daoUtil = new DaoUtil();
    }

    @Override
    public List<Character> getAllCharacters(final List<CharacterClass> allCharacterClasses, final List<Race> allRaces,
            final List<XpTable> allXpTables) {
        final List<Character> characters = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(SQL_GET_ALL_CHARACTERS, new String[0]);
            final RowMapper characterRowMapper = new CharacterRowMapper(allRaces, allXpTables);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final Character character = (Character) characterRowMapper.mapRow(new SQLiteDataRow(cursor));
                character.setClassLevels(getClassLevels(character, allCharacterClasses));
                characters.add(character);
            }
        } catch (final SQLException | java.sql.SQLException sqlException) {
            Logger.error("Can't get all characters", sqlException);
        } finally {
            close(cursor);
        }
        return characters;
    }

    private List<ClassLevel> getClassLevels(final Character character, final List<CharacterClass> allCharacterClasses) {
        final List<ClassLevel> classLevels = new LinkedList<>();
        Cursor cursor = null;
        final String[] characterId = new String[] { Integer.toString(character.getId()) };
        try {
            cursor = db.rawQuery(SQL_GET_CHARACTER_CLASS_LEVELS, characterId);
            final RowMapper classLevelRowMapper = new ClassLevelRowMapper(allCharacterClasses);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final ClassLevel classLevel = (ClassLevel) classLevelRowMapper.mapRow(new SQLiteDataRow(cursor));
                classLevel.setCharacterAbilities(getCharacterAbilities(character, classLevel.getCharacterClass()));
                classLevels.add(classLevel);
            }
        } catch (final SQLException | java.sql.SQLException sqlException) {
            Logger.error("Can't get class levels of character: " + character, sqlException);
        } finally {
            close(cursor);
        }
        return classLevels;
    }

    @Override
    public Character getCharacter(final int id, final List<CharacterClass> allCharacterClasses,
            final List<Race> allRaces, final List<XpTable> allXpTables) {
        Cursor cursor = null;
        Character character = null;
        try {
            final String[] bindVariables = new String[1];
            bindVariables[0] = Integer.toString(id);
            cursor = db.rawQuery(SQL_GET_CHARACTER, bindVariables);
            character = getCharacter(cursor, allRaces, allXpTables);
            if (character != null) {
                character.setClassLevels(getClassLevels(character, allCharacterClasses));
            }
        } catch (final SQLException | java.sql.SQLException sqlException) {
            Logger.error("Can't get character with id: " + id, sqlException);
        } finally {
            close(cursor);
        }
        return character;
    }

    private Character getCharacter(final Cursor cursor, final List<Race> allRaces, final List<XpTable> allXpTables) throws java.sql.SQLException {
        final int count = cursor.getCount();
        if (count == 0) {
            return null;
        } else if (count == 1) {
            cursor.moveToFirst();
            return (Character) new CharacterRowMapper(allRaces, allXpTables).mapRow(new SQLiteDataRow(cursor));
        }
        throw new IllegalStateException("Too many characters found");
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.CharacterDao#updateCharacter(com.d20charactersheet.framework.boc.model.Character)
     */
    public Character updateCharacter(final Character character) {
        updateCharakterTable(character);
        return character;
    }

    private void updateCharakterTable(final Character character) {
        final ContentValues values = getCharacterValues(character);
        final String[] bindVariables = new String[1];
        bindVariables[0] = Integer.toString(character.getId());
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.update(TABLE_CHARAKTER, values, SQL_WHERE_ID, bindVariables);
            if (numberOfAffectedRows != 1) {
                throw new SQLException("More or Less than 1 row affected: " + numberOfAffectedRows);
            }
        }
    }

    private ContentValues getCharacterValues(final Character character) {
        final ContentValues values = new ContentValues();

        // stats
        values.put(COLUMN_PLAYER, character.getPlayer());
        values.put(COLUMN_NAME, character.getName());
        values.put(COLUMN_RACE_ID, character.getRace().getId());
        values.put(COLUMN_SEX_ID, character.getSex().ordinal());
        values.put(COLUMN_ALIGNMENT_ID, character.getAlignment().ordinal());
        values.put(COLUMN_XP_TABLE_ID, character.getXpTable().getId());
        values.put(COLUMN_EXPERIENCE, character.getExperiencePoints());

        // abilities
        values.put(COLUMN_STRENGTH, character.getStrength());
        values.put(COLUMN_DEXTERITY, character.getDexterity());
        values.put(COLUMN_CONSTITUTION, character.getConstitution());
        values.put(COLUMN_INTELLIGENCE, character.getIntelligence());
        values.put(COLUMN_WISDOM, character.getWisdom());
        values.put(COLUMN_CHARISMA, character.getCharisma());

        // combat
        values.put(COLUMN_HITPOINTS, character.getHitPoints());
        values.put(COLUMN_MAX_HITPOINTS, character.getMaxHitPoints());
        values.put(COLUMN_ARMOR, character.getArmorClass());
        values.put(COLUMN_INI_MISC_MOD, character.getInitiativeModifier());
        values.put(COLUMN_CMB_MOD, character.getCmbModifier());
        values.put(COLUMN_CMD_MOD, character.getCmdModifier());

        // saving throws
        values.put(COLUMN_FORTITUDE_MISC_MOD, character.getFortitudeModifier());
        values.put(COLUMN_REFLEX_MISC_MOD, character.getReflexModifier());
        values.put(COLUMN_WILL_MISC_MOD, character.getWillModifier());

        // images
        values.put(COLUMN_IMAGE_ID, character.getImageId());
        values.put(COLUMN_THUMB_IMAGE_ID, character.getThumbImageId());

        // money
        final Money money = character.getMoney();
        values.put(COLUMN_PLATINUM, money.getPlatinum());
        values.put(COLUMN_GOLD, money.getGold());
        values.put(COLUMN_SILVER, money.getSilver());
        values.put(COLUMN_COPPER, money.getCopper());

        return values;
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.CharacterDao#createCharacter(com.d20charactersheet.framework.boc.model.Character)
     */
    @Override
    public Character createCharacter(final Character character) {

        final ContentValues characterValues = getCharacterValues(character);
        characterValues.putNull(COLUMN_ID);
        long rowId;
        synchronized (DBHelper.DB_LOCK) {
            rowId = db.insertOrThrow(TABLE_CHARAKTER, null, characterValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert character in charakter table");
            }
        }
        character.setId(getId(SQL_GET_CHARACTER_ID, rowId));

        final ClassLevel classLevel = character.getClassLevels().get(0);
        final ContentValues classValues = getClassLevelValues(character, classLevel);
        classValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            rowId = db.insertOrThrow(TABLE_CHARAKTER_CLASS_LEVEL, null, classValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert class level in charakter_class_level table");
            }
        }
        classLevel.setId(getId(SQL_GET_CLASS_LEVEL_ID, rowId));

        for (final CharacterAbility characterAbility : classLevel.getCharacterAbilities()) {
            createCharacterAbility(character, classLevel.getCharacterClass(), characterAbility);
        }

        return character;
    }

    private ContentValues getClassLevelValues(final Character character, final ClassLevel classLevel) {
        // id, charakter_id, charakter_class_id, level
        final ContentValues values = new ContentValues();
        values.put(COLUMN_CHARAKTER_ID, character.getId());
        values.put(COLUMN_CLASS_ID, classLevel.getCharacterClass().getId());
        values.put(COLUMN_LEVEL, classLevel.getLevel());
        return values;
    }

    /**
     * @see com.d20charactersheet.framework.dac.dao.CharacterDao#deleteCharacter(com.d20charactersheet.framework.boc.model.Character)
     */
    @Override
    public void deleteCharacter(final Character character) {

        // delete notes
        final String[] characterId = new String[] { Integer.toString(character.getId()) };

        // delete class levels (table charakter_class)
        synchronized (DBHelper.DB_LOCK) {
            int numberOfAffectedRows = db
                    .delete(TABLE_CHARAKTER_CLASS_LEVEL, COLUMN_CHARAKTER_ID + " = ?", characterId);
            if (numberOfAffectedRows == 0) {
                throw new SQLException("Can't delete class levels of character: " + character);
            }

            // delete character (table charakter)
            numberOfAffectedRows = db.delete(TABLE_CHARAKTER, COLUMN_ID + " = ?", characterId);
            if (numberOfAffectedRows == 0) {
                throw new SQLException("Can't delete character: " + character);
            }
        }

    }

    @Override
    public List<CharacterFeat> getCharacterFeats(final Character character, final List<Feat> allFeats) {
        final List<CharacterFeat> characterFeats = new ArrayList<>();
        Cursor cursor = null;
        try {
            final String[] params = new String[]{Integer.toString(character.getId())};
            cursor = db.rawQuery(SQL_GET_CHARACTER_FEATS, params);
            final RowMapper characterFeatRowMapper = new CharacterFeatRowMapper(allFeats);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final CharacterFeat characterFeat = (CharacterFeat) characterFeatRowMapper.mapRow(new SQLiteDataRow(cursor));
                characterFeats.add(characterFeat);
            }
        } catch (final SQLException | java.sql.SQLException sqlException) {
            Logger.error("Can't get character feats", sqlException);
        } finally {
            close(cursor);
        }
        return characterFeats;
    }

    @Override
    public void updateCharacterFeats(final Character character) {
        deleteCharacterFeats(character);
        insertFeats(character);
    }

    @Override
    public void deleteCharacterFeats(final Character character) {
        final String[] characterId = new String[] { Integer.toString(character.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_FEAT, COLUMN_CHARAKTER_ID + " = ?", characterId);
        }

    }

    private void insertFeats(final Character character) {
        synchronized (DBHelper.DB_LOCK) {
            for (final CharacterFeat characterFeat : character.getCharacterFeats()) {
                final ContentValues values = new ContentValues();
                values.putNull(COLUMN_ID);
                values.put(COLUMN_CHARAKTER_ID, character.getId());
                values.put(COLUMN_FEAT_ID, characterFeat.getFeat().getId());
                values.put(COLUMN_CATEGORY, characterFeat.getCategory());
                db.insertOrThrow(TABLE_CHARAKTER_FEAT, null, values);
            }
        }
    }

    @Override
    public List<CharacterSkill> getCharacterSkills(final Character character, final List<Skill> allSkills) {
        final List<CharacterSkill> characterSkills = new ArrayList<>();
        Cursor cursor = null;
        final RowMapper characterSkillRowMapper = new SQLiteCharacterSkillRowMapper(allSkills);
        try {
            final String[] params = new String[]{Integer.toString(character.getId())};
            cursor = db.rawQuery(SQL_GET_CHARACTER_SKILLS, params);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final CharacterSkill characterSkill = (CharacterSkill) characterSkillRowMapper.mapRow(new SQLiteDataRow(cursor));
                characterSkills.add(characterSkill);
            }
        } catch (final SQLException | java.sql.SQLException sqlException) {
            Logger.error("Can't get character skills", sqlException);
        } finally {
            close(cursor);
        }
        return characterSkills;
    }

    @Override
    public void deleteCharacterSkills(final Character character) {
        final String[] characterId = new String[] { Integer.toString(character.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_SKILL, COLUMN_CHARAKTER_ID + " = ?", characterId);
        }

    }

    @Override
    public List<ItemGroup> getWeapons(final Character character, final List<Weapon> allWeapons) {
        return weaponHelper.getWeapons(character, allWeapons);
    }

    @Override
    public List<ItemGroup> getArmor(final Character character, final List<Armor> allArmor) {
        return armorHelper.getArmor(character, allArmor);
    }

    @Override
    public List<ItemGroup> getGoods(final Character character, final List<Good> allGoods) {
        return goodHelper.getGoods(character, allGoods);
    }

    @Override
    public List<ItemGroup> updateWeapons(final Character character, final List<ItemGroup> weapons) {
        return weaponHelper.updateWeapons(character, weapons);
    }

    @Override
    public List<ItemGroup> updateArmor(final Character character, final List<ItemGroup> armor) {
        return armorHelper.updateArmor(character, armor);
    }

    @Override
    public List<ItemGroup> updateGoods(final Character character, final List<ItemGroup> goods) {
        return goodHelper.updateGoods(character, goods);
    }

    @Override
    public void deleteSkill(final Skill skill) {
        final String[] skillId = new String[] { Integer.toString(skill.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_SKILL, COLUMN_SKILL_ID + " = ?", skillId);
        }

    }

    @Override
    public void deleteFeat(final Feat feat) {
        final String[] featId = new String[] { Integer.toString(feat.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_FEAT, COLUMN_FEAT_ID + " = ?", featId);
            db.delete(TABLE_CHARAKTER_SPELL_SLOT_FEAT, COLUMN_FEAT_ID + " = ?", featId);
        }
    }

    @Override
    public List<Note> getNotes(final Character character) {
        final List<Note> notes = new ArrayList<>();
        Cursor cursor = null;
        try {
            final String[] params = new String[]{Integer.toString(character.getId())};
            cursor = db.rawQuery(SQL_GET_CHARACTER_NOTES, params);
            final NoteRowMapper noteRowMapper = new NoteRowMapper(DATE_FORMAT);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final Note note = (Note) noteRowMapper.mapRow(new SQLiteDataRow(cursor));
                notes.add(note);
            }
        } catch (final SQLException | java.sql.SQLException sqlException) {
            Logger.error("Can't get items of character", sqlException);
        } finally {
            close(cursor);
        }
        return notes;
    }

    @Override
    public Note createNote(final Note note, final Character character) {
        final ContentValues noteValues = getNoteValues(note, character);
        noteValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_CHARAKTER_NOTE, null, noteValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert note in note table");
            }
            note.setId(getId(SQL_GET_NOTE_ID, rowId));
        }
        return note;
    }

    @Override
    public void updateNote(final Note note, final Character character) {
        final ContentValues noteValues = getNoteValues(note, character);
        final String[] bindVariables = new String[] { Integer.toString(note.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.update(TABLE_CHARAKTER_NOTE, noteValues, SQL_WHERE_ID, bindVariables);
            if (numberOfAffectedRows != 1) {
                throw new SQLException("More or Less than 1 row affected: " + numberOfAffectedRows);
            }
        }
    }

    private ContentValues getNoteValues(final Note note, final Character character) {
        final ContentValues noteValues = new ContentValues();
        noteValues.put(COLUMN_CHARAKTER_ID, character.getId());
        noteValues.put(COLUMN_DATE, new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(note.getDate()));
        noteValues.put(COLUMN_TEXT, note.getText());
        return noteValues;
    }

    @Override
    public void deleteNote(final Note note) {
        final String[] noteId = new String[] { Integer.toString(note.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.delete(TABLE_CHARAKTER_NOTE, COLUMN_ID + " = ?", noteId);
            if (numberOfAffectedRows == 0) {
                throw new SQLException("Can't delete note");
            }
        }

    }

    @Override
    public CharacterSkill newCharacterSkill(final Skill skill) {
        return new FavoriteCharacterSkill(skill);
    }

    @Override
    public void deleteNotes(final Character character) {
        final String[] characterId = new String[] { Integer.toString(character.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_NOTE, COLUMN_CHARAKTER_ID + " = ?", characterId);
        }

    }

    @Override
    public void deleteWeapons(final Character character) {
        final String[] characterId = new String[] { Integer.toString(character.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_WEAPON, COLUMN_CHARAKTER_ID + " = ?", characterId);
        }
    }

    @Override
    public void deleteArmor(final Character character) {
        final String[] characterId = new String[] { Integer.toString(character.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_ARMOR, COLUMN_CHARAKTER_ID + " = ?", characterId);
        }
    }

    @Override
    public void deleteGoods(final Character character) {
        final String[] characterId = new String[] { Integer.toString(character.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_GOOD, COLUMN_CHARAKTER_ID + " = ?", characterId);
        }
    }

    @Override
    public List<CharacterAbility> getCharacterAbilities(final Character character, final CharacterClass characterClass) {
        final List<CharacterAbility> characterAbilities = new ArrayList<>();
        Cursor cursor = null;
        try {
            final String[] params = new String[] { Integer.toString(character.getId()),
                    Integer.toString(characterClass.getId()) };
            cursor = db.rawQuery(SQL_GET_CHARACTER_ABILITIES, params);
            final CharacterAbilityRowMapper rowMapper = new CharacterAbilityRowMapper(
                    characterClass.getClassAbilities());
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                try {
                    final CharacterAbility characterAbility = (CharacterAbility) rowMapper.mapRow(new SQLiteDataRow(cursor));
                    characterAbilities.add(characterAbility);
                } catch (final IllegalArgumentException illegalArgumentException) {
                    Logger.warn(illegalArgumentException.getMessage(), illegalArgumentException);
                }
            }
        } catch (final SQLException | java.sql.SQLException sqlException) {
            Logger.error("Can't get character abilities of character", sqlException);
        } finally {
            close(cursor);
        }
        return characterAbilities;
    }

    @Override
    public CharacterAbility createCharacterAbility(final Character character, final CharacterClass characterClass,
            final CharacterAbility characterAbility) {
        final ContentValues characterAbilityValues = getCharacterAbilityValues(character, characterClass,
                characterAbility);
        characterAbilityValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_CHARAKTER_ABILITY, null, characterAbilityValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert character ability in character_ability table");
            }
            characterAbility.setId(getId(SQL_GET_CHARACTER_ABILITY_ID, rowId));
        }
        return characterAbility;
    }

    @Override
    public void updateCharacterAbility(final CharacterAbility characterAbility) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_OWNED, daoUtil.setBoolean(characterAbility.isOwned()));
        final String[] id = new String[] { Integer.toString(characterAbility.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.update(TABLE_CHARAKTER_ABILITY, contentValues, SQL_WHERE_ID, id);
            if (numberOfAffectedRows != 1) {
                throw new SQLException("More or Less than 1 row affected: " + numberOfAffectedRows);
            }
        }
    }

    private ContentValues getCharacterAbilityValues(final Character character, final CharacterClass characterClass,
            final CharacterAbility characterAbility) {
        final ContentValues values = new ContentValues();
        values.put(COLUMN_CHARAKTER_ID, character.getId());
        values.put(COLUMN_CLASS_ID, characterClass.getId());
        values.put(COLUMN_ABILITY_ID, characterAbility.getClassAbility().getAbility().getId());
        values.put(COLUMN_OWNED, daoUtil.setBoolean(characterAbility.isOwned()));
        return values;
    }

    @Override
    public void deleteCharacterAbility(final CharacterAbility characterAbility) {
        final String[] id = new String[] { Integer.toString(characterAbility.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_ABILITY, SQL_WHERE_ID, id);
        }
    }

    @Override
    public void deleteCharacterAbilities(final Character character) {
        final String[] id = new String[] { Integer.toString(character.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_ABILITY, COLUMN_CHARAKTER_ID + " = ?", id);
        }
    }

    @Override
    public ClassLevel createClassLevel(final Character character, final ClassLevel classLevel) {
        final ContentValues classLevelValues = getClassLevelValues(classLevel, character);
        classLevelValues.putNull(COLUMN_ID);
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_CHARAKTER_CLASS_LEVEL, null, classLevelValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert class level in class level table");
            }
            classLevel.setId(getId(SQL_GET_CLASS_LEVEL_ID, rowId));
        }

        for (final CharacterAbility characterAbility : classLevel.getCharacterAbilities()) {
            createCharacterAbility(character, classLevel.getCharacterClass(), characterAbility);
        }

        return classLevel;
    }

    private ContentValues getClassLevelValues(final ClassLevel classLevel, final Character character) {
        // id, character_id, class_id, level
        final ContentValues values = new ContentValues();
        values.put(COLUMN_CHARAKTER_ID, character.getId());
        values.put(COLUMN_CLASS_ID, classLevel.getCharacterClass().getId());
        values.put(COLUMN_LEVEL, classLevel.getLevel());
        return values;
    }

    @Override
    public void updateClassLevel(final ClassLevel classLevel) {
        final ContentValues values = new ContentValues();
        values.put(COLUMN_LEVEL, classLevel.getLevel());
        final String[] bindVariables = new String[] { Integer.toString(classLevel.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db
                    .update(TABLE_CHARAKTER_CLASS_LEVEL, values, SQL_WHERE_ID, bindVariables);
            if (numberOfAffectedRows != 1) {
                throw new SQLException("More or Less than 1 row affected: " + numberOfAffectedRows);
            }
        }

        for (final CharacterAbility characterAbility : classLevel.getCharacterAbilities()) {
            updateCharacterAbility(characterAbility);
        }
    }

    @Override
    public void deleteClassLevel(final ClassLevel classLevel) {
        for (final CharacterAbility characterAbility : classLevel.getCharacterAbilities()) {
            deleteCharacterAbility(characterAbility);
        }

        final String[] classLevelId = new String[] { Integer.toString(classLevel.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.delete(TABLE_CHARAKTER_CLASS_LEVEL, COLUMN_ID + " = ?", classLevelId);
            if (numberOfAffectedRows == 0) {
                throw new SQLException("Can't delete class level");
            }
        }
    }

    @Override
    public void deleteCharacterAbilities(final CharacterClass characterClass, final List<ClassAbility> classAbilities) {
        final String classId = Integer.toString(characterClass.getId());
        synchronized (DBHelper.DB_LOCK) {
            for (final ClassAbility classAbility : classAbilities) {
                final String[] params = new String[] { classId, Integer.toString(classAbility.getAbility().getId()) };
                final int numberOfAffectedRows = db.delete(TABLE_CHARAKTER_ABILITY, WHERE_CHARACTER_ABILITY, params);
                if (numberOfAffectedRows == 0) {
                    throw new SQLException("Can't delete character ability");
                }
            }
        }
    }

    @Override
    public List<WeaponAttack> getWeaponAttacks(final Character character, final List<Weapon> allWeapons) {
        final List<WeaponAttack> weaponAttacks = new ArrayList<>();
        Cursor cursor = null;
        try {
            final String[] characterId = new String[]{Integer.toString(character.getId())};
            cursor = db.rawQuery(SQL_GET_CHARACTER_WEAPON_ATTACKS, characterId);
            final WeaponAttackRowMapper weaponAttackRowMapper = new WeaponAttackRowMapper(allWeapons);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final WeaponAttack weaponAttack = (WeaponAttack) weaponAttackRowMapper.mapRow(new SQLiteDataRow(cursor));
                weaponAttacks.add(weaponAttack);
            }
        } catch (final SQLException | java.sql.SQLException sqlException) {
            Logger.error("Can't get weapon attacks of " + character, sqlException);
        } finally {
            close(cursor);
        }
        return weaponAttacks;
    }

    @Override
    public WeaponAttack createWeaponAttack(final Character character, final WeaponAttack weaponAttack) {
        final ContentValues weaponAttackValues = getWeaponAttackValues(weaponAttack);
        weaponAttackValues.putNull(COLUMN_ID);
        weaponAttackValues.put(COLUMN_CHARAKTER_ID, character.getId());
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_CHARAKTER_WEAPON_ATTACK, null, weaponAttackValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert weapon attack in weapon_attack table");
            }
            weaponAttack.setId(getId(SQL_GET_WEAPON_ATTACK_ID, rowId));
        }
        return weaponAttack;
    }

    private ContentValues getWeaponAttackValues(final WeaponAttack weaponAttack) {
        final ContentValues weaponAttackValues = new ContentValues();
        weaponAttackValues.put(COLUMN_NAME, weaponAttack.getName());
        weaponAttackValues.put(COLUMN_DESCRIPTION, weaponAttack.getDescription());
        weaponAttackValues.put(COLUMN_ATTACK_WIELD_ID, weaponAttack.getAttackWield().ordinal());
        weaponAttackValues.put(COLUMN_WEAPON_ID, weaponAttack.getWeapon().getId());
        weaponAttackValues.put(COLUMN_AMMUNITION, weaponAttack.getAmmunition());
        weaponAttackValues.put(COLUMN_ATTACK_BONUS_MODIFIER, weaponAttack.getAttackBonusModifier());
        weaponAttackValues.put(COLUMN_DAMAGE_BONUS_MODIFIER, weaponAttack.getDamageBonusModifier());

        return weaponAttackValues;
    }

    @Override
    public void updateWeaponAttack(final WeaponAttack weaponAttack) {
        final ContentValues weaponAttackValues = getWeaponAttackValues(weaponAttack);
        final String[] bindVariables = new String[] { Integer.toString(weaponAttack.getId()) };
        final int numberOfAffectedRows = db.update(TABLE_CHARAKTER_WEAPON_ATTACK, weaponAttackValues, SQL_WHERE_ID,
                bindVariables);
        if (numberOfAffectedRows != 1) {
            throw new SQLException("More or Less than 1 row affected: " + numberOfAffectedRows);
        }
    }

    @Override
    public void deleteWeaponAttack(final WeaponAttack weaponAttack) {
        final String[] weaponAttackId = new String[] { Integer.toString(weaponAttack.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.delete(TABLE_CHARAKTER_WEAPON_ATTACK, COLUMN_ID + " = ?",
                    weaponAttackId);
            if (numberOfAffectedRows == 0) {
                throw new SQLException("Can't delete note");
            }
        }
    }

    @Override
    public void deleteWeaponAttacks(final Character character) {
        final String[] characterId = new String[] { Integer.toString(character.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_WEAPON_ATTACK, COLUMN_CHARAKTER_ID + " = ?", characterId);
        }
    }

    @Override
    public KnownSpell createKnownSpell(final Character character, final KnownSpell knownSpell) {
        synchronized (DBHelper.DB_LOCK) {
            final ContentValues knownSpellValues = getKnownSpellValues(knownSpell, character);
            knownSpellValues.putNull(COLUMN_ID);
            final long rowId = db.insertOrThrow(TABLE_CHARAKTER_KNOWN_SPELL, null, knownSpellValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert character spell in character spell table");
            }
            knownSpell.setId(getId(SQL_GET_CHARACTER_KNOWN_SPELL_ID, rowId));
        }
        return knownSpell;
    }

    private ContentValues getKnownSpellValues(final KnownSpell knownSpell, final Character character) {
        final ContentValues knownSpellValues = new ContentValues();
        knownSpellValues.put(COLUMN_CHARAKTER_ID, character.getId());
        knownSpellValues.put(COLUMN_SPELLLIST_ID, knownSpell.getSpelllist().getId());
        knownSpellValues.put(COLUMN_SPELL_ID, knownSpell.getSpell().getId());
        return knownSpellValues;
    }

    @Override
    public void deleteKnownSpell(final KnownSpell knownSpell) {
        synchronized (DBHelper.DB_LOCK) {
            final String[] knownSpellId = new String[] { Integer.toString(knownSpell.getId()) };
            final int numberOfAffectedRows = db.delete(TABLE_CHARAKTER_KNOWN_SPELL, WHERE_ID, knownSpellId);
            if (numberOfAffectedRows == 0) {
                throw new SQLException("Can't delete known spell " + knownSpell);
            }
        }

    }

    @Override
    public void deleteKnownSpells(final Character character) {
        final String[] characterId = new String[] { Integer.toString(character.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_KNOWN_SPELL, WHERE_CHARACTER_ID, characterId);
        }
    }

    @Override
    public List<KnownSpell> getKnownSpells(final Character character, final List<Spelllist> allSpelllists,
            final List<Spell> allSpells) {
        final List<KnownSpell> knownSpells = new ArrayList<>();
        Cursor cursor = null;
        try {
            final RowMapper knownSpellRowMapper = new KnownSpellRowMapper(allSpelllists, allSpells);
            final String[] characterId = new String[]{Integer.toString(character.getId())};
            cursor = db.rawQuery(SQL_GET_CHARACTER_KNOWN_SPELLS, characterId);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                try {
                    final KnownSpell knownSpell = (KnownSpell) knownSpellRowMapper.mapRow(new SQLiteDataRow(cursor));
                    knownSpells.add(knownSpell);
                } catch (final IllegalArgumentException illegalArgumentException) {
                    Logger.warn(illegalArgumentException.getLocalizedMessage(), illegalArgumentException);
                }
            }
        } catch (final SQLException | java.sql.SQLException sqlException) {
            Logger.error("Can't get all known spells", sqlException);
        } finally {
            close(cursor);
        }
        return knownSpells;
    }

    @Override
    public List<SpellSlot> getSpellSlots(final Character character, final List<Spell> allSpells,
            final List<Ability> allAbilities, final List<Feat> allFeats) {
        final List<SpelllistAbility> spelllistAbilities = getSpelllistAbilities(allAbilities);
        final List<Feat> metaMagicFeats = getMetamagicFeats(allFeats);

        final List<SpellSlot> spellSlots = new ArrayList<>();
        Cursor cursor = null;
        try {
            final RowMapper spellSlotRowMapper = new SpellSlotRowMapper(allSpells);
            final String[] characterId = new String[]{Integer.toString(character.getId())};
            cursor = db.rawQuery(SQL_GET_CHARACTER_SPELL_SLOTS, characterId);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final SpellSlot spellSlot = (SpellSlot) spellSlotRowMapper.mapRow(new SQLiteDataRow(cursor));
                spellSlot.setSpelllistAbilities(getSpelllistAbilities(spellSlot, spelllistAbilities));
                spellSlot.setMetamagicFeats(getMetaMagicFeats(spellSlot, metaMagicFeats));

                spellSlots.add(spellSlot);
            }
        } catch (final SQLException | java.sql.SQLException sqlException) {
            Logger.error("Can't get all spell slots", sqlException);
        } finally {
            close(cursor);
        }
        return spellSlots;
    }

    private List<SpelllistAbility> getSpelllistAbilities(final List<Ability> allAbilities) {
        final List<SpelllistAbility> spelllistAbilities = new LinkedList<>();
        for (final Ability ability : allAbilities) {
            if (ability instanceof SpelllistAbility) {
                final SpelllistAbility spelllistAbility = (SpelllistAbility) ability;
                spelllistAbilities.add(spelllistAbility);
            }
        }
        return spelllistAbilities;
    }

    private List<Feat> getMetamagicFeats(final List<Feat> allFeats) {
        final List<Feat> metamagicFeats = new LinkedList<>();
        for (final Feat feat : allFeats) {
            if (FeatType.METAMAGIC.equals(feat.getFeatType())) {
                metamagicFeats.add(feat);
            }
        }
        return metamagicFeats;
    }

    private List<SpelllistAbility> getSpelllistAbilities(final SpellSlot spellSlot,
            final List<SpelllistAbility> allSpelllistAbilities) {
        final List<SpelllistAbility> spelllistAbilities = new LinkedList<>();
        Cursor cursor = null;
        try {
            final String[] spellSlotId = new String[] { Integer.toString(spellSlot.getId()) };
            cursor = db.rawQuery(SQL_GET_CHARACTER_SPELL_SLOT_ABILITIES, spellSlotId);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final SpelllistAbility spelllistAbility = getSpelllistAbility(cursor.getInt(0), allSpelllistAbilities);
                spelllistAbilities.add(spelllistAbility);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get spell slot abilities", sqlException);
        } finally {
            close(cursor);
        }
        return spelllistAbilities;
    }

    private SpelllistAbility getSpelllistAbility(final int spelllistAbilityId,
            final List<SpelllistAbility> allSpelllistAbilities) {
        for (final SpelllistAbility spelllistAbility : allSpelllistAbilities) {
            if (spelllistAbility.getId() == spelllistAbilityId) {
                return spelllistAbility;
            }
        }
        throw new IllegalArgumentException("Can't find spell list ability with id: " + spelllistAbilityId);
    }

    private List<Feat> getMetaMagicFeats(final SpellSlot spellSlot, final List<Feat> allMetamagicFeats) {
        final List<Feat> metamagicFeats = new LinkedList<>();
        Cursor cursor = null;
        try {
            final String[] spellSlotId = new String[] { Integer.toString(spellSlot.getId()) };
            cursor = db.rawQuery(SQL_GET_CHARACTER_SPELL_SLOT_FEATS, spellSlotId);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                final Feat metamagicFeat = getMetamagicFeat(cursor.getInt(0), allMetamagicFeats);
                metamagicFeats.add(metamagicFeat);
            }
        } catch (final SQLException sqlException) {
            Logger.error("Can't get spell slot feats", sqlException);
        } finally {
            close(cursor);
        }
        return metamagicFeats;
    }

    private Feat getMetamagicFeat(final int metamagicFeatId, final List<Feat> allMetamagicFeats) {
        for (final Feat metamagicFeat : allMetamagicFeats) {
            if (metamagicFeat.getId() == metamagicFeatId) {
                return metamagicFeat;
            }
        }
        throw new IllegalArgumentException("Can't find spell list feat with id: " + metamagicFeatId);
    }

    @Override
    public void updateSpellSlot(final SpellSlot spellSlot) {
        final ContentValues contentValues = getSpellSlotValues(spellSlot);
        final String[] spellSlotId = new String[] { Integer.toString(spellSlot.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.update(TABLE_CHARAKTER_SPELL_SLOT, contentValues, SQL_WHERE_ID,
                    spellSlotId);
            if (numberOfAffectedRows != 1) {
                throw new SQLException("More or Less than 1 row affected: " + numberOfAffectedRows);
            }

            db.delete(TABLE_CHARAKTER_SPELL_SLOT_FEAT, COLUMN_SPELL_SLOT_ID + " = ?", spellSlotId);
            for (final Feat metamagicFeat : spellSlot.getMetamagicFeats()) {
                final ContentValues metamagicFeatContentValues = getMetamagicFeatValues(spellSlot.getId(),
                        metamagicFeat);
                db.insertOrThrow(TABLE_CHARAKTER_SPELL_SLOT_FEAT, null, metamagicFeatContentValues);
            }

        }
    }

    private ContentValues getMetamagicFeatValues(final int spellSlotId, final Feat metamagicFeat) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SPELL_SLOT_ID, spellSlotId);
        contentValues.put(COLUMN_FEAT_ID, metamagicFeat.getId());
        return contentValues;
    }

    private ContentValues getSpellSlotValues(final SpellSlot spellSlot) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SPELL_ID, spellSlot.getSpell().getId());
        contentValues.put(COLUMN_LEVEL, spellSlot.getLevel());
        contentValues.put(COLUMN_CAST, spellSlot.isCast());
        return contentValues;
    }

    @Override
    public SpellSlot createSpellSlot(final Character character, final SpellSlot spellSlot) {
        insertSpellSlot(character, spellSlot);
        insertSpellSlotAbility(spellSlot);
        insertSpellSlotFeat(spellSlot);
        return spellSlot;
    }

    private void insertSpellSlot(final Character character, final SpellSlot spellSlot) {
        final ContentValues contentValues = getSpellSlotValues(spellSlot);
        contentValues.putNull(COLUMN_ID);
        contentValues.put(COLUMN_CHARAKTER_ID, character.getId());
        synchronized (DBHelper.DB_LOCK) {
            final long rowId = db.insertOrThrow(TABLE_CHARAKTER_SPELL_SLOT, null, contentValues);
            if (rowId == -1) {
                throw new SQLException("Can't insert spell slot in charakter_spell_slot table");
            }
            spellSlot.setId(getId(SQL_GET_SPELL_SLOT_ID, rowId));
        }
    }

    private void insertSpellSlotAbility(final SpellSlot spellSlot) {
        for (final SpelllistAbility spelllistAbility : spellSlot.getSpelllistAbilities()) {
            final ContentValues contentValues = getSpelllistAbilityValues(spellSlot, spelllistAbility);
            synchronized (DBHelper.DB_LOCK) {
                final long rowId = db.insertOrThrow(TABLE_CHARAKTER_SPELL_SLOT_ABILITY, null, contentValues);
                if (rowId == -1) {
                    throw new SQLException("Can't insert spelllist ability in charakter_spell_slot_ability table");
                }
            }
        }
    }

    private ContentValues getSpelllistAbilityValues(final SpellSlot spellSlot, final SpelllistAbility spelllistAbility) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SPELL_SLOT_ID, spellSlot.getId());
        contentValues.put(COLUMN_ABILITY_ID, spelllistAbility.getId());
        return contentValues;
    }

    private void insertSpellSlotFeat(final SpellSlot spellSlot) {
        for (final Feat metamagicFeat : spellSlot.getMetamagicFeats()) {
            final ContentValues contentValues = getMetamagicFeatsValues(spellSlot, metamagicFeat);
            synchronized (DBHelper.DB_LOCK) {
                final long rowId = db.insertOrThrow(TABLE_CHARAKTER_SPELL_SLOT_FEAT, null, contentValues);
                if (rowId == -1) {
                    throw new SQLException("Can't insert feat in charakter_spell_slot_feat table");
                }
            }
        }
    }

    private ContentValues getMetamagicFeatsValues(final SpellSlot spellSlot, final Feat metamagicFeat) {
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SPELL_SLOT_ID, spellSlot.getId());
        contentValues.put(COLUMN_FEAT_ID, metamagicFeat.getId());
        return contentValues;
    }

    @Override
    public void deleteSpellSlot(final SpellSlot spellSlot) {
        final String[] spellSlotId = new String[] { Integer.toString(spellSlot.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_SPELL_SLOT_FEAT, COLUMN_SPELL_SLOT_ID + " = ?", spellSlotId);
            db.delete(TABLE_CHARAKTER_SPELL_SLOT_ABILITY, COLUMN_SPELL_SLOT_ID + " = ?", spellSlotId);
            db.delete(TABLE_CHARAKTER_SPELL_SLOT, COLUMN_ID + " = ?", spellSlotId);
        }
    }

    @Override
    public void deleteSpellSlots(final Character character) {
        for (final SpellSlot spellSlot : character.getSpellSlots()) {
            deleteSpellSlot(spellSlot);
        }
    }

    @Override
    public void deleteKnownSpells(final Spelllist spelllist, final Spell spell) {
        final String[] bindVariables = new String[] { Integer.toString(spelllist.getId()),
                Integer.toString(spell.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_KNOWN_SPELL, WHERE_SPELLLIST_ID_AND_SPELL_ID, bindVariables);
        }
    }

    @Override
    public void deleteSpellSlots(final Spelllist spelllist, final Spell spell) {
        final String[] spellId = new String[] { Integer.toString(spell.getId()) };
        synchronized (DBHelper.DB_LOCK) {
            db.delete(TABLE_CHARAKTER_SPELL_SLOT, COLUMN_SPELL_ID + " = ?", spellId);
            db.delete(TABLE_CHARAKTER_SPELL_SLOT_ABILITY, WHERE_SPELL_SLOT_ID_NOT_IN_TABLE_CHARAKTER_SPELL_SLOT,
                    new String[0]);
            db.delete(TABLE_CHARAKTER_SPELL_SLOT_FEAT, WHERE_SPELL_SLOT_ID_NOT_IN_TABLE_CHARAKTER_SPELL_SLOT,
                    new String[0]);
        }
    }

    @Override
    public void updateCharacterSkill(final Character character, final CharacterSkill characterSkill) {
        final ContentValues contentValues = getCharacterSkillContentValues(characterSkill);

        final String[] bindVariables = new String[] { Integer.toString(character.getId()),
                Integer.toString(characterSkill.getSkill().getId()) };
        synchronized (DBHelper.DB_LOCK) {
            final int numberOfAffectedRows = db.update(TABLE_CHARAKTER_SKILL, contentValues,
                    WHERE_CHARAKTER_ID_AND_SKILL_ID, bindVariables);
            if (numberOfAffectedRows == 0) {
                contentValues.put(COLUMN_SKILL_ID, characterSkill.getSkill().getId());
                contentValues.put(COLUMN_CHARAKTER_ID, character.getId());
                db.insertOrThrow(TABLE_CHARAKTER_SKILL, null, contentValues);
            }
            if (numberOfAffectedRows > 1) {
                throw new SQLException("More than 1 row affected: " + numberOfAffectedRows);
            }
        }
    }

    @Override
    public Body getBody(Character character, List<Weapon> allWeapons, List<Armor> allArmors, List<Good> allGoods) {
        return new HumanoidBody();
    }

    @Override
    public Body createBody(Character character, Body body) {
        return body;
    }

    @Override
    public void deleteBody(Character character) {

    }

    @Override
    public void updateBody(Character character) {

    }

    private ContentValues getCharacterSkillContentValues(final CharacterSkill characterSkill) {
        final FavoriteCharacterSkill favCharacterSkill = (FavoriteCharacterSkill) characterSkill;
        final ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_RANK, favCharacterSkill.getRank());
        contentValues.put(COLUMN_MISC_MODIFIER, favCharacterSkill.getModifier());
        contentValues.put(COLUMN_FAVORITE, favCharacterSkill.isFavorite());
        return contentValues;
    }
}
