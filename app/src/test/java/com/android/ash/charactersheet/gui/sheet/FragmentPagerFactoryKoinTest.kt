package com.android.ash.charactersheet.gui.sheet

import android.content.res.Resources
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class FragmentPagerFactoryKoinTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun createSectionsPagerAdapter_gameSystemIsDnDv35_createSectionsFragmentStatePagerAdapter() {

        // arrange
        gameSystemHolder.gameSystemType = GameSystemType.DNDV35
        val resources: Resources = mock()
        whenever(resources.getString(any())).thenReturn("myTitle")

        // act
        val sectionsPagerAdapter = FragmentPagerFactory().createSectionsPagerAdapter(mock(), resources)

        // assert
        assertThat(sectionsPagerAdapter).isInstanceOf(DnDv35FragmentPager::class.java)
    }

    @Test
    fun createSectionsPagerAdapter_gameSystemIsDnD5e_createDnD5eSectionsFragmentStatePagerAdapter() {

        // arrange
        gameSystemHolder.gameSystemType = GameSystemType.DND5E
        val resources: Resources = mock()
        whenever(resources.getString(any())).thenReturn("myTitle")

        // act
        val sectionsPagerAdapter = FragmentPagerFactory().createSectionsPagerAdapter(mock(), resources)

        // assert
        assertThat(sectionsPagerAdapter).isInstanceOf(DnD5eFragmentPager::class.java)
    }

}