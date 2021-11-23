package com.android.ash.charactersheet.gui.sheet

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.boc.model.GameSystemType.DND5E
import com.android.ash.charactersheet.boc.model.GameSystemType.DNDV35
import com.android.ash.charactersheet.boc.model.GameSystemType.PATHFINDER
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject


class GameSystemLoaderEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferencesServiceHolder: PreferenceServiceHolder by inject()

    @Test
    fun connectDatabase_everythingIsFine_createDBHelper() {
        // arrange
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

        // act
        GameSystemLoader().connectDatabases(context)

        // assert
        assertThat(gameSystemHolder.dndv35DbHelper?.databaseName).isEqualTo(DNDV35.databaseName)
        assertThat(gameSystemHolder.pathfinderDbHelper?.databaseName).isEqualTo(PATHFINDER.databaseName)
        assertThat(gameSystemHolder.dnd5eDbHelper?.databaseName).isEqualTo(DND5E.databaseName)
        assertThat(preferencesServiceHolder.preferenceService).isNotNull
    }

    @Test
    fun load_everythingIsFine_loadGameSystem() {

        // Arrange
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val underTest = GameSystemLoader()
        underTest.connectDatabases(context)

        // Act
        val gameSystem = underTest.load(context, PATHFINDER)

        // Assert
        assertThat(gameSystem.isLoaded).isTrue
        assertThat(gameSystem.name).isEqualTo("Pathfinder")
        assertThat(gameSystemHolder.gameSystemType).isEqualTo(PATHFINDER)
    }

}