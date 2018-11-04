package com.android.ash.charactersheet.boc.service;

import android.content.Context;

import com.android.ash.charactersheet.AndroidObjectMother;
import com.d20charactersheet.framework.boc.service.BaseCharacterServiceTest;

public class AndroidCharacterServiceTest extends BaseCharacterServiceTest {

    private final Context context;

    public AndroidCharacterServiceTest(final Context context) {
        super();
        this.context = context;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final AndroidObjectMother objectMother = new AndroidObjectMother(context);
        gameSystem = objectMother.getGameSystem();

        fighter = objectMother.getCharacterClass("Fighter");
        wizard = objectMother.getCharacterClass("Wizard");
        human = objectMother.getRace("Human");
    }

    @Override
    protected int getNumberOfCharacters() {
        return 1;
    }

}
