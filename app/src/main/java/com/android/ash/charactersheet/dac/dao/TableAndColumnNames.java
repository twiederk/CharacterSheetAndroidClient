package com.android.ash.charactersheet.dac.dao;

/**
 * Constants of table and column names in the SQLite3 database.
 */
public interface TableAndColumnNames {

    /* ==== */
    /* SQL */
    /* ==== */
    /** Key word SELECT */
    static final String SELECT = "SELECT ";
    /** Key word FROM */
    static final String FROM = " FROM ";
    /** Key word WHERE */
    static final String WHERE = " WHERE ";

    /* ====== */
    /* TABLES */
    /* ====== */
    /** Table of images */
    static final String TABLE_IMAGE = "image";
    /** Table of skills */
    static final String TABLE_SKILL = "skill";
    /** Table of feats */
    static final String TABLE_FEAT = "feat";
    /** Table of charcter classes */
    static final String TABLE_CLASS = "class";
    /** Table of class skills of classes */
    static final String TABLE_CLASS_SKILL = "class_skill";
    /** Table of weapons */
    static final String TABLE_WEAPON = "weapon";
    /** Table of weapon families */
    static final String TABLE_WEAPON_FAMILY = "weapon_family";
    /** Table of armor */
    static final String TABLE_ARMOR = "armor";
    /** Table of goods */
    static final String TABLE_GOOD = "good";
    /** Table of abilities */
    static final String TABLE_ABILITY = "ability";
    /** Table of races */
    static final String TABLE_RACE = "race";
    /** Table maps abilities to races */
    static final String TABLE_RACE_ABILITY = "race_ability";
    /** Table of class abilities */
    static final String TABLE_CLASS_ABILITY = "class_ability";
    /** Table of xp tables */
    static final String TABLE_XP_TABLE = "xp_table";
    /** Table of xp levels */
    static final String TABLE_XP_LEVEL = "xp_level";
    /** Table of spells */
    static final String TABLE_SPELL = "spell";
    /** Table of spell lists */
    static final String TABLE_SPELLLIST = "spelllist";
    /** Table maps spells to spell lists */
    static final String TABLE_SPELLLIST_SPELL = "spelllist_spell";
    /** Table of ability properties */
    static final String TABLE_ABILITY_PROPERTY = "ability_property";

    /** Table of known spells */
    static final String TABLE_KNOWN_SPELLS_TABLE = "known_spells_table";
    /** Table of levels of known spells */
    static final String TABLE_KNOWN_SPELLS_LEVEL = "known_spells_level";

    /** Table of spells per day */
    static final String TABLE_SPELLS_PER_DAY_TABLE = "spells_per_day_table";
    /** Table of levels of spells per day */
    static final String TABLE_SPELLS_PER_DAY_LEVEL = "spells_per_day_level";

    /** Table of characters */
    static final String TABLE_CHARAKTER = "charakter";
    /** Table of character classes with level */
    static final String TABLE_CHARAKTER_CLASS_LEVEL = "charakter_class_level";
    /** Table of character abilities */
    static final String TABLE_CHARAKTER_ABILITY = "charakter_ability";
    /** Table of character dependent skill data */
    static final String TABLE_CHARAKTER_SKILL = "charakter_skill";
    /** Table of feats of character */
    static final String TABLE_CHARAKTER_FEAT = "charakter_feat";
    /** Table of notes */
    static final String TABLE_CHARAKTER_NOTE = "charakter_note";
    /** Table stores weapons carried by characters */
    static final String TABLE_CHARAKTER_WEAPON = "charakter_weapon";
    /** Table stores armor carried by characters */
    static final String TABLE_CHARAKTER_ARMOR = "charakter_armor";
    /** Table stores goods carried by characters */
    static final String TABLE_CHARAKTER_GOOD = "charakter_good";
    /** Table of character spells */
    static final String TABLE_CHARAKTER_KNOWN_SPELL = "charakter_known_spell";
    /** Table of weapon attacks */
    static final String TABLE_CHARAKTER_WEAPON_ATTACK = "charakter_weapon_attack";

