package com.android.ash.charactersheet.boc.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.test.AndroidTestCase;

import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.android.AndroidPreferenceDao;

public class PreferenceServiceTest extends AndroidTestCase {

    private SharedPreferences.Editor editor;
    private PreferenceService preferenceService;

    @Override
    protected void setUp() throws Exception {
        final SharedPreferences preferences = getContext().getSharedPreferences(AndroidPreferenceDao.FILENAME,
                Context.MODE_PRIVATE);
        editor = preferences.edit();
        preferenceService = new PreferenceServiceImpl(new AndroidPreferenceDao(getContext()));
    }

    public void testGetBooleanException() throws Exception {
        try {
            preferenceService.getBoolean("unknownPreference");
            fail("Missing exception, expected IllegalArgumentException");
        } catch (final IllegalArgumentException illegalArgumentException) {
            // correct exception thrown
        } catch (final Exception exception) {
            fail("Wrong exception thrown, expected IllegalArgumentException");
        }
    }

    public void testGetIntException() throws Exception {
        try {
            preferenceService.getInt("unknownPreference");
            fail("Missing exception, expected IllegalArgumentException");
        } catch (final IllegalArgumentException illegalArgumentException) {
            // correct exception thrown
        } catch (final Exception exception) {
            fail("Wrong exception thrown, expected IllegalArgumentException");
        }
    }

    public void testSetBoolenException() throws Exception {
        try {
            preferenceService.setBoolean("unknownPrefence", false);
            fail("Missing exception, expected IllegalArgumentException");
        } catch (final IllegalArgumentException illegalArgumentException) {
            // correct exception thrown
        } catch (final Exception exception) {
            fail("Wrong exception. Expected IllegalArgumentException");
        }
    }

    public void testSetIntException() throws Exception {
        try {
            preferenceService.setInt("unknownPrefence", 0);
            fail("Missing exception, expected IllegalArgumentException");
        } catch (final IllegalArgumentException illegalArgumentException) {
            // correct exception thrown
        } catch (final Exception exception) {
            fail("Wrong exception. Expected IllegalArgumentException");
        }
    }

    public void testGetShowImageAsBackground() {
        editor.putBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, true);
        editor.commit();

        final boolean showImageAsBackground = preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND);
        assertTrue(showImageAsBackground);
    }

    public void testGetBackgroundColor() throws Exception {
        editor.putInt(PreferenceService.BACKGROUND_COLOR, Color.RED);
        editor.commit();

        final int color = preferenceService.getInt(PreferenceService.BACKGROUND_COLOR);
        assertEquals(Color.RED, color);
    }

    public void testSetShowImageAsBackground() throws Exception {
        editor.putBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, false);
        editor.commit();

        preferenceService.setBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, true);

        final boolean showImageAsBackground = preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND);
        assertTrue(showImageAsBackground);
    }

    public void testSetBackgroundColor() throws Exception {
        editor.putInt(PreferenceService.BACKGROUND_COLOR, Color.BLACK);
        editor.commit();

        preferenceService.setInt(PreferenceService.BACKGROUND_COLOR, Color.RED);

        final int color = preferenceService.getInt(PreferenceService.BACKGROUND_COLOR);
        assertEquals(Color.RED, color);
    }

    public void testGetGameSystemType() throws Exception {
        editor.putInt(PreferenceService.GAME_SYSTEM_TYPE, 0);
        editor.commit();

        preferenceService.setInt(PreferenceService.GAME_SYSTEM_TYPE, GameSystemType.PATHFINDER.ordinal());

        final int gameSystemTypeId = preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE);
        assertEquals(GameSystemType.PATHFINDER.ordinal(), gameSystemTypeId);
    }

    public void testSetGameSystemType() throws Exception {
        editor.putInt(PreferenceService.GAME_SYSTEM_TYPE, GameSystemType.DNDV35.ordinal());
        editor.commit();

        preferenceService.setInt(PreferenceService.GAME_SYSTEM_TYPE, GameSystemType.PATHFINDER.ordinal());

        final int gameSystemTypeId = preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE);
        assertEquals(GameSystemType.PATHFINDER.ordinal(), gameSystemTypeId);
    }
}
