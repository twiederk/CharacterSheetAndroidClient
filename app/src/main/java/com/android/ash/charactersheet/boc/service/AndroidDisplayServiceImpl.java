package com.android.ash.charactersheet.boc.service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.content.res.Resources;

import com.android.ash.charactersheet.R;
import com.d20charactersheet.framework.boc.model.Ability;
import com.d20charactersheet.framework.boc.model.AbilityType;
import com.d20charactersheet.framework.boc.model.Alignment;
import com.d20charactersheet.framework.boc.model.ArmorType;
import com.d20charactersheet.framework.boc.model.AttackWield;
import com.d20charactersheet.framework.boc.model.Attribute;
import com.d20charactersheet.framework.boc.model.BaseAttackBonus;
import com.d20charactersheet.framework.boc.model.CastingTime;
import com.d20charactersheet.framework.boc.model.CastingType;
import com.d20charactersheet.framework.boc.model.CombatType;
import com.d20charactersheet.framework.boc.model.Critical;
import com.d20charactersheet.framework.boc.model.Descriptor;
import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.model.ExtraFeatsAbility;
import com.d20charactersheet.framework.boc.model.ExtraSkillPointsAbility;
import com.d20charactersheet.framework.boc.model.FeatType;
import com.d20charactersheet.framework.boc.model.GoodType;
import com.d20charactersheet.framework.boc.model.Item;
import com.d20charactersheet.framework.boc.model.QualityType;
import com.d20charactersheet.framework.boc.model.Range;
import com.d20charactersheet.framework.boc.model.RuleError;
import com.d20charactersheet.framework.boc.model.Save;
import com.d20charactersheet.framework.boc.model.School;
import com.d20charactersheet.framework.boc.model.Sex;
import com.d20charactersheet.framework.boc.model.Size;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.SpellResistance;
import com.d20charactersheet.framework.boc.model.SpellSource;
import com.d20charactersheet.framework.boc.model.SpelllistAbility;
import com.d20charactersheet.framework.boc.model.SubSchool;
import com.d20charactersheet.framework.boc.model.WeaponCategory;
import com.d20charactersheet.framework.boc.model.WeaponEncumbrance;
import com.d20charactersheet.framework.boc.model.WeaponType;
import com.d20charactersheet.framework.boc.service.AbstractDisplayService;

/**
 * Converts data into a displayable string.
 */
public class AndroidDisplayServiceImpl extends AbstractDisplayService {

    private final Resources resources;

    /**
     * Creates a DisplayManager instance with the given context.
     * 
     * @param resources
     *            The resources containing the display information.
     */
    public AndroidDisplayServiceImpl(final Resources resources) {
        super();
        this.resources = resources;
    }

    /**
     * @see com.d20charactersheet.framework.boc.service.DisplayService#getDisplayAlignment(com.d20charactersheet.framework.boc.model.Alignment)
     */
    @Override
    public String getDisplayAlignment(final Alignment alignment) {
        switch (alignment) {
        case LAWFUL_GOOD:
            return resources.getString(R.string.align_lawful_good);
        case NEUTRAL_GOOD:
            return resources.getString(R.string.align_neutral_good);
        case CHAOTIC_GOOD:
            return resources.getString(R.string.align_chaotic_good);
        case LAWFUL_NEUTRAL:
            return resources.getString(R.string.align_lawful_neutral);
        case NEUTRAL:
            return resources.getString(R.string.align_neutral);
        case CHAOTIC_NEUTRAL:
            return resources.getString(R.string.align_chaotic_neutal);
        case LAWFUL_EVIL:
            return resources.getString(R.string.align_lawful_evil);
        case NEUTRAL_EVIL:
            return resources.getString(R.string.align_neutral_evil);
        case CHAOTIC_EVIL:
            return resources.getString(R.string.align_chaotic_evil);
        default:
            throw new IllegalArgumentException("Cant determine alignment of: " + alignment);
        }
    }

    /**
     * @see com.d20charactersheet.framework.boc.service.DisplayService#getDisplaySex(com.d20charactersheet.framework.boc.model.Sex)
     */
    @Override
    public String getDisplaySex(final Sex sex) {
        switch (sex) {
        case MALE:
            return resources.getString(R.string.sex_male);
        case FEMALE:
            return resources.getString(R.string.sex_female);
        default:
            throw new IllegalArgumentException("Can't determine sex of: " + sex);
        }
    }

