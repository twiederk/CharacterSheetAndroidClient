package com.android.ash.charactersheet.gui.main.characterlist

import android.content.Intent
import android.widget.AdapterView
import android.widget.ListView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.service.AndroidImageServiceImpl
import com.android.ash.charactersheet.gui.sheet.CharacterSheetActivity
import com.d20charactersheet.framework.boc.service.GameSystem
import com.d20charactersheet.framework.dsl.createCharacter
import com.google.firebase.analytics.FirebaseAnalytics
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.kotlin.*
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class CharacterListRobolectricTest : KoinTest {

    private val characterHolder: CharacterHolder by inject()
    private val gameSystemHolder: GameSystemHolder by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<FirebaseAnalytics>()
    }


    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun initListView_initialize_adapterIsInitialized() {
        // Arrange
        val listView: ListView = mock()
        val activity: CharacterListActivity = mock()
        val underTest = CharacterList(activity)
        val gameSystem: GameSystem = mock()
        whenever(gameSystem.imageService).doReturn(AndroidImageServiceImpl(mock()))
        whenever(gameSystem.displayService).doReturn(mock())
        gameSystemHolder.gameSystem = gameSystem

        // Act
        underTest.initListView(listView)

        // Assert
        verify(listView).adapter = any()
        verify(listView).onItemClickListener = any()
        verify(listView).isTextFilterEnabled = true
        verify(listView).setOnCreateContextMenuListener(activity)
    }

    @Test
    fun onItemClick_characterClicked_openCharacter() {
        // Arrange
        val activity: CharacterListActivity = mock()
        val character = createCharacter { id = 1 }
        val adapter: AdapterView<*> = mock()
        whenever(adapter.getItemAtPosition(0)).doReturn(character)

        // Act
        CharacterList(activity).onItemClick(adapter, mock(), 0, 1L)

        // Assert
        assertThat(characterHolder.character).isEqualTo(character)
        argumentCaptor<Intent> {
            verify(activity).startActivity(capture())
            assertThat(firstValue.component?.className).isEqualTo(CharacterSheetActivity::class.qualifiedName)
        }
    }

}