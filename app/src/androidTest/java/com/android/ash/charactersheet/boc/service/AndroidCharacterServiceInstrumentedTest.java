package com.android.ash.charactersheet.boc.service;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.android.ash.charactersheet.AndroidObjectMother;
import com.d20charactersheet.framework.boc.service.BaseCharacterServiceTest;

import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AndroidCharacterServiceInstrumentedTest extends BaseCharacterServiceTest {


    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        final AndroidObjectMother objectMother = new AndroidObjectMother(context);
        gameSystem = objectMother.getGameSystem();

        fighter = objectMother.getCharacterClass("Fighter");
        wizard = objectMother.getCharacterClass("Wizard");
        human = objectMother.getRace("Human");
    }

    @SuppressWarnings("SameReturnValue")
    @Override
    protected int getNumberOfCharacters() {
        return 1;
    }

}