    /**
     * @see com.d20charactersheet.framework.boc.service.DisplayService#getErrorMessage(com.d20charactersheet.framework.boc.model.RuleError)
     */
    @Override
    public String getErrorMessage(final RuleError ruleError) {
        switch (ruleError) {
        case ATTRIBUTE_VALUE_NOT_IN_RANGE:
            return resources.getString(R.string.attribute_value_not_in_range);
        case EXPERIENCE_NOT_IN_RANGE:
            return resources.getString(R.string.experience_not_in_range);
        case NEGATIVE_EXPERIENCE_POINTS:
            return resources.getString(R.string.negative_experience_points);
        case ZERO_OR_NEGATIVE_CLASS_LEVEL:
            return resources.getString(R.string.zero_or_negative_class_level);
        case NONE_CLASS_LEVEL:
            return resources.getString(R.string.none_class);
        case NEGATIVE_SKILL_RANK:
            return resources.getString(R.string.negative_skill_rank);
        case MAX_SKILL_RANK_EXCEEDED:
            return resources.getString(R.string.max_skill_rank_exceeded);
        case INVALID_CRITICAL_HIT_VALUE:
            return resources.getString(R.string.invalid_critical_hit_value);
        default:
            throw new IllegalArgumentException("Can't determine message of rule error: " + ruleError);
        }
    }

    /**
     * @see com.d20charactersheet.framework.boc.service.DisplayService#getDisplayAttribute(com.d20charactersheet.framework.boc.model.Attribute)
     */
    @Override
    public String getDisplayAttribute(final Attribute attribute) {
        switch (attribute) {
        case STRENGTH:
            return resources.getString(R.string.attribute_strength);
        case DEXTERITY:
            return resources.getString(R.string.attribute_dexterity);
        case CONSTITUTION:
            return resources.getString(R.string.attribute_constitution);
        case INTELLIGENCE:
            return resources.getString(R.string.attribute_intelligence);
        case WISDOM:
            return resources.getString(R.string.attribute_wisdom);
        case CHARISMA:
            return resources.getString(R.string.attribute_charisma);
        default:
            throw new IllegalArgumentException("Can't determine attribute of: " + attribute);
        }
    }

    @Override
    public String getDisplayAttributeShort(final Attribute attribute) {
        switch (attribute) {
        case STRENGTH:
            return resources.getString(R.string.attribute_str);
        case DEXTERITY:
            return resources.getString(R.string.attribute_dex);
        case CONSTITUTION:
            return resources.getString(R.string.attribute_con);
        case INTELLIGENCE:
            return resources.getString(R.string.attribute_int);
        case WISDOM:
            return resources.getString(R.string.attribute_wis);
        case CHARISMA:
            return resources.getString(R.string.attribute_cha);
        default:
            throw new IllegalArgumentException("Can't determine attribute short of: " + attribute);
        }
    }

    /**
     * @see com.d20charactersheet.framework.boc.service.DisplayService#getDisplayFeatType(com.d20charactersheet.framework.boc.model.FeatType)
     */
    @Override
    public String getDisplayFeatType(final FeatType featType) {
        switch (featType) {
        case GENERAL:
            return resources.getString(R.string.feat_type_general);
        case ITEM_CREATION:
            return resources.getString(R.string.feat_type_item_creation);
        case METAMAGIC:
            return resources.getString(R.string.feat_type_metamagic);
        case TEAMWORK:
            return resources.getString(R.string.feat_type_teamwork);
        default:
            throw new IllegalArgumentException("Can't determine feattype of: " + featType);
        }
    }

    /**
     * @see com.d20charactersheet.framework.boc.service.DisplayService#getDisplayDie(com.d20charactersheet.framework.boc.model.Die)
     */
    @Override
    public String getDisplayDie(final Die die) {
        switch (die) {
        case D2:
            return resources.getString(R.string.die_d2);
        case D3:
            return resources.getString(R.string.die_d3);
        case D4:
            return resources.getString(R.string.die_d4);
        case D6:
            return resources.getString(R.string.die_d6);
        case D8:
            return resources.getString(R.string.die_d8);
        case D10:
            return resources.getString(R.string.die_d10);
        case D12:
            return resources.getString(R.string.die_d12);
        case D20:
            return resources.getString(R.string.die_d20);
        case D100:
            return resources.getString(R.string.die_d100);
        default:
            throw new IllegalArgumentException("Can't determine die of: " + die);
        }
    }

