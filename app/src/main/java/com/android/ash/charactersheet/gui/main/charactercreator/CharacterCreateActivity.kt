package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Activity containing the character creator
 */
class CharacterCreateActivity : LogAppCompatActivity(), KoinComponent {

    private val characterCreatorViewModel: CharacterCreatorViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        characterCreatorViewModel.reset()
        setContentView(R.layout.character_create)
    }

}