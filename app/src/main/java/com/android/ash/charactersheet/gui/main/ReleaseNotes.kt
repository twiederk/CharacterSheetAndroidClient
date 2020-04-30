package com.android.ash.charactersheet.gui.main

import android.content.res.Resources
import com.android.ash.charactersheet.R

class ReleaseNotes(val resources: Resources) {

    private val releaseNotes = arrayOf( //
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
            resources.getString(R.string.release_notes_2_12_3)  //
    )

    fun getReleaseNotes(oldVersion: Int): String {
        return releaseNotes
                .filterIndexed { index, _ -> index >= oldVersion - 1 }
                .joinToString(separator = "") { it }
    }

}