    /**
     * @see com.d20charactersheet.framework.boc.service.DisplayService#getDisplayBaseAttackBonus(com.d20charactersheet.framework.boc.model.BaseAttackBonus)
     */
    @Override
    public String getDisplayBaseAttackBonus(final BaseAttackBonus baseAttackBonus) {
        switch (baseAttackBonus) {
        case POOR:
            return resources.getString(R.string.baseattackbonus_poor);
        case AVERAGE:
            return resources.getString(R.string.baseattackbonus_average);
        case GOOD:
            return resources.getString(R.string.baseattackbonus_good);
        default:
            throw new IllegalArgumentException("Can't determine die of: " + baseAttackBonus);
        }
    }

    @Override
    public String getDisplayWeight(final float weight) {
        final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        final StringBuilder displayWeight = new StringBuilder();
        displayWeight.append(decimalFormat.format(weight));
        displayWeight.append(" ");
        displayWeight.append(resources.getString(R.string.unit_weight_abbreviation));
        return displayWeight.toString();
    }

    @Override
    public String getDisplayCost(final float cost) {
        final DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        final StringBuilder displayCost = new StringBuilder();
        displayCost.append(decimalFormat.format(cost));
        displayCost.append(" ");
        displayCost.append(resources.getString(R.string.currency_gold_abbreviation));
        return displayCost.toString();
    }

    @Override
    public String getDisplayWeaponType(final WeaponType weaponFamily) {
        switch (weaponFamily) {
        case SIMPLE:
            return resources.getString(R.string.weapon_type_simple);
        case MARTIAL:
            return resources.getString(R.string.weapon_type_martial);
        case EXOTIC:
            return resources.getString(R.string.weapon_type_exotic);
        case AMMO:
            return resources.getString(R.string.weapon_type_ammo);
        default:
            throw new IllegalArgumentException("Can't determine weapon type of: " + weaponFamily);
        }
    }

    @Override
    public String getDisplayArmorType(final ArmorType armorType) {
        switch (armorType) {
        case LIGHT:
            return resources.getString(R.string.armor_type_light);
        case MEDIUM:
            return resources.getString(R.string.armor_type_medium);
        case HEAVY:
            return resources.getString(R.string.armor_type_heavy);
        case SHIELD:
            return resources.getString(R.string.armor_type_shield);
        default:
            throw new IllegalArgumentException("Can't determine armor type of: " + armorType);
        }
    }

    @Override
    public String getDisplayGoodType(final GoodType goodType) {
        switch (goodType) {
        case ADVENTURING_GEAR:
            return resources.getString(R.string.good_type_adventuring_gear);
        case SPECIAL_SUBSTANCE:
            return resources.getString(R.string.good_type_special_substance);
        case SPECIAL_ITEM:
            return resources.getString(R.string.good_type_special_item);
        case TOOL_KIT:
            return resources.getString(R.string.good_type_tool_kit);
        case SKILL_KIT:
            return resources.getString(R.string.good_type_skill_kit);
        case CLOTHING:
            return resources.getString(R.string.good_type_clothing);
        case MOUNT:
            return resources.getString(R.string.good_type_mount);
        case MOUNT_GEAR:
            return resources.getString(R.string.good_type_mount_gear);
        case ENTERTAINMENT:
            return resources.getString(R.string.good_type_entertainment);
        default:
            throw new IllegalArgumentException("Can't determine good type of: " + goodType);
        }
    }

    @Override
    public String getDisplayCritical(final Critical critical) {
        if (critical.getHit() == 0) {
            return "";
        }
        return critical.toString();
    }

    @Override
    public String getDisplaySize(final Size size) {
        switch (size) {
        case FINE:
            return resources.getString(R.string.size_fine);
        case DIMINUTIVE:
            return resources.getString(R.string.size_diminutive);
        case TINY:
            return resources.getString(R.string.size_tiny);
        case SMALL:
            return resources.getString(R.string.size_small);
        case MEDIUM:
            return resources.getString(R.string.size_medium);
        case LARGE_TALL:
            return resources.getString(R.string.size_large_tall);
        case LARGE_LONG:
            return resources.getString(R.string.size_large_long);
        case HUGE_TALL:
            return resources.getString(R.string.size_huge_tall);
        case HUGE_LONG:
            return resources.getString(R.string.size_huge_long);
        case GARGANTUAN_TALL:
            return resources.getString(R.string.size_gargantuan_tall);
        case GARGANTUAN_LONG:
            return resources.getString(R.string.size_gargantuan_long);
        case COLOSSAL_TALL:
            return resources.getString(R.string.size_colossal_tall);
        case COLOSSAL_LONG:
            return resources.getString(R.string.size_colossal_long);
        default:
            throw new IllegalArgumentException("Can't determine size: " + size);
        }
    }

