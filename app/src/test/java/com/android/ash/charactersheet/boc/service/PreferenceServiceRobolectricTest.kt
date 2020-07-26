package com.android.ash.charactersheet.boc.service

import android.content.Context
import android.content.SharedPreferences.Editor
import android.graphics.Color
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.android.ash.charactersheet.dac.dao.android.AndroidPreferenceDao
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class PreferenceServiceRobolectricTest {

    private lateinit var editor: Editor
    private lateinit var preferenceService: PreferenceService

    @Before
    fun setUp() {
        val filename = "d20cs-test.pref"
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE)
        editor = preferences.edit()
        preferenceService = PreferenceServiceImpl(AndroidPreferenceDao(context, filename))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testGetBooleanException() {
        preferenceService.getBoolean("unknownPreference")
    }

    @Test(expected = IllegalArgumentException::class)
    fun testGetIntException() {
        preferenceService.getInt("unknownPreference")
    }

    @Test(expected = IllegalArgumentException::class)
    fun testSetBooleanException() = preferenceService.setBoolean("unknownPreference", false)

    @Test(expected = IllegalArgumentException::class)
    fun testSetIntException() = preferenceService.setInt("unknownPreference", 0)

    @Test
    fun testGetShowImageAsBackground() {
        editor.putBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, true)
        editor.commit()
        val showImageAsBackground = preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND)
        Assert.assertTrue(showImageAsBackground)
    }

    @Test
    fun testGetBackgroundColor() {
        editor.putInt(PreferenceService.BACKGROUND_COLOR, Color.RED)
        editor.commit()
        val color = preferenceService.getInt(PreferenceService.BACKGROUND_COLOR)
        Assert.assertEquals(Color.RED.toLong(), color.toLong())
    }

    @Test
    fun testSetShowImageAsBackground() {
        editor.putBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, false)
        editor.commit()
        preferenceService.setBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND, true)
        val showImageAsBackground = preferenceService.getBoolean(PreferenceService.SHOW_IMAGE_AS_BACKGROUND)
        Assert.assertTrue(showImageAsBackground)
    }

    @Test
    fun testSetBackgroundColor() {
        editor.putInt(PreferenceService.BACKGROUND_COLOR, Color.BLACK)
        editor.commit()
        preferenceService.setInt(PreferenceService.BACKGROUND_COLOR, Color.RED)
        val color = preferenceService.getInt(PreferenceService.BACKGROUND_COLOR)
        Assert.assertEquals(Color.RED.toLong(), color.toLong())
    }

    @Test
    fun testGetGameSystemType() {
        editor.putInt(PreferenceService.GAME_SYSTEM_TYPE, 0)
        editor.commit()
        preferenceService.setInt(PreferenceService.GAME_SYSTEM_TYPE, GameSystemType.PATHFINDER.ordinal)
        val gameSystemTypeId = preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE)
        Assert.assertEquals(GameSystemType.PATHFINDER.ordinal.toLong(), gameSystemTypeId.toLong())
    }

    @Test
    fun testSetGameSystemType() {
        editor.putInt(PreferenceService.GAME_SYSTEM_TYPE, GameSystemType.DNDV35.ordinal)
        editor.commit()
        preferenceService.setInt(PreferenceService.GAME_SYSTEM_TYPE, GameSystemType.PATHFINDER.ordinal)
        val gameSystemTypeId = preferenceService.getInt(PreferenceService.GAME_SYSTEM_TYPE)
        Assert.assertEquals(GameSystemType.PATHFINDER.ordinal.toLong(), gameSystemTypeId.toLong())
    }
}