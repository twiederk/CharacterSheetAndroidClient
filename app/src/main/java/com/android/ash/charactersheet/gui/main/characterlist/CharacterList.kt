package com.android.ash.charactersheet.gui.main.characterlist

import android.content.Intent
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.service.AndroidImageService
import com.android.ash.charactersheet.gui.sheet.CharacterSheetActivity
import com.android.ash.charactersheet.gui.util.Logger.debug
import com.d20charactersheet.framework.boc.model.Character
import com.d20charactersheet.framework.boc.util.CharacterComparator
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class CharacterList(private val characterListActivity: CharacterListActivity) : AdapterView.OnItemClickListener, KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val characterHolder: CharacterHolder by inject()

    companion object {
        const val CONTEXT_MENU_DELETE_CHARACTER = 10
    }

    fun initListView(listView: ListView) {
        val gameSystem = gameSystemHolder.gameSystem
        val adapter = CharacterArrayAdapter(characterListActivity, gameSystem?.displayService, gameSystem?.imageService as AndroidImageService, R.layout.listitem_character, gameSystem.allCharacters)
        adapter.sort(CharacterComparator())
        listView.onItemClickListener = this
        listView.adapter = adapter
        listView.isTextFilterEnabled = true
        listView.setOnCreateContextMenuListener(characterListActivity)
    }

    override fun onItemClick(listView: AdapterView<*>, view: View, position: Int, id: Long) {
        val character = listView.getItemAtPosition(position) as Character
        debug("character: $character")

        characterHolder.character = character

        val intent = Intent(characterListActivity, CharacterSheetActivity::class.java)
        characterListActivity.startActivity(intent)
    }

}