    @Override
    public String getDisplayAbilityType(final AbilityType abilityType) {
        switch (abilityType) {
        case NATURAL:
            return resources.getString(R.string.ability_type_natural);
        case EXTRAORDINARY:
            return resources.getString(R.string.ability_type_extraordinary);
        case SPELL_LIKE:
            return resources.getString(R.string.ability_type_spell_like);
        case SUPERNATURAL:
            return resources.getString(R.string.ability_type_supernatural);
        default:
            throw new IllegalArgumentException("Can't determine ability type: " + abilityType);
        }
    }

    @Override
    public String getDisplayItem(final Item item) {
        if (QualityType.MASTERWORK.equals(item.getQualityType())) {
            return item.getName() + resources.getString(R.string.masterwork);
        }
        return item.getName();
    }

    @Override
    public String getDisplayQualityType(final QualityType qualityType) {
        switch (qualityType) {
        case NORMAL:
            return resources.getString(R.string.quality_type_normal);
        case MASTERWORK:
            return resources.getString(R.string.quality_type_masterwork);
        case MAGIC:
            return resources.getString(R.string.quality_type_magic);
        default:
            throw new IllegalArgumentException("Can't determine quality type: " + qualityType);
        }
    }

    @Override
    public String getDisplaySpellLevel(final int level) {
        switch (level) {
        case 0:
            return "0-LEVEL SPELLS";
        case 1:
            return "1st-LEVEL SPELLS";
        case 2:
            return "2nd-LEVEL SPELLS";
        case 3:
            return "3rd-LEVEL SPELLS";
        default:
            return level + "th LEVEL SPELLS";
        }
    }

    @Override
    public String getDisplaySpellComponents(final Spell spell) {
        final List<String> letters = getSpellComponentLetters(spell);
        final StringBuilder components = new StringBuilder();
        for (final Iterator<String> iterator = letters.iterator(); iterator.hasNext();) {
            final String letter = iterator.next();
            components.append(letter);
            if (iterator.hasNext()) {
                components.append(", ");
            }
        }
        return components.toString();
    }

    private List<String> getSpellComponentLetters(final Spell spell) {
        final List<String> letters = new ArrayList<String>(5);
        if (spell.isVerbal()) {
            letters.add(resources.getString(R.string.spell_component_verbal));
        }
        if (spell.isSomatic()) {
            letters.add(resources.getString(R.string.spell_component_somatic));
        }
        if (spell.isMaterial()) {
            letters.add(resources.getString(R.string.spell_component_material));
        }
        if (spell.isFocus()) {
            letters.add(resources.getString(R.string.spell_component_focus));
        }
        if (spell.isDivineFocus()) {
            letters.add(resources.getString(R.string.spell_component_divine_focus));
        }
        if (spell.isXpCost()) {
            letters.add(resources.getString(R.string.spell_component_xp_cost));
        }
        return letters;
    }

    @Override
    public String getDisplayUnitDistanceShort() {
        return resources.getString(R.string.unit_distance_short);
    }

    @Override
    public String getDisplayAttackWield(final AttackWield attackWield) {
        // OFF_HAND, PRIMARY_HAND, PRIMARY_HAND_LIGHT_OFF_HAND, ONE_HAND, TWO_HANDED
        switch (attackWield) {
        case OFF_HAND:
            return resources.getString(R.string.attackwield_off_hand);
        case PRIMARY_HAND:
            return resources.getString(R.string.attackwield_primary_hand);
        case PRIMARY_HAND_LIGHT_OFF_HAND:
            return resources.getString(R.string.attackwield_primary_hand_light_off_hand);
        case ONE_HAND:
            return resources.getString(R.string.attackwield_one_hand);
        case TWO_HANDED:
            return resources.getString(R.string.attackwield_two_handed);
        default:
            throw new IllegalArgumentException("Can't determine attack wield: " + attackWield);
        }
    }

