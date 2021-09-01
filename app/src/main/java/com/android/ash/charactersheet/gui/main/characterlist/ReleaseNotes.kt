package com.android.ash.charactersheet.gui.main.characterlist

import android.content.res.Resources
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ReleaseNotes(private val activity: AppCompatActivity) : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()

    private var showedReleaseNotes = false

    fun show() {
        if (isShowReleaseNotes()) {
            showReleaseNotes(getReleaseNotes())
            setOkButton()
        }
    }

    fun isShowReleaseNotes(): Boolean {
        if (gameSystemHolder.dndDbHelper?.isUpgrade == true
            || gameSystemHolder.dnd5eDbHelper?.isUpgrade == true
            || gameSystemHolder.pathfinderDbHelper?.isUpgrade == true
            && !showedReleaseNotes
        ) {
            showedReleaseNotes = true
            return true
        }
        return false
    }

    private fun showReleaseNotes(releaseNotes: String) {
        val releaseNotesView = activity.findViewById<View>(R.id.character_list_release_notes)
        releaseNotesView.visibility = View.VISIBLE
        val releaseNotesTextView: TextView = activity.findViewById(R.id.release_notes_list)
        releaseNotesTextView.text = releaseNotes
    }

    private fun setOkButton() {
        val okButton: Button = activity.findViewById(R.id.button_ok)
        okButton.setOnClickListener {
            val releaseNotesView = activity.findViewById<View>(R.id.character_list_release_notes)
            releaseNotesView.visibility = View.INVISIBLE
        }
    }


    internal fun getReleaseNotes(): String {
        val dbHelper: DBHelper? = gameSystemHolder.dndDbHelper
        val releaseNotes = createReleaseNotesArray(activity.resources)
        return releaseNotes
            .filterIndexed { index, _ -> index >= dbHelper?.oldVersion?.minus(1) ?: index }
            .joinToString(separator = "") { it }
    }

    private fun createReleaseNotesArray(resources: Resources): Array<String> = arrayOf(
        //
        resources.getString(R.string.release_notes_1_1_0),  //
        resources.getString(R.string.release_notes_1_2_0),  //
        resources.getString(R.string.release_notes_1_3_0),  //
        resources.getString(R.string.release_notes_1_4_0),  //
        resources.getString(R.string.release_notes_1_5_0),  //
        resources.getString(R.string.release_notes_1_6_0),  //
        resources.getString(R.string.release_notes_1_7_0),  //
        resources.getString(R.string.release_notes_1_8_0),  //
        resources.getString(R.string.release_notes_1_9_0),  //
        resources.getString(R.string.release_notes_1_10_0),  //
        resources.getString(R.string.release_notes_1_11_0),  //
        resources.getString(R.string.release_notes_1_11_1),  //
        resources.getString(R.string.release_notes_1_12_0),  //
        resources.getString(R.string.release_notes_1_12_1),  //
        resources.getString(R.string.release_notes_1_13_0),  //
        resources.getString(R.string.release_notes_1_14_0),  //
        resources.getString(R.string.release_notes_1_15_0),  //
        resources.getString(R.string.release_notes_1_16_0),  //
        resources.getString(R.string.release_notes_1_17_0),  //
        resources.getString(R.string.release_notes_1_18_0),  //
        resources.getString(R.string.release_notes_1_19_0),  //
        resources.getString(R.string.release_notes_1_19_1),  //
        resources.getString(R.string.release_notes_1_20_0),  //
        resources.getString(R.string.release_notes_1_21_0),  //
        resources.getString(R.string.release_notes_2_0_0),  //
        resources.getString(R.string.release_notes_2_0_1),  //
        resources.getString(R.string.release_notes_2_1_0),  //
        resources.getString(R.string.release_notes_2_2_0),  //
        resources.getString(R.string.release_notes_2_2_1),  //
        resources.getString(R.string.release_notes_2_3_0),  //
        resources.getString(R.string.release_notes_2_4_0),  //
        resources.getString(R.string.release_notes_2_5_0),  //
        resources.getString(R.string.release_notes_2_6_0),  //
        resources.getString(R.string.release_notes_2_6_1),  //
        resources.getString(R.string.release_notes_2_7_0),  //
        resources.getString(R.string.release_notes_2_8_0),  //
        resources.getString(R.string.release_notes_2_9_0),  //
        resources.getString(R.string.release_notes_2_10_0),  //
        resources.getString(R.string.release_notes_2_11_0),  //
        resources.getString(R.string.release_notes_2_11_1),  //
        resources.getString(R.string.release_notes_2_11_2),  //
        resources.getString(R.string.release_notes_2_11_3),  //
        resources.getString(R.string.release_notes_2_11_4),  //
        resources.getString(R.string.release_notes_2_11_5),  //
        resources.getString(R.string.release_notes_2_11_6),  //
        resources.getString(R.string.release_notes_2_11_7),  //
        resources.getString(R.string.release_notes_2_11_8),  //
        resources.getString(R.string.release_notes_2_11_9),  //
        resources.getString(R.string.release_notes_2_12_0),  //
        resources.getString(R.string.release_notes_2_12_1),  //
        resources.getString(R.string.release_notes_2_12_2),  //
        resources.getString(R.string.release_notes_2_12_3),  //
        resources.getString(R.string.release_notes_3_0_0), //
        resources.getString(R.string.release_notes_3_1_0), //
        resources.getString(R.string.release_notes_3_1_1), //
        resources.getString(R.string.release_notes_3_1_2), //
        resources.getString(R.string.release_notes_3_1_3), //
        resources.getString(R.string.release_notes_3_1_4), //
        resources.getString(R.string.release_notes_3_1_5), //
        resources.getString(R.string.release_notes_3_1_6), //
        resources.getString(R.string.release_notes_3_1_7), //
        resources.getString(R.string.release_notes_3_2_0), //
        resources.getString(R.string.release_notes_3_3_0), //
        resources.getString(R.string.release_notes_3_3_1), //
        resources.getString(R.string.release_notes_3_4_0), //
        resources.getString(R.string.release_notes_3_5_0), //
        resources.getString(R.string.release_notes_3_5_1), //
        resources.getString(R.string.release_notes_4_0_0), //
        resources.getString(R.string.release_notes_4_1_0), //
        resources.getString(R.string.release_notes_4_2_0), //
        resources.getString(R.string.release_notes_4_3_0), //
        resources.getString(R.string.release_notes_4_4_0), //
        resources.getString(R.string.release_notes_4_5_0), //
        resources.getString(R.string.release_notes_4_6_0), //
    )


}