    /** Table of spell slots of character */
    static final String TABLE_CHARAKTER_SPELL_SLOT = "charakter_spell_slot";
    /** Table of spell slot abilities */
    static final String TABLE_CHARAKTER_SPELL_SLOT_ABILITY = "charakter_spell_slot_ability";
    /** Table of spell slot meatamagic feats */
    static final String TABLE_CHARAKTER_SPELL_SLOT_FEAT = "charakter_spell_slot_feat";

    /* ======= */
    /* COLUMNS */
    /* ======= */
    /** Id column */
    static final String COLUMN_ID = "id";

    // columns of image table
    /** Image column (blob) */
    static final String COLUMN_IMAGE = "image";

    // columns of charakter table
    /** Player column */
    static final String COLUMN_PLAYER = "player";
    /** Name column */
    static final String COLUMN_NAME = "name";
    /** Race id column */
    static final String COLUMN_RACE_ID = "race_id";
    /** Sex id column */
    static final String COLUMN_SEX_ID = "sex_id";
    /** Alignment id column */
    static final String COLUMN_ALIGNMENT_ID = "alignment_id";
    /** XP Table id column */
    static final String COLUMN_XP_TABLE_ID = "xp_table_id";
    /** Experience column */
    static final String COLUMN_EXPERIENCE = "experience";

    /** Strength column */
    static final String COLUMN_STRENGTH = "strength";
    /** Dexterity column */
    static final String COLUMN_DEXTERITY = "dexterity";
    /** Constitution column */
    static final String COLUMN_CONSTITUTION = "constitution";
    /** Intelligence column */
    static final String COLUMN_INTELLIGENCE = "intelligence";
    /** Wisdom column */
    static final String COLUMN_WISDOM = "wisdom";
    /** Charisma column */
    static final String COLUMN_CHARISMA = "charisma";

    /** Hit points column */
    static final String COLUMN_HITPOINTS = "hitpoints";
    /** Max hit points column */
    static final String COLUMN_MAX_HITPOINTS = "max_hitpoints";
    /** Armor class column */
    static final String COLUMN_ARMOR = "armor";
    /** Initiative misc modifier column */
    static final String COLUMN_INI_MISC_MOD = "ini_misc_mod";
    /** Modifier of combat maneuver bonus modifier column */
    static final String COLUMN_CMB_MOD = "cmb_mod";
    /** Modifier of combat maneuver defence modifier column */
    static final String COLUMN_CMD_MOD = "cmd_mod";
    /** Fortitude saving throw misc modifier column */
    static final String COLUMN_FORTITUDE_MISC_MOD = "fort_misc_mod";
    /** Reflex saving throw misc modifier column */
    static final String COLUMN_REFLEX_MISC_MOD = "ref_misc_mod";
    /** Will saving throw misc modifier column */
    static final String COLUMN_WILL_MISC_MOD = "will_misc_mod";

    /** Image id column */
    static final String COLUMN_IMAGE_ID = "image_id";
    /** Thumb image id column */
    static final String COLUMN_THUMB_IMAGE_ID = "thumb_image_id";

    /** Number of platinum coins column */
    static final String COLUMN_PLATINUM = "platinum";
    /** Number of gold coins column */
    static final String COLUMN_GOLD = "gold";
    /** Number of silver coins column */
    static final String COLUMN_SILVER = "silver";
    /** Number of copper coins column */
    static final String COLUMN_COPPER = "copper";

    // columns of charakter_class table
    /** Character id column */
    static final String COLUMN_CHARAKTER_ID = "charakter_id";
    /** Level column */
    static final String COLUMN_LEVEL = "level";
    /** Saves column */
    static final String COLUMN_SAVES = "saves";
    /** Alignments column */
    static final String COLUMN_ALIGNMENTS = "alignments";

    // columsn of charakter_ability table
    /** owned column */
    static final String COLUMN_OWNED = "owned";

    // columns of charakter_skill table
    /** Skill id column */
    static final String COLUMN_SKILL_ID = "skill_id";
    /** Rank column */
    static final String COLUMN_RANK = "rank";
    /** Misc modifier column */
    static final String COLUMN_MISC_MODIFIER = "misc_modifier";
    /** Favorite column */
    static final String COLUMN_FAVORITE = "favorite";
    /** Description column */
    static final String COLUMN_DESCRIPTION = "description";

