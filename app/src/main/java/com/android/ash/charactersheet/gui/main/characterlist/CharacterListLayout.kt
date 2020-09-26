package com.android.ash.charactersheet.gui.main.characterlist

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.main.characterlist.purchase.PurchaseDialog
import com.android.ash.charactersheet.gui.main.characterlist.purchase.PurchaseListener
import com.android.ash.charactersheet.gui.util.AdViewConfiguration
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterListLayout(private val characterListActivity: CharacterListActivity) : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()

    private val releaseNotes = ReleaseNotes()
    private val characterList = CharacterList(characterListActivity)


    fun layout() {
        characterListActivity.setContentView(R.layout.activity_character_list)
        setToolbar()
        setReleaseNotes()
        setOkButton()
        setFavoriteActionButton()
    }

    private fun setToolbar() {
        val toolbar: Toolbar = characterListActivity.findViewById(R.id.toolbar)
        characterListActivity.setSupportActionBar(toolbar)
        characterListActivity.supportActionBar?.title = createTitle()
        characterListActivity.supportActionBar?.setIcon(R.drawable.icon)
    }

    private fun createTitle(): String {
        return characterListActivity.resources.getString(R.string.character_list_title) + " - " + gameSystemHolder.gameSystem?.ruleService?.name
    }

    private fun setReleaseNotes() {
        if (releaseNotes.isShowReleaseNotes()) {
            showReleaseNotes(releaseNotes.getReleaseNotes(characterListActivity.resources))
        }
    }

    private fun showReleaseNotes(releaseNotes: String) {
        val releaseNotesView = characterListActivity.findViewById<View>(R.id.character_list_release_notes)
        releaseNotesView.visibility = View.VISIBLE
        val releaseNotesTextView: TextView = characterListActivity.findViewById(R.id.release_notes_list)
        releaseNotesTextView.text = releaseNotes
    }

    private fun setOkButton() {
        val okButton: Button = characterListActivity.findViewById(R.id.button_ok)
        okButton.setOnClickListener {
            val releaseNotesView = characterListActivity.findViewById<View>(R.id.character_list_release_notes)
            releaseNotesView.visibility = View.INVISIBLE
        }
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