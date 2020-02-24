package com.android.ash.charactersheet.gui

import com.android.ash.charactersheet.GameSystemHolder
import com.android.ash.charactersheet.appModule
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject

class KoinConfigurationTest : KoinTest {

    private val gameSystemHolder: GameSystemHolder by inject()

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun koinConfiguration() {
        assertThat(gameSystemHolder).isNotNull
    }
}