    // columns of skill table
    /** Attribute id colmun */
    static final String COLUMN_ATTRIBUTE_ID = "attribute_id";
    /** Untrained colmun */
    static final String COLUMN_UNTRAINED = "untrained";

    // columns of charakter_feat table
    /** Feat id column */
    static final String COLUMN_FEAT_ID = "feat_id";
    /** Category column */
    static final String COLUMN_CATEGORY = "category";

    // columns of feat table
    /** prerequisit column */
    static final String COLUMN_PREREQUISIT = "prerequisit";
    /** benefit column */
    static final String COLUMN_BENEFIT = "benefit";
    /** feat type id column */
    static final String COLUMN_FEAT_TYPE_ID = "feat_type_id";
    /** fighter column */
    static final String COLUMN_FIGHTER_BONUS = "fighter";
    /** multiple column */
    static final String COLUMN_MULTIPLE = "multiple";
    /** stack column */
    static final String COLUMN_STACK = "stack";
    /** spell slot column */
    static final String COLUMN_SPELL_SLOT = "spell_slot";

    // columns of charakter_class table
    /** base attack bonus of a character class column */
    static final String COLUMN_BASE_ATTACK_BONUS = "base_attack_bonus";
    /** number of skill points per level of a character class column */
    static final String COLUMN_SKILL_POINTS_PER_LEVEL = "skill_points_per_level";
    /** hit die of character class column */
    static final String COLUMN_HIT_DIE_ID = "hit_die_id";

    // columns of charakter_class_alignment table
    /** id of a save column */
    static final String COLUMN_SAVE_ID = "save_id";

    // columns of charakter_item (weapon, armor, good) tables
    /** number of items in item group column */
    static final String COLUMN_NUMBER = "number";
    /** id of weapon in item group column */
    static final String COLUMN_WEAPON_ID = "weapon_id";
    /** id of armor in item group column */
    static final String COLUMN_ARMOR_ID = "armor_id";
    /** id of good in item group column */
    static final String COLUMN_GOOD_ID = "good_id";

    // columns of weapon table
    /** cost column */
    static final String COLUMN_COST = "cost";
    /** weight column */
    static final String COLUMN_WEIGHT = "weight";
    /** number of dice column */
    static final String COLUMN_NUMBER_OF_DICE = "number_of_dice";
    /** id of die column */
    static final String COLUMN_DIE_ID = "die_id";
    /** bonus column */
    static final String COLUMN_ENHANCEMENT_BONUS = "enhancement_bonus";
    /** critical hit column */
    static final String COLUMN_CRITICAL_HIT = "critical_hit";
    /** critical mulitplier column */
    static final String COLUMN_CRITICAL_MOD = "critical_mod";
    /** id of weapon type column */
    static final String COLUMN_WEAPON_TYPE_ID = "weapon_type_id";
    /** id of quality type column */
    static final String COLUMN_QUALITY_TYPE_ID = "quality_type_id";
    /** id of combat type column */
    static final String COLUMN_COMBAT_TYPE_ID = "combat_type_id";
    /** id of weapon encumbrance column */
    static final String COLUMN_WEAPON_ENCUMBRANCE_ID = "weapon_encumbrance_id";
    /** id of weaopn cagegory column */
    static final String COLUMN_WEAPON_CATEGORY_ID = "weapon_category_id";
    /** id of weapon family column */
    static final String COLUMN_WEAPON_FAMILY_ID = "weapon_family_id";
    /** range increment column */
    static final String COLUMN_RANGE_INCREMENT = "range_increment";

    // columns of armor table
    /** armor class column */
    static final String COLUMN_ARMOR_BONUS = "armor_bonus";
    /** skill check penalty column */
    static final String COLUMN_ARMOR_PENALTY = "armor_penalty";
    /** id of the armor type column */
    static final String COLUMN_ARMOR_TYPE_ID = "armor_type_id";

    // columns of good table
    /** id of the good type column */
    static final String COLUMN_GOOD_TYPE_ID = "good_type_id";

    // columns of the ability table
    /** ability type id column */
    static final String COLUMN_ABILITY_TYPE_ID = "ability_type_id";
    /** classname of ability column */
    static final String COLUMN_CLASSNAME = "classname";

