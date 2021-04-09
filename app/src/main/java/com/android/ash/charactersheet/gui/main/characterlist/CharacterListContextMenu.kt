package com.android.ash.charactersheet.gui.main.characterlist

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.android.ash.charactersheet.FBAnalytics
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.util.Logger
import com.d20charactersheet.framework.boc.model.Character
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class CharacterListContextMenu : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    fun onCreateContextMenu(menu: ContextMenu, view: View, menuInfo: ContextMenu.ContextMenuInfo) {
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val listView = view as ListView
        val adapter = listView.adapter as CharacterArrayAdapter
        menu.setHeaderTitle(adapter.getItem(info.position)?.name)
        menu.add(0, CharacterList.CONTEXT_MENU_DELETE_CHARACTER, 0, R.string.character_list_delete)
    }

    fun onContextItemSelected(menuItem: MenuItem, listView: ListView): Boolean {
        if (menuItem.itemId == CharacterList.CONTEXT_MENU_DELETE_CHARACTER) {
            deleteCharacter(getCharacter(menuItem, listView), listView)
        }
        return true
    }

    private fun getCharacter(menuItem: MenuItem, listView: ListView): Character? {
        val menuInfo = menuItem.menuInfo as AdapterView.AdapterContextMenuInfo
        val adapter = listView.adapter as CharacterArrayAdapter
        return adapter.getItem(menuInfo.position)
    }

    private fun deleteCharacter(character: Character?, listView: ListView) {
        Logger.debug("deleteCharacter")
        Logger.debug("character: $character")
        logEventCharacterDelete(character)
        gameSystemHolder.gameSystem?.deleteCharacter(character)
        val adapter = listView.adapter as CharacterArrayAdapter
        adapter.remove(character)
    }

    private fun logEventCharacterDelete(character: Character?) {
        val bundle = Bundle()
        bundle.putString(FBAnalytics.Param.RACE_NAME, character?.race?.name)
        bundle.putString(FBAnalytics.Param.CLASS_NAME, character?.characterClasses?.get(0)?.name)
        firebaseAnalytics.logEvent(FBAnalytics.Event.CHARACTER_DELETE, bundle)
    }

}