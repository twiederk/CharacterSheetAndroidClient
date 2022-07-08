package com.android.ash.charactersheet.gui.sheet.skill

import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.model.GameSystemType
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.kotlin.mock

class SkillEditArrayAdapterFactoryKoinTest : KoinTest {

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
    fun createSkillEditArrayAdapter_DnD5e_createDnD5eSkillEditArrayAdapter() {
        // arrange
        gameSystemHolder.gameSystemType = GameSystemType.DND5E

        // act
        val skillEditArrayAdapter =
            SkillEditArrayAdapterFactory().createSkillEditArrayAdapter(mock(), mock(), mock())

        // assert
        assertThat(skillEditArrayAdapter).isInstanceOf(DnD5eSkillEditArrayAdapter::class.java)
    }

    @Test
    fun createSkillEditArrayAdapter_DnDv35_createDnDv35SkillEditArrayAdapter() {
        // arrange
        gameSystemHolder.gameSystemType = GameSystemType.DNDV35

        // act
        val skillEditArrayAdapter =
            SkillEditArrayAdapterFactory().createSkillEditArrayAdapter(mock(), mock(), mock())

        // assert
        assertThat(skillEditArrayAdapter).isInstanceOf(DnDv35SkillEditArrayAdapter::class.java)
    }

    @Test
    fun createSkillEditArrayAdapter_Pathfinder_createDnDv35SkillEditArrayAdapter() {
        // arrange
        gameSystemHolder.gameSystemType = GameSystemType.PATHFINDER

        // act
        val skillEditArrayAdapter =
            SkillEditArrayAdapterFactory().createSkillEditArrayAdapter(mock(), mock(), mock())

        // assert
        assertThat(skillEditArrayAdapter).isInstanceOf(DnDv35SkillEditArrayAdapter::class.java)
    }

}