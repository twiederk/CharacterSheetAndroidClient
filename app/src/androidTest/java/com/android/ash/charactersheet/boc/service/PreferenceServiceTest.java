package com.android.ash.charactersheet.boc.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.android.AndroidPreferenceDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class PreferenceServiceTest {

    private SharedPreferences.Editor editor;
    private PreferenceService preferenceService;

    @Before
    public void setUp() {
        final Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final SharedPreferences preferences = context.getSharedPreferences(AndroidPreferenceDao.FILENAME,
                Context.MODE_PRIVATE);
        editor = preferences.edit();
        preferenceService = new PreferenceServiceImpl(new AndroidPreferenceDao(context));
    }

    @Test
    public void testGetBooleanException() {
        try {
            preferenceService.getBoolean("unknownPreference");
            fail("Missing exception, expected IllegalArgumentException");
        } catch (final IllegalArgumentException illegalArgumentException) {
            // correct exception thrown
        } catch (final Exception exception) {
            fail("Wrong exception thrown, expected IllegalArgumentException");
        }
    }

    @Test
    public void testGetIntException() {
        try {
            preferenceService.getInt("unknownPreference");
            fail("Missing exception, expected IllegalArgumentException");
        } catch (final IllegalArgumentException illegalArgumentException) {
            // correct exception thrown
        } catch (final Exception exception) {
            fail("Wrong exception thrown, expected IllegalArgumentException");
        }
    }

    @Test
    public void testSetBooleanException() {
        try {
            preferenceService.setBoolean("unknownPrefence", false);
            fail("Missing exception, expected IllegalArgumentException");
        } catch (final IllegalArgumentException illegalArgumentException) {
            // correct exception thrown
        } catch (final Exception exception) {
            fail("Wrong exception. Expected IllegalArgumentException");
        }
    }

    @Test
    public void testSetIntException() {
        try {
            preferenceService.setInt("unknownPrefence", 0);
            fail("Missing exception, expected IllegalArgumentException");
        } catch (final IllegalArgumentException illegalArgumentException) {
            // correct exception thrown
        } catch (final Exception exception) {
            fail("Wrong exception. Expected IllegalArgumentException");
        }
    }

    @Test
    public void testGetShowImageAsBackground() {
        editor.putBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, true);
        editor.commit();

        final boolean showImageAsBackground = preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND);
        assertTrue(showImageAsBackground);
    }

    @Test
    public void testGetBackgroundColor() {
        editor.putInt(PreferenceService.BACKGROUND_COLOR, Color.RED);
        editor.commit();

        final int color = preferenceService.getInt(PreferenceService.BACKGROUND_COLOR);
        assertEquals(Color.RED, color);
    }

    @Test
    public void testSetShowImageAsBackground() {
        editor.putBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, false);
        editor.commit();

        preferenceService.setBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, true);

        final boolean showImageAsBackground = preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND);
        assertTrue(showImageAsBackground);
    }

    @Test
    public void testSetBackgroundColor() {
        editor.putInt(PreferenceService.BACKGROUND_COLOR, Color.BLACK);
        editor.commit();

        preferenceService.setInt(PreferenceService.BACKGROUND_COLOR, Color.RED);

        final int color = preferenceService.getInt(PreferenceService.BACKGROUND_COLOR);
        assertEquals(Color.RED, color);
    }

    @Test
    public void testGetGameSystemType() {
        editor.putInt(PreferenceService.GAME_SYSTEM_TYPE, 0);
        editor.commit();

        preferenceService.setInt(PreferenceService.GAME_SYSTEM_TYPE, GameSystemType.PATHFINDER.ordinal());

        final int gameSystemTypeId = preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE);
        assertEquals(GameSystemType.PATHFINDER.ordinal(), gameSystemTypeId);
    }

    @Test
    public void testSetGameSystemType() {
        editor.putInt(PreferenceService.GAME_SYSTEM_TYPE, GameSystemType.DNDV35.ordinal());
        editor.commit();

        preferenceService.setInt(PreferenceService.GAME_SYSTEM_TYPE, GameSystemType.PATHFINDER.ordinal());

        final int gameSystemTypeId = preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE);
        assertEquals(GameSystemType.PATHFINDER.ordinal(), gameSystemTypeId);
    }
}
