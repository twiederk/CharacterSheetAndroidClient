package com.android.ash.charactersheet.dac.dao.sqlite;

import android.test.AndroidTestCase;

public class WrapperSQLiteAbilityDaoTest extends AndroidTestCase {

    private SQLiteAbilityDaoTest abilityDaoSQLiteTest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        abilityDaoSQLiteTest = new SQLiteAbilityDaoTest(getContext());
        abilityDaoSQLiteTest.setUp();
    }

    public void testGetAllAbilities() {
        abilityDaoSQLiteTest.testGetAllAbilities();
    }

    public void testExtraFeatAbility() throws Exception {
        abilityDaoSQLiteTest.testExtraFeatAbility();
    }

    public void testClassAbilityFastMovementBarbarian() throws Exception {
        abilityDaoSQLiteTest.testAbilityFastMovementBarbarian();
    }

    public void testCreateAbility() throws Exception {
        abilityDaoSQLiteTest.testCreateAbility();
    }

    public void testExtraSkillPointsAbility() throws Exception {
        abilityDaoSQLiteTest.testExtraSkillPointsAbility();
    }

    public void testSpelllistAbility() throws Exception {
        abilityDaoSQLiteTest.testSpelllistAbility();
    }

    public void testCreateSpelllistAbility() throws Exception {
        abilityDaoSQLiteTest.testCreateSpelllistAbility();
    }

    public void testUpdateSpelllistAbility() throws Exception {
        abilityDaoSQLiteTest.testUpdateSpelllistAbility();
    }
}