    @Override
    public String getDisplayCombatType(final CombatType combatType) {
        // MELEE_WEAPON, RANGED_WEAPON
        switch (combatType) {
        case MELEE_WEAPON:
            return resources.getString(R.string.combat_type_melee_weapon);
        case RANGED_WEAPON:
            return resources.getString(R.string.combat_type_ranged_weapon);
        default:
            throw new IllegalArgumentException("Can't determine combat type: " + combatType);
        }
    }

    @Override
    public String getDisplayWeaponCategory(final WeaponCategory weaponCategory) {
        // NORMAL_WEAPON, REACH_WEAPON, DOUBLE_WEAPON, THROWN_WEAPON, PROJECTILE_WEAPON, AMMUNITION
        switch (weaponCategory) {
        case NORMAL_WEAPON:
            return resources.getString(R.string.weapon_category_normal_weapon);
        case REACH_WEAPON:
            return resources.getString(R.string.weapon_category_reach_weapon);
        case DOUBLE_WEAPON:
            return resources.getString(R.string.weapon_category_double_weapon);
        case THROWN_WEAPON:
            return resources.getString(R.string.weapon_category_thrown_weapon);
        case PROJECTILE_WEAPON:
            return resources.getString(R.string.weapon_category_projectile_weapon);
        case AMMUNITION:
            return resources.getString(R.string.weapon_category_ammunition);
        default:
            throw new IllegalArgumentException("Can't determine weapon category: " + weaponCategory);
        }
    }

    @Override
    public String getDisplayWeaponEncumbrance(final WeaponEncumbrance weaponEncumbrance) {
        // LIGHT_MELEE_WEAPON, ONE_HANDED_MELEE_WEAPON, TWO_HANDED_MELEE_WEAPON, LIGHT_RANGED_WEAPON,
        // ONE_HANDED_RANGED_WEAPON, TWO_HANDED_RANGED_WEAPON
        switch (weaponEncumbrance) {
        case LIGHT_HANDED:
            return resources.getString(R.string.weapon_encumbrance_light_handed);
        case ONE_HANDED:
            return resources.getString(R.string.weapon_encumbrance_one_handed);
        case TWO_HANDED:
            return resources.getString(R.string.weapon_encumbrance_two_handed);
        default:
            throw new IllegalArgumentException("Can't determine weapon encumbrance: " + weaponEncumbrance);
        }
    }

    @Override
    public String getDisplaySave(final Save save) {
        switch (save) {
        case FORTITUDE:
            return resources.getString(R.string.save_fortitude);
        case REFLEX:
            return resources.getString(R.string.save_reflex);
        case WILL:
            return resources.getString(R.string.save_will);
        default:
            throw new IllegalArgumentException("Can't determine save: " + save);
        }
    }

    @Override
    public String getDisplayCastingTime(final CastingTime castingTime) {
        switch (castingTime) {
        case NONE:
            return resources.getString(R.string.spell_castingtime_none);
        case ONE_FREE_ACTION:
            return resources.getString(R.string.spell_castingtime_onefreeaction);
        case ONE_STANDARD_ACTION:
            return resources.getString(R.string.spell_castingtime_onestandardaction);
        case ONE_ROUND:
            return resources.getString(R.string.spell_castingtime_oneround);
        case ONE_MINUTE:
            return resources.getString(R.string.spell_castingtime_oneminute);
        case TEN_MINUTES:
            return resources.getString(R.string.spell_castingtime_tenminutes);
        case ONE_HOUR:
            return resources.getString(R.string.spell_castingtime_onehour);
        case ONE_IMMEDIATE_ACTION:
            return resources.getString(R.string.spell_castingtime_oneimmediateaction);
        case ONE_SWIFT_ACTION:
            return resources.getString(R.string.spell_castingtime_oneswiftaction);
        case SPECIFIC:
            return resources.getString(R.string.spell_castingtime_specific);
        default:
            throw new IllegalArgumentException("Can't determine casting time: " + castingTime);
        }
    }

    @Override
    public String getDisplaySchool(final School school) {
        switch (school) {
        case ABJURATION:
            return resources.getString(R.string.spell_school_abjuration);
        case CONJURATION:
            return resources.getString(R.string.spell_school_conjuration);
        case DIVINATION:
            return resources.getString(R.string.spell_school_divination);
        case ENCHANTMENT:
            return resources.getString(R.string.spell_school_enchantment);
        case EVOCATION:
            return resources.getString(R.string.spell_school_evocation);
        case ILLUSION:
            return resources.getString(R.string.spell_school_illusion);
        case NECROMANCY:
            return resources.getString(R.string.spell_school_necormancy);
        case TRANSMUTATION:
            return resources.getString(R.string.spell_school_transmuation);
        case UNIVERSAL:
            return resources.getString(R.string.spell_school_universal);
        default:
            throw new IllegalArgumentException("Can't determine school: " + school);
        }
    }

