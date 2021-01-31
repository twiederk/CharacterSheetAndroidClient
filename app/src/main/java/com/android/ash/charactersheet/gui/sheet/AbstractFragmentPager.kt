package com.android.ash.charactersheet.gui.sheet

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

abstract class AbstractFragmentPager(
        private val pageFragments: Array<PageFragment>,
        private val pageTitles: Array<String>,
        fragmentManager: FragmentManager?
) : FragmentStatePagerAdapter(fragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return pageFragments[position]
    }

    override fun getCount(): Int {
        return pageFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return pageTitles[position]
    }

}