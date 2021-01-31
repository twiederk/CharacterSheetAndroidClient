package com.android.ash.charactersheet.gui.sheet

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class ComingSoonPageFragmentEspressoTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()

    @Test
    fun onCreateView() {

        // arrange
        gameSystemHolder.gameSystem = mock()

        // act
        launchFragmentInContainer<ComingSoonPageFragment>(themeResId = R.style.AppTheme)

        // assert
        onView(withId(R.id.page_coming_soon_text_view)).check(matches(withText("Coming Soon")))
    }

}