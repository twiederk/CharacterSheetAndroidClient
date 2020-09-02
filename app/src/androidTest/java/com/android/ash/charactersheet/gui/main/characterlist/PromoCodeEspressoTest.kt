package com.android.ash.charactersheet.gui.main.characterlist

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.dac.dao.sql.sqlite.DBHelper
import com.android.ash.charactersheet.gui.main.characterlist.promocode.PromoCode
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.After
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class PromoCodeEspressoTest : KoinTest {

    private val gameSystemHolder by inject<GameSystemHolder>()

    private lateinit var scenario: ActivityScenario<CharacterListActivity>

    @After
    fun after() {
        scenario.close()
    }


    @Test
    fun onGameSystemLoaded_updateOfVersionLower3_displayPromoCodeDialog() {
        // Arrange
        gameSystemHolder.gameSystem = null
        val dbHelper: DBHelper = mock()
        whenever(dbHelper.isUpgrade).doReturn(true)
        whenever(dbHelper.oldVersion).doReturn(PromoCode.VERSION_CODE_WITHOUT_INAPP_PAYMENT)

        scenario = ActivityScenario.launch(CharacterListActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Act
        scenario.onActivity { activity ->
            gameSystemHolder.dndDbHelper = dbHelper
            activity.promoCode.execute(activity)
        }

        // Assert
        onView(withId(R.id.promo_activate_code_button)).check(matches(isDisplayed()))
        onView(withId(R.id.promo_done_button)).check(matches(isDisplayed()))
    }

}