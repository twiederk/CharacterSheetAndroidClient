package com.android.ash.charactersheet.gui.main.characterlist

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.main.characterlist.purchase.PurchaseDialog
import com.android.ash.charactersheet.gui.main.characterlist.purchase.PurchaseListener
import com.android.ash.charactersheet.gui.util.AdViewConfiguration
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CharacterListLayout(private val characterListActivity: CharacterListActivity) : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()

    private val gameSystemSelector = GameSystemSelector(characterListActivity)
    private val releaseNotes = ReleaseNotes(characterListActivity)
    private val characterList = CharacterList(characterListActivity)


    fun layout() {
        characterListActivity.setContentView(R.layout.activity_character_list)
        setToolbar()
        releaseNotes.show()
        gameSystemSelector.show(characterListActivity.findViewById(R.id.character_list_view))
        setFavoriteActionButton()
    }

    private fun setToolbar() {
        val toolbar: Toolbar = characterListActivity.findViewById(R.id.toolbar)
        characterListActivity.setSupportActionBar(toolbar)
        characterListActivity.supportActionBar?.title = createTitle()
        characterListActivity.supportActionBar?.setIcon(R.drawable.icon)
    }

    private fun createTitle(): String {
        return characterListActivity.resources.getString(R.string.character_list_title) + " - " + gameSystemHolder.gameSystemType?.getName()
    }


    private fun setFavoriteActionButton() {
        val favoriteActionButton: FloatingActionButton = characterListActivity.findViewById(R.id.favorite_action_button)
        favoriteActionButton.setOnClickListener(PurchaseListener(characterListActivity, PurchaseDialog(AlertDialog.Builder(characterListActivity))))
    }

    fun resumeLayout() {
        AdViewConfiguration().setAdView(characterListActivity)
        characterList.initListView(characterListActivity.findViewById(android.R.id.list))
    }

}