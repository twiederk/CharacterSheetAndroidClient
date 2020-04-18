package com.android.ash.charactersheet.gui

import com.android.ash.charactersheet.CharacterHolder
import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.PreferenceServiceHolder
import com.google.firebase.analytics.FirebaseAnalytics
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class KoinConfigurationTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()
    private val preferenceServiceHolder: PreferenceServiceHolder by inject()
    private val characterHolder: CharacterHolder by inject()
    private val firebaseAnalytics: FirebaseAnalytics by inject()

    @Test
    fun koinConfiguration() {
        assertThat(gameSystemHolder).isNotNull
        assertThat(preferenceServiceHolder).isNotNull
        assertThat(characterHolder).isNotNull
        assertThat(firebaseAnalytics).isNotNull
    }
}