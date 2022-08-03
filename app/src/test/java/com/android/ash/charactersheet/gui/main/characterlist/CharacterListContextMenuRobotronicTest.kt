package com.android.ash.charactersheet.gui.main.characterlist

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ListView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.appModule
import com.d20charactersheet.framework.boc.model.Character
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
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class CharacterListContextMenuRobotronicTest : KoinTest {

    private val firebaseAnalytics: FirebaseAnalytics by inject()
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
    fun onContextItemSelected_deleteCharacterSelected_characterDeleted() {
        // Arrange
        val gameSystem: GameSystem = mock()
        gameSystemHolder.gameSystem = gameSystem

        val character = createCharacter {
            race {
                racename = "myRace"
            }
            classLevels {
                classLevel {
                    classname = "myClass"
                }
            }
        }

        val listView: ListView = mock()
        val adapter: CharacterArrayAdapter = mock()
        whenever(adapter.getItem(any())).doReturn(character)
        whenever(listView.adapter).doReturn(adapter)
        val menuItem: MenuItem = mock()
        whenever(menuItem.itemId).doReturn(CharacterList.CONTEXT_MENU_DELETE_CHARACTER)
        val menuInfo: AdapterView.AdapterContextMenuInfo = mock()
        whenever(menuItem.menuInfo).doReturn(menuInfo)

        // Act
        CharacterListContextMenu().onContextItemSelected(menuItem, listView)

        // Assert
        verify(gameSystemHolder.gameSystem)?.deleteCharacter(character)
        verify(adapter).remove(character)
        val captor = argumentCaptor<Bundle>()
        verify(firebaseAnalytics).logEvent(any(), captor.capture())
        assertThat(captor.firstValue.getString("race_name")).isEqualTo("myRace")
        assertThat(captor.firstValue.getString("class_name")).isEqualTo("myClass")
    }

    @Test
    fun onCreateContextMenu_listEntryClicked_contextMenuWithDeleteEntry() {
        // Arrange
        val menu: ContextMenu = mock()
        val adapter: CharacterArrayAdapter = mock()
        whenever(adapter.getItem(any())).doReturn(Character().apply { name = "myCharacter" })
        val listView: ListView = mock()
        whenever(listView.adapter).doReturn(adapter)
        val menuInfo: AdapterView.AdapterContextMenuInfo = mock()

        // Act
        CharacterListContextMenu().onCreateContextMenu(menu, listView, menuInfo)

        // Assert
        verify(menu).setHeaderTitle("myCharacter")
        verify(menu).add(
            0,
            CharacterList.CONTEXT_MENU_DELETE_CHARACTER,
            0,
            R.string.character_list_delete
        )
    }

}