package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.appModule
import com.google.firebase.analytics.FirebaseAnalytics
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class AbilityScoresFragmentRobotronicTest : KoinTest {

    private val firebaseAnalytics: FirebaseAnalytics by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
        declareMock<FirebaseAnalytics>()
    }


    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun onResume_screenViewIsLoggedInFirebase() {

        // act
        AbilityScoresComposeFragment().onResume()

        // assert
        argumentCaptor<Bundle> {
            verify(firebaseAnalytics).logEvent(eq(FirebaseAnalytics.Event.SCREEN_VIEW), capture())
            assertThat(firstValue.getString(FirebaseAnalytics.Param.SCREEN_NAME)).isEqualTo("ability_scores_fragment")
            assertThat(firstValue.getString(FirebaseAnalytics.Param.SCREEN_CLASS)).isEqualTo("AbilityScoresComposeFragment")
        }

    }

}