    // columns of the ability_property table
    /** ability id column */
    static final String COLUMN_ABILITY_ID = "ability_id";
    /** property key column */
    static final String COLUMN_PROPERTY_KEY = "property_key";
    /** property value column */
    static final String COLUMN_PROPERTY_VALUE = "property_value";

    // columns of the race table
    /** size id column */
    static final String COLUMN_SIZE_ID = "size_id";
    /** base land speed column */
    static final String COLUMN_BASE_LAND_SPEED = "base_land_speed";
    /** favorite character class id column */
    static final String COLUMN_FAV_CLASS_ID = "fav_class_id";

    // columns of note table
    /** note text column */
    static final String COLUMN_TEXT = "text";
    /** create date column */
    static final String COLUMN_DATE = "date";

    // columns of class_ability table
    /** class_id column */
    static final String COLUMN_CLASS_ID = "class_id";

    // columns of weapon_attack table
    /** id of attack wield column */
    static final String COLUMN_ATTACK_WIELD_ID = "attack_wield_id";
    /** ammunition column */
    static final String COLUMN_AMMUNITION = "ammunition";
    /** attack bonus modifier column */
    static final String COLUMN_ATTACK_BONUS_MODIFIER = "attack_bonus_modifier";
    /** damage bonus modifier column */
    static final String COLUMN_DAMAGE_BONUS_MODIFIER = "damage_bonus_modifier";

    // columns of charakter_spell table
    /** id of spell column */
    static final String COLUMN_SPELL_ID = "spell_id";

    // columns of spell table
    /** id of school column */
    static final String COLUMN_SCHOOL = "school";
    /** id of components column */
    static final String COLUMN_COMPONENTS = "components";
    /** id of casting time column */
    static final String COLUMN_CASTING_TIME = "casting_time";
    /** id of range column */
    static final String COLUMN_RANGE = "range";
    /** id of effect column */
    static final String COLUMN_EFFECT = "effect";
    /** id of duration column */
    static final String COLUMN_DURATION = "duration";
    /** id of saving throw column */
    static final String COLUMN_SAVING_THROW = "saving_throw";
    /** id of spell resistance column */
    static final String COLUMN_SPELL_RESISTANCE = "spell_resistance";
    /** id of short description column */
    static final String COLUMN_SHORT_DESCRIPTION = "short_description";
    /** id of material component column */
    static final String COLUMN_MATERIAL_COMPONENT = "mat_component";
    /** id of focus column */
    static final String COLUMN_FOCUS = "focus";

    // columns of spelllist_spell table
    /** id of spelllist column */
    static final String COLUMN_SPELLLIST_ID = "spelllist_id";

    // columns of spelllist table
    /** short name column */
    static final String COLUMN_SHORTNAME = "shortname";
    /** domain column */
    static final String COLUMN_DOMAIN = "domain";
    /** minimum level column */
    static final String COLUMN_MIN_LEVEL = "min_level";
    /** maximum level column */
    static final String COLUMN_MAX_LEVEL = "max_level";

    // columns of charakter_spell_slot table
    /** cast column */
    static final String COLUMN_CAST = "cast";

    // columns of charakter_spell_slot_feat table
    /** spell slot id column */
    static final String COLUMN_SPELL_SLOT_ID = "spell_slot_id";

    /* ============= */
    /* WHERE CLAUSES */
    /* ============= */
    /** Where clause on id column */
    static final String SQL_WHERE_ID = COLUMN_ID + " = ?";

    // CharacterDao
    /** Selects all characters from the database */
    static final String SQL_GET_ALL_CHARACTERS = SELECT
            + "id, player, name, race_id, sex_id, alignment_id, xp_table_id, experience, strength, dexterity, constitution, intelligence, wisdom, charisma, hitpoints, "
            + "max_hitpoints, armor, ini_misc_mod, cmb_mod, cmd_mod, fort_misc_mod, ref_misc_mod, will_misc_mod, image_id, thumb_image_id, platinum, gold, silver, copper" //
            + FROM + TABLE_CHARAKTER; //

