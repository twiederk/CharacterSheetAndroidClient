package com.android.ash.charactersheet.gui

import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.android.ash.charactersheet.billing.Billing
import com.android.ash.charactersheet.billing.MessageDisplay
import com.google.firebase.analytics.FirebaseAnalytics
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class KoinConfigurationInstrumentedTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferenceServiceHolder: PreferenceServiceHolder by inject()
    private val characterHolder: CharacterHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()
    private val billing: Billing by inject()
    private val messageDisplay: MessageDisplay by inject()

    @Test
    fun koinConfiguration() {
        assertThat(gameSystemHolder).isNotNull
        assertThat(preferenceServiceHolder).isNotNull
        assertThat(characterHolder).isNotNull
        assertThat(firebaseAnalytics).isNotNull
        assertThat(billing).isNotNull
        assertThat(messageDisplay).isNotNull
    }
}