package com.android.ash.charactersheet.dac.dao.sqlite;

import android.test.AndroidTestCase;

public class WrapperSQLiteCharacterDaoTest extends AndroidTestCase {

    private SQLiteCharacterDaoTest characterDaoSQLiteTest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        characterDaoSQLiteTest = new SQLiteCharacterDaoTest(getContext());
        characterDaoSQLiteTest.setUp();
    }

    public void testGetAllCharacters() throws Exception {
        characterDaoSQLiteTest.testGetAllCharacters();
    }

    public void testGetCharacter() throws Exception {
        characterDaoSQLiteTest.testGetCharacter();
    }

    public void testUpdateAttribute() throws Exception {
        characterDaoSQLiteTest.testUpdateAttribute();
    }

    public void testUpdateClassLevel() throws Exception {
        characterDaoSQLiteTest.testUpdateClassLevel();
    }

    public void testCreateCharacter() throws Exception {
        characterDaoSQLiteTest.testCreateCharacter();
    }

    public void testDeleteCharacter() throws Exception {
        characterDaoSQLiteTest.testDeleteCharacter();
    }

    public void testGetAllNotes() throws Exception {
        characterDaoSQLiteTest.testGetAllNotes();
    }

    public void testCreateAndDeleteNote() throws Exception {
        characterDaoSQLiteTest.testCreateAndDeleteNote();
    }

    public void testGetKnownSpells() throws Exception {
        characterDaoSQLiteTest.testGetKnownSpells();
    }

    public void testGetSpellSlots() throws Exception {
        characterDaoSQLiteTest.testGetSpellSlots();
    }

    public void testUpdateSpellSlot() throws Exception {
        characterDaoSQLiteTest.testUpdateSpellSlot();
    }

    public void testCreateSpellSlot() throws Exception {
        characterDaoSQLiteTest.testCreateSpellSlot();
    }

    public void testUpdateCharacterSkill() throws Exception {
        characterDaoSQLiteTest.testUpdateCharacterSkill();

    }

}