    /** Selects class levels of one character from the database */
    static final String SQL_GET_CHARACTER_CLASS_LEVELS = SELECT + "id, class_id, level" //
            + FROM + TABLE_CHARAKTER_CLASS_LEVEL //
            + WHERE + "charakter_id = ?";

    /** Selects one character from the database */
    static final String SQL_GET_CHARACTER = SELECT
            + "id, player, name, race_id, sex_id, alignment_id, xp_table_id, experience, strength, dexterity, constitution, intelligence, wisdom, charisma, hitpoints, "
            + "max_hitpoints, armor, ini_misc_mod, cmb_mod, cmd_mod, fort_misc_mod, ref_misc_mod, will_misc_mod, image_id, thumb_image_id, platinum, gold, silver, copper" //
            + FROM + TABLE_CHARAKTER //
            + WHERE + "id = ?";

    /** Selects notes of one character */
    static final String SQL_GET_CHARACTER_NOTES = SELECT + "id, date, text" //
            + FROM + TABLE_CHARAKTER_NOTE //
            + WHERE + "charakter_id = ?";

    /** Select abilites of a character for one class */
    static final String SQL_GET_CHARACTER_ABILITIES = SELECT + "id, ability_id, owned" //
            + FROM + TABLE_CHARAKTER_ABILITY //
            + WHERE + "charakter_id = ? AND class_id = ?";

    // SkillDao
    /** Select all skills */
    static final String SQL_GET_ALL_SKILLS = SELECT + "id, attribute_id, untrained, name" //
            + FROM + TABLE_SKILL;

    /** Select the skills of one character */
    static final String SQL_GET_CHARACTER_SKILLS = SELECT + "skill_id, rank, misc_modifier, favorite" //
            + FROM + TABLE_CHARAKTER_SKILL //
            + WHERE + "charakter_skill.charakter_id = ? ";

    /** Selects the description of a skill */
    static final String SQL_GET_SKILL_DESCRIPTION = SELECT + "description" //
            + FROM + TABLE_SKILL //
            + WHERE + "id = ?";

    // FeatDao
    /** Select all feats */
    static final String SQL_GET_ALL_FEATS = SELECT
            + "id, name, prerequisit, benefit, feat_type_id, fighter, multiple, stack, spell_slot" //
            + FROM + TABLE_FEAT;

    // CharacterClassDao
    /** Select all character classes */
    static final String SQL_GET_ALL_CLASSES = SELECT
            + "id, name, saves, alignments, base_attack_bonus, skill_points_per_level, hit_die_id" //
            + FROM + TABLE_CLASS;

    /** Select all class abilities of a class */
    static final String SQL_GET_CLASS_ABILITIES = SELECT + "ability_id, level" //
            + FROM + TABLE_CLASS_ABILITY //
            + WHERE + "class_id = ?";

    // AbilityDao
    /** Select all abilities in a specific language */
    static final String SQL_GET_ALL_ABILITIES = SELECT + "id, name, description, ability_type_id, classname" //
            + FROM + TABLE_ABILITY;

    // RaceDao
    /** Select all races */
    static final String SQL_GET_ALL_RACES = SELECT + "id, name, size_id, base_land_speed, fav_class_id" //
            + FROM + TABLE_RACE;

    /** Select ability ids of one race */
    static final String SQL_GET_ABILITY_IDS_OF_RACE = SELECT + "ability_id" //
            + FROM + TABLE_RACE_ABILITY //
            + WHERE + "race_id = ?";

    /** Select all class skills of a character class */
    static final String SQL_GET_CLASS_SKILLS = SELECT + "skill_id" //
            + FROM + TABLE_CLASS_SKILL //
            + WHERE + "class_id = ?";

    // SpelllistDao
    /** Select all spells */
    static final String SQL_GET_ALL_SPELLS = SELECT
            + "id, name, school, components, casting_time, range, effect, duration, saving_throw, " //
            + "spell_resistance, short_description, mat_component, focus" //
            + FROM + TABLE_SPELL;

    /** Selects the descriptio of a spell */
    static final String SQL_GET_SPELL_DESCRIPTION = SELECT + "description" // ;
            + FROM + TABLE_SPELL //
            + WHERE + "id = ?";