    @Override
    public String getDisplaySchoolShort(final School school) {
        switch (school) {
        case ABJURATION:
            return resources.getString(R.string.spell_school_abj);
        case CONJURATION:
            return resources.getString(R.string.spell_school_con);
        case DIVINATION:
            return resources.getString(R.string.spell_school_div);
        case ENCHANTMENT:
            return resources.getString(R.string.spell_school_enc);
        case EVOCATION:
            return resources.getString(R.string.spell_school_evo);
        case ILLUSION:
            return resources.getString(R.string.spell_school_ill);
        case NECROMANCY:
            return resources.getString(R.string.spell_school_nec);
        case TRANSMUTATION:
            return resources.getString(R.string.spell_school_tra);
        case UNIVERSAL:
            return resources.getString(R.string.spell_school_uni);
        default:
            throw new IllegalArgumentException("Can't determine school: " + school);
        }
    }

    @Override
    public String getDisplaySubSchool(final SubSchool subSchool) {
        switch (subSchool) {
        case CALLING:
            return resources.getString(R.string.spell_subschool_calling);
        case CHARM:
            return resources.getString(R.string.spell_subschool_charm);
        case COMPULSION:
            return resources.getString(R.string.spell_subschool_compulsion);
        case CREATION:
            return resources.getString(R.string.spell_subschool_creation);
        case FIGMENT:
            return resources.getString(R.string.spell_subschool_figment);
        case GLAMER:
            return resources.getString(R.string.spell_subschool_glamer);
        case HEALING:
            return resources.getString(R.string.spell_subschool_healing);
        case NONE:
            return resources.getString(R.string.spell_subschool_none);
        case PATTERN:
            return resources.getString(R.string.spell_subschool_pattern);
        case PHANTASM:
            return resources.getString(R.string.spell_subschool_phantasm);
        case POLYMORPH:
            return resources.getString(R.string.spell_subschool_polymorph);
        case SCRYING:
            return resources.getString(R.string.spell_subschool_scrying);
        case SHADOW:
            return resources.getString(R.string.spell_subschool_shadow);
        case SUMMONING:
            return resources.getString(R.string.spell_subschool_summoning);
        case TELEPORTATION:
            return resources.getString(R.string.spell_subschool_teleportation);
        default:
            throw new IllegalArgumentException("Can't determine subschool: " + subSchool);
        }
    }

    @Override
    public String getDisplayDescriptor(final Descriptor descriptor) {
        switch (descriptor) {
        case ACID:
            return resources.getString(R.string.spell_descriptor_acid);
        case AIR:
            return resources.getString(R.string.spell_descriptor_air);
        case CHAOTIC:
            return resources.getString(R.string.spell_descriptor_chaotic);
        case COLD:
            return resources.getString(R.string.spell_descriptor_cold);
        case DARKNESS:
            return resources.getString(R.string.spell_descriptor_darkness);
        case DEATH:
            return resources.getString(R.string.spell_descriptor_death);
        case EARTH:
            return resources.getString(R.string.spell_descriptor_earth);
        case ELECTRICITY:
            return resources.getString(R.string.spell_descriptor_electricity);
        case EVIL:
            return resources.getString(R.string.spell_descriptor_evil);
        case FEAR:
            return resources.getString(R.string.spell_descriptor_fear);
        case FIRE:
            return resources.getString(R.string.spell_descriptor_fire);
        case FORCE:
            return resources.getString(R.string.spell_descriptor_force);
        case GOOD:
            return resources.getString(R.string.spell_descriptor_good);
        case LANGUAGE_DEPENDENT:
            return resources.getString(R.string.spell_descriptor_languagedependent);
        case LAWFUL:
            return resources.getString(R.string.spell_descriptor_lawful);
        case LIGHT:
            return resources.getString(R.string.spell_descriptor_light);
        case MIND_AFFECTING:
            return resources.getString(R.string.spell_descriptor_mindaffecting);
        case NONE:
            return resources.getString(R.string.spell_descriptor_none);
        case SONIC:
            return resources.getString(R.string.spell_descriptor_sonic);
        case WATER:
            return resources.getString(R.string.spell_descriptor_water);
        default:
            throw new IllegalArgumentException("Can't determine descriptor: " + descriptor);
        }
    }

