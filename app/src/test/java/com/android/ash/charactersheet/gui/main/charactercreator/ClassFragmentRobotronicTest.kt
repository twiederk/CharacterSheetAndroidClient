package com.android.ash.charactersheet.gui.main.charactercreator

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.android.ash.charactersheet.appModule
import com.google.firebase.analytics.FirebaseAnalytics
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.KoinApiExtension
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
@MediumTest
class ClassFragmentRobotronicTest : KoinTest {

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

    @KoinApiExtension
    @Test
    fun onResume_screenViewIsLoggedInFirebase() {

        // act
        ClassComposeFragment().onResume()

        // Assert
        argumentCaptor<Bundle> {
            verify(firebaseAnalytics).logEvent(eq(FirebaseAnalytics.Event.SCREEN_VIEW), capture())
            assertThat(firstValue.getString(FirebaseAnalytics.Param.SCREEN_NAME)).isEqualTo("class_fragment")
            assertThat(firstValue.getString(FirebaseAnalytics.Param.SCREEN_CLASS)).isEqualTo("ClassComposeFragment")
        }

    }

}