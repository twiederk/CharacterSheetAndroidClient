package com.android.ash.charactersheet.boc.service;

import android.test.AndroidTestCase;

public class WrapperCharacterServiceTest extends AndroidTestCase {

    private AndroidCharacterServiceTest androidCharacterServiceTest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        androidCharacterServiceTest = new AndroidCharacterServiceTest(getContext());
        androidCharacterServiceTest.setUp();
    }

    public void testCreateCharacter() throws Exception {
        androidCharacterServiceTest.testCreateCharacter();
    }

    public void testCreateCharacterWithIllegalValues() throws Exception {
        androidCharacterServiceTest.testCreateCharacterWithIllegalValues();
    }

    public void testGetAllCharacters() {
        androidCharacterServiceTest.testGetAllCharacters();
    }

    public void testGetBelvador() throws Exception {
        androidCharacterServiceTest.testGetBelvador();
    }

    public void testGetCharacterWithAllSkills() throws Exception {
        androidCharacterServiceTest.testGetCharacterWithAllSkills();
    }

    public void testGetCharacterWithListenSkill() throws Exception {
        androidCharacterServiceTest.testGetCharacterWithListenSkill();
    }

    public void testInitiativeMiscModifier() throws Exception {
        androidCharacterServiceTest.testInitiativeModifier();
    }

    public void testUpdateAttribute() {
        androidCharacterServiceTest.testUpdateAttribute();
    }

    public void testUpdateClassLevel() throws Exception {
        androidCharacterServiceTest.testUpdateClassLevel();
    }

    public void testGetKnownSpells() throws Exception {
        androidCharacterServiceTest.testGetKnownSpells();
    }

    public void testCreateKnownSpells() throws Exception {
        androidCharacterServiceTest.testCreateKnownSpells();
    }

    public void testDeleteKnownSpells() throws Exception {
        androidCharacterServiceTest.testDeleteKnownSpells();
    }

    public void testAddCharacterAbilities() throws Exception {
        androidCharacterServiceTest.testAddCharacterAbilities();
    }

    public void testArmor() throws Exception {
        androidCharacterServiceTest.testArmor();
    }

    public void testCreateNote() throws Exception {
        androidCharacterServiceTest.testCreateNote();
    }

    public void testDeleteCharacter() throws Exception {
        androidCharacterServiceTest.testDeleteCharacter();
    }

    public void testGetCharacterWithSpellSlots() throws Exception {
        androidCharacterServiceTest.testGetCharacterWithSpellSlots();
    }

    public void testGetNotes() throws Exception {
        androidCharacterServiceTest.testGetNotes();
    }

    public void testGoods() throws Exception {
        androidCharacterServiceTest.testGoods();
    }

    public void testRemoveCharacterAbilities() throws Exception {
        androidCharacterServiceTest.testRemoveCharacterAbilities();
    }

    public void testUpdateCharacterAbilities() throws Exception {
        androidCharacterServiceTest.testUpdateCharacterAbilities();
    }

    public void testWeaponAttacks() throws Exception {
        androidCharacterServiceTest.testWeaponAttacks();
    }

    public void testWeapons() throws Exception {
        androidCharacterServiceTest.testWeapons();
    }

    public void testCreateSpellSlot() throws Exception {
        androidCharacterServiceTest.testCreateSpellSlot();
    }

    public void testCalculateSpellSlot_increaseLevel() throws Exception {
        androidCharacterServiceTest.testCalculateSpellSlot_increaseLevel();
    }

    public void testCalculateSpellSlot_decreaseLevel() throws Exception {
        androidCharacterServiceTest.testCalculateSpellSlot_decreaseLevel();
    }

    public void testUpdateCharacterSkill() throws Exception {
        androidCharacterServiceTest.testUpdateCharacterSkill();
    }
}
