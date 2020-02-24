package com.android.ash.charactersheet.boc.service;

import android.content.Context;

import com.d20charactersheet.framework.boc.model.Alignment;
import com.d20charactersheet.framework.boc.model.CharacterClass;
import com.d20charactersheet.framework.boc.model.ClassLevel;
import com.d20charactersheet.framework.boc.model.Damage;
import com.d20charactersheet.framework.boc.model.Descriptor;
import com.d20charactersheet.framework.boc.model.Die;
import com.d20charactersheet.framework.boc.model.QualityType;
import com.d20charactersheet.framework.boc.model.RuleError;
import com.d20charactersheet.framework.boc.model.School;
import com.d20charactersheet.framework.boc.model.Sex;
import com.d20charactersheet.framework.boc.model.Spell;
import com.d20charactersheet.framework.boc.model.SubSchool;
import com.d20charactersheet.framework.boc.model.Weapon;
import com.d20charactersheet.framework.boc.service.DisplayService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class AndroidDisplayServiceTest {

    private DisplayService displayService;
    private CharacterClass wizard;
    private CharacterClass bard;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        displayService = new AndroidDisplayServiceImpl(context.getResources());
        bard = createCharacterClass(1, "Bard");
        wizard = createCharacterClass(10, "Wizard");
    }

    private CharacterClass createCharacterClass(final int id, final String name) {
        final CharacterClass characterClass = new CharacterClass();
        characterClass.setId(id);
        characterClass.setName(name);
        characterClass.setClassAbilities(new LinkedList<>());
        return characterClass;
    }

    @Test
    public void testGetDisplayClassLevelsWithSingleClass() {

        // Wizard (4)
        final List<ClassLevel> classLevels = new LinkedList<>();
        classLevels.add(new ClassLevel(wizard, 4));

        // test
        final String displayClassLevels = displayService.getDisplayClassLevels(classLevels);
        assertEquals("Wizard (4)", displayClassLevels);
    }

    @Test
    public void testGetDisplayClassLevel() {

        // Wizard (4)
        String displayClassLevel = displayService.getDisplayClassLevel(new ClassLevel(wizard, 4));
        assertEquals("Wizard (4)", displayClassLevel);

        // Bard (2)
        displayClassLevel = displayService.getDisplayClassLevel(new ClassLevel(bard, 2));
        assertEquals("Bard (2)", displayClassLevel);
    }

    @Test
    public void testGetDisplayClassLevelsWithMultipleClass() {

        // Wizard (4), Bard (2)
        final List<ClassLevel> classLevels = new LinkedList<>();
        classLevels.add(new ClassLevel(wizard, 4));
        classLevels.add(new ClassLevel(bard, 2));

        // test
        final String displayClassLevels = displayService.getDisplayClassLevels(classLevels);
        assertEquals("Wizard (4), Bard (2)", displayClassLevels);
    }

    @Test
    public void testGetModifier() {
        String modifier = displayService.getDisplayModifier(-3);
        assertEquals("-3", modifier);

        modifier = displayService.getDisplayModifier(0);
        assertEquals("0", modifier);

        modifier = displayService.getDisplayModifier(2);
        assertEquals("+2", modifier);
    }

    @Test
    public void testGetDisplayBaseAttackBonus() {

        String displayBaseAttackBonus = displayService.getDisplayBaseAttackBonus(0);
        assertEquals("+0", displayBaseAttackBonus);

        displayBaseAttackBonus = displayService.getDisplayBaseAttackBonus(5);
        assertEquals("+5", displayBaseAttackBonus);

        displayBaseAttackBonus = displayService.getDisplayBaseAttackBonus(6);
        assertEquals("+6/+1", displayBaseAttackBonus);

        displayBaseAttackBonus = displayService.getDisplayBaseAttackBonus(10);
        assertEquals("+10/+5", displayBaseAttackBonus);

        displayBaseAttackBonus = displayService.getDisplayBaseAttackBonus(11);
        assertEquals("+11/+6/+1", displayBaseAttackBonus);
    }

    @Test
    public void testGetDisplaySex() {
        String sex = displayService.getDisplaySex(Sex.MALE);
        assertEquals("Male", sex);
        sex = displayService.getDisplaySex(Sex.FEMALE);
        assertEquals("Female", sex);
    }

    @Test
    public void testGetDisplayAlignment() {
        String alignment = displayService.getDisplayAlignment(Alignment.LAWFUL_GOOD);
        assertEquals("Lawful Good", alignment);

        alignment = displayService.getDisplayAlignment(Alignment.NEUTRAL);
        assertEquals("Neutral", alignment);

        alignment = displayService.getDisplayAlignment(Alignment.CHAOTIC_EVIL);
        assertEquals("Chaotic Evil", alignment);
    }

    @Test
    public void testGetDisplayArmorClassFormular() {
        String formular = displayService.getDisplayArmourClassFormular(-2);
        assertEquals("10 + (-2) + ", formular);

        formular = displayService.getDisplayArmourClassFormular(0);
        assertEquals("10 + (0) + ", formular);

        formular = displayService.getDisplayArmourClassFormular(5);
        assertEquals("10 + (+5) + ", formular);
    }

    @Test
    public void testGetErrorMessage() {
        final String errorMessage = displayService.getErrorMessage(RuleError.ATTRIBUTE_VALUE_NOT_IN_RANGE);
        assertEquals("Attribute value must be between 1 and 99.", errorMessage);
    }

    @Test
    public void testGetDisplayInitiativeFormular() {
        String formular = displayService.getDisplaySimpleFormular(-2);
        assertEquals("-2 + ", formular);

        formular = displayService.getDisplaySimpleFormular(0);
        assertEquals("0 + ", formular);

        formular = displayService.getDisplaySimpleFormular(5);
        assertEquals("+5 + ", formular);
    }

    @Test
    public void testGetDisplayWeight() {
        String displayWeight = displayService.getDisplayWeight(8.0f);
        assertNotNull(displayWeight);
        assertEquals("8 lb.", displayWeight);

        displayWeight = displayService.getDisplayWeight(6.5f);
        assertNotNull(displayWeight);
        assertEquals("6.5 lb.", displayWeight);
    }

    @Test
    public void testGetDisplayCost() {
        String displayCost = displayService.getDisplayCost(10f);
        assertNotNull(displayCost);
        assertEquals("10 gp", displayCost);

        displayCost = displayService.getDisplayCost(1f);
        assertNotNull(displayCost);
        assertEquals("1 gp", displayCost);

        displayCost = displayService.getDisplayCost(0.1f);
        assertNotNull(displayCost);
        assertEquals("0.1 gp", displayCost);

        displayCost = displayService.getDisplayCost(0.01f);
        assertNotNull(displayCost);
        assertEquals("0.01 gp", displayCost);
    }

    @Test
    public void testGetDisplayDamage() {
        String displayDamage = displayService.getDisplayDamage(createWeapon(new Damage(1, Die.D8), 2));
        assertEquals("1D8+2", displayDamage);

        displayDamage = displayService.getDisplayDamage(createWeapon(new Damage(1, Die.D4), -1));
        assertEquals("1D4-1", displayDamage);

        displayDamage = displayService.getDisplayDamage(createWeapon(new Damage(1, Die.D10), 0));
        assertEquals("1D10", displayDamage);

        displayDamage = displayService.getDisplayDamage(createWeapon(new Damage(0, Die.D2), 1));
        assertEquals("+1", displayDamage);

        displayDamage = displayService.getDisplayDamage(createWeapon(new Damage(0, Die.D2), 0));
        assertEquals("", displayDamage);
    }

    private Weapon createWeapon(final Damage damage, final int enhancementBonus) {
        final Weapon weapon = new Weapon();
        weapon.setDamage(damage);
        weapon.setEnhancementBonus(enhancementBonus);
        return weapon;
    }

    @Test
    public void testGetDisplayItem() {
        final Weapon weapon = new Weapon();
        weapon.setName("Dagger");
        String displayWeapon = displayService.getDisplayItem(weapon);
        assertEquals("Dagger", displayWeapon);

        weapon.setQualityType(QualityType.MASTERWORK);
        displayWeapon = displayService.getDisplayItem(weapon);
        assertEquals("Dagger, masterwork", displayWeapon);

        weapon.setQualityType(QualityType.MAGIC);
        displayWeapon = displayService.getDisplayItem(weapon);
        assertEquals("Dagger", displayWeapon);

    }

    @Test
    public void testGetDisplaySpellComponents() {

        Spell spell = new Spell();
        spell.setVerbal(true);
        String components = displayService.getDisplaySpellComponents(spell);
        assertNotNull(components);
        assertEquals("V", components);

        spell = new Spell();
        spell.setVerbal(true);
        spell.setMaterial(true);
        components = displayService.getDisplaySpellComponents(spell);
        assertNotNull(components);
        assertEquals("V, M", components);

        spell = new Spell();
        spell.setVerbal(false);
        spell.setMaterial(true);
        components = displayService.getDisplaySpellComponents(spell);
        assertNotNull(components);
        assertEquals("M", components);
    }

    @Test
    public void testGetDisplaySpellSchool() {
        final Spell spell = new Spell();
        assertNotNull(displayService.getDisplaySpellSchool(spell));

        spell.setSchool(School.CONJURATION);
        assertEquals("Conjuration", displayService.getDisplaySpellSchool(spell));

        spell.setSubSchool(SubSchool.CREATION);
        assertEquals("Conjuration (Creation)", displayService.getDisplaySpellSchool(spell));

        spell.setDescriptors(new Descriptor[]{Descriptor.ACID});
        assertEquals("Conjuration (Creation) [Acid]", displayService.getDisplaySpellSchool(spell));

        spell.setDescriptors(new Descriptor[]{Descriptor.ACID, Descriptor.AIR});
        assertEquals("Conjuration (Creation) [Acid, Air]", displayService.getDisplaySpellSchool(spell));

        spell.setSchool(School.ENCHANTMENT);
        spell.setSubSchool(SubSchool.COMPULSION);
        spell.setDescriptors(new Descriptor[]{Descriptor.MIND_AFFECTING});
        assertEquals("Enchantment (Compulsion) [Mind-Affecting]", displayService.getDisplaySpellSchool(spell));
    }

}