    /** Select all spell lists */
    static final String SQL_GET_ALL_SPELLLISTS = SELECT + "id, name, shortname, domain, min_level, max_level" //
            + FROM + TABLE_SPELLLIST;

    /** Select spells of a single spell list */
    static final String SQL_GET_SPELLS_OF_SPELLLIST = SELECT + "spell_id, level" //
            + FROM + TABLE_SPELLLIST_SPELL //
            + WHERE + "spelllist_id = ?";

    /** Select ability properties of a single ability */
    static final String SQL_GET_ABILITY_PROPERTIES = SELECT + "property_key, property_value" //
            + FROM + TABLE_ABILITY_PROPERTY //
            + WHERE + "ability_id = ?";

    /** Select feats of a single character */
    static final String SQL_GET_CHARACTER_FEATS = SELECT + "id, feat_id, category" //
            + FROM + TABLE_CHARAKTER_FEAT //
            + WHERE + "charakter_id = ?";

    /** Select weapon attacks of a character */
    static final String SQL_GET_CHARACTER_WEAPON_ATTACKS = SELECT + "id, name, description, attack_wield_id, " //
            + "weapon_id, ammunition, attack_bonus_modifier, damage_bonus_modifier" //
            + FROM + TABLE_CHARAKTER_WEAPON_ATTACK //
            + WHERE + "charakter_id = ?";

    /** Select all xp tables */
    static final String SQL_GET_ALL_XP_TABLES = SELECT + "id, name" //
            + FROM + TABLE_XP_TABLE;

    /** Select experience levels a xp table. */
    static final String SQL_GET_XP_LEVELS = SELECT + "level, xp" //
            + FROM + TABLE_XP_LEVEL //
            + WHERE + "xp_table_id = ?";

    /** Select spells marked by a character */
    static final String SQL_GET_CHARACTER_KNOWN_SPELLS = SELECT + "id, spelllist_id, spell_id" //
            + FROM + TABLE_CHARAKTER_KNOWN_SPELL //
            + WHERE + "charakter_id = ?";

    /** Select all known spells tables */
    static final String SQL_GET_ALL_KNOWN_SPELLS_TABLES = SELECT + "id, name" //
            + FROM + TABLE_KNOWN_SPELLS_TABLE;

    /** Select levels of known spells table and caster level */
    static final String SQL_GET_KNOWN_SPELLS_LEVELS = SELECT
            + "level_0, level_1, level_2, level_3, level_4, level_5, level_6, level_7, level_8, level_9" //
            + FROM + TABLE_KNOWN_SPELLS_LEVEL //
            + WHERE + "known_spells_table_id = ? AND spellcaster_level = ?";

    /** Select all spells per day tables */
    static final String SQL_GET_ALL_SPELLS_PER_DAY_TABLES = SELECT + "id, name" //
            + FROM + TABLE_SPELLS_PER_DAY_TABLE;

    /** Select levels of spells per day table and caster level */
    static final String SQL_GET_SPELLS_PER_DAY_LEVELS = SELECT
            + "level_0, level_1, level_2, level_3, level_4, level_5, level_6, level_7, level_8, level_9" //
            + FROM + TABLE_SPELLS_PER_DAY_LEVEL //
            + WHERE + "spells_per_day_table_id = ? AND spellcaster_level = ?";

    /** Select spell slots of a character */
    static final String SQL_GET_CHARACTER_SPELL_SLOTS = SELECT + "id, spell_id, level, \"cast\"" //
            + FROM + TABLE_CHARAKTER_SPELL_SLOT //
            + WHERE + "charakter_id = ?";

    /** Select abilities of a spell slot */
    static final String SQL_GET_CHARACTER_SPELL_SLOT_ABILITIES = SELECT + "ability_id" //
            + FROM + TABLE_CHARAKTER_SPELL_SLOT_ABILITY //
            + WHERE + "spell_slot_id = ?";

    /** Select feats of a spell slot */
    static final String SQL_GET_CHARACTER_SPELL_SLOT_FEATS = SELECT + "feat_id" //
            + FROM + TABLE_CHARAKTER_SPELL_SLOT_FEAT //
            + WHERE + "spell_slot_id = ?";
}
