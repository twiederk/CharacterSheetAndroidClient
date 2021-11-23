package com.android.ash.charactersheet.boc.service;

import android.graphics.Color;

import com.android.ash.charactersheet.boc.model.GameSystemType;
import com.android.ash.charactersheet.dac.dao.PreferenceDao;

import java.util.Arrays;

/**
 * Handles preferences. Checks if preference exists and if correct type of preference value is stored.
 */
public class PreferenceServiceImpl implements PreferenceService {

    private static final String[] INT_PREFERENCES_KEYS = { BACKGROUND_COLOR, GAME_SYSTEM_TYPE };
    private static final String[] BOOLEAN_PREFERENCES_KEYS = { SHOW_IMAGE_AS_BACKGROUND };

    private final PreferenceDao preferenceDao;

    /**
     * Instantiates PreferenceServiceImpl with given data access object.
     *
     * @param preferenceDao The dao to persist preferences
     */
    public PreferenceServiceImpl(final PreferenceDao preferenceDao) {
        this.preferenceDao = preferenceDao;
    }

    @Override
    public boolean getBoolean(final String key) {
        boolean preference;
        if (SHOW_IMAGE_AS_BACKGROUND.equals(key)) {
            preference = preferenceDao.getBoolean(key, true);
        } else {
            throw new IllegalArgumentException("Can't get boolean preference with key: " + key);
        }
        return preference;
    }

    @Override
    public int getInt(final String key) {
        int preference;
        if (BACKGROUND_COLOR.equals(key)) {
            preference = preferenceDao.getInt(key, Color.BLACK);
        } else if (GAME_SYSTEM_TYPE.equals(key)) {
            preference = preferenceDao.getInt(key, GameSystemType.DND5E.ordinal());
        } else {
            throw new IllegalArgumentException("Can't get int preference with key: " + key);
        }
        return preference;
    }

    @Override
    public void setBoolean(final String key, final boolean value) {
        if (Arrays.asList(BOOLEAN_PREFERENCES_KEYS).contains(key)) {
            preferenceDao.setBoolean(key, value);
        } else {
            throw new IllegalArgumentException("Can't set boolean preference with key: " + key);
        }
    }

    @Override
    public void setInt(final String key, final int value) {
        if (Arrays.asList(INT_PREFERENCES_KEYS).contains(key)) {
            preferenceDao.setInt(key, value);
        } else {
            throw new IllegalArgumentException("Can't set int preference with key: " + key);
        }
    }
}
