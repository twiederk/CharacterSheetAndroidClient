package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.gui.util.LogAppCompatActivity

/**
 * Activity containing the character creator
 */
class CharacterCreateActivity : LogAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_create)
        setToolbar()
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.character_create_title)
        supportActionBar?.setIcon(R.drawable.icon)
    }
    
}