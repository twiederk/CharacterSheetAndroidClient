package com.android.ash.charactersheet.gui.sheet

import android.content.res.Resources
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.boc.model.GameSystemType
import org.koin.core.KoinComponent
import org.koin.core.inject

class FragmentPagerFactory : KoinComponent {

    private val gameSystemHolder: GameSystemHolder by inject()

    fun createSectionsPagerAdapter(fragmentManager: FragmentManager, resources: Resources): FragmentStatePagerAdapter {
        if (gameSystemHolder.gameSystemType == GameSystemType.DND5E) return DnD5eFragmentPager(fragmentManager, resources)
        return DnDv35FragmentPager(fragmentManager, resources)
    }

}