    @Override
    public String getDisplayRange(final Range range) {
        switch (range) {
        case PERSONAL:
            return resources.getString(R.string.spell_range_personal);
        case TOUCH:
            return resources.getString(R.string.spell_range_touch);
        case CLOSE:
            return resources.getString(R.string.spell_range_close);
        case MEDIUM:
            return resources.getString(R.string.spell_range_medium);
        case LONG:
            return resources.getString(R.string.spell_range_long);
        case SPECIFIC:
            return resources.getString(R.string.spell_range_specific);
        default:
            throw new IllegalArgumentException("Can't determine range: " + range);
        }
    }

    @Override
    public String getDisplaySpellResistance(final SpellResistance spellResistance) {
        switch (spellResistance) {
        case NO:
            return resources.getString(R.string.spell_spellresistance_no);
        case YES:
            return resources.getString(R.string.spell_spellresistance_yes);
        case YES_HARMLESS:
            return resources.getString(R.string.spell_spellresistance_yesharmless);
        case YES_OBJECT:
            return resources.getString(R.string.spell_spellresistance_yesobject);
        case YES_HARMLESS_OBJECT:
            return resources.getString(R.string.spell_spellresistance_yesharmlessobject);
        case SPECIFIC:
            return resources.getString(R.string.spell_spellresistance_specific);
        case NONE:
            return resources.getString(R.string.spell_spellresistance_none);

        default:
            throw new IllegalArgumentException("Can't determine spell resistance: " + spellResistance);
        }
    }

    @Override
    public String getDisplayAbilityClass(final Ability ability) {
        if (ability instanceof ExtraFeatsAbility) {
            return resources.getString(R.string.ability_class_extrafeats);
        } else if (ability instanceof ExtraSkillPointsAbility) {
            return resources.getString(R.string.ability_class_extraskillpoints);
        } else if (ability instanceof SpelllistAbility) {
            return resources.getString(R.string.ability_class_spelllist);
        } else {
            return resources.getString(R.string.ability_class_description);
        }
    }

    @Override
    public String getDisplayNumberOfKnownSpells(final int numberOfKnownSpells, final int maxNumberOfKnownSpells,
            final int numberOfSpells) {
        final StringBuilder output = new StringBuilder();
        String textMaxNumberOfKnownSpells;
        if (maxNumberOfKnownSpells == 0) {
            textMaxNumberOfKnownSpells = resources.getString(R.string.page_known_spell_none);
        } else if (maxNumberOfKnownSpells == numberOfSpells) {
            textMaxNumberOfKnownSpells = resources.getString(R.string.page_known_spell_all);
        } else {
            textMaxNumberOfKnownSpells = Integer.toString(maxNumberOfKnownSpells);
        }
        output.append("[ ").append(numberOfKnownSpells).append(" / ").append(textMaxNumberOfKnownSpells).append(" ]");
        return output.toString();
    }

    @Override
    public String getDisplaySpellSlotLevel(final int level) {
        switch (level) {
        case 0:
            return "0-LEVEL SPELL SLOTS";
        case 1:
            return "1st-LEVEL SPELL SLOTS";
        case 2:
            return "2nd-LEVEL SPELL SLOTS";
        case 3:
            return "3rd-LEVEL SPELL SLOTS";
        default:
            return level + "th LEVEL SPELL SLOTS";
        }
    }

    @Override
    public String getDisplayCastingType(final CastingType castingType) {
        switch (castingType) {
        case SPONTANEOUS:
            return resources.getString(R.string.casting_type_sponanteous);
        case PREPARED:
            return resources.getString(R.string.casting_type_prepared);

        default:
            throw new IllegalArgumentException("Can't determine casting type: " + castingType);
        }
    }

    @Override
    public String getDisplaySpellSource(final SpellSource spellSource) {
        switch (spellSource) {
        case DIVINE:
            return resources.getString(R.string.spell_source_divine);
        case ARCANE:
            return resources.getString(R.string.spell_source_arcane);

        default:
            throw new IllegalArgumentException("Can't determine spell source: " + spellSource);
        }
    }

}
