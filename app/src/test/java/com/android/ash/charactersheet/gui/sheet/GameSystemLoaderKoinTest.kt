package com.android.ash.charactersheet.gui.sheet

import com.android.ash.charactersheet.appModule
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.d20charactersheet.framework.boc.service.DnD5eRuleServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

class GameSystemLoaderKoinTest : KoinTest {

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
    fun getRuleService_gameSystemIsDnD5e_returnDnD5eRuleService() {

        // act
        val ruleService = GameSystemLoader().getRuleService(GameSystemType.DND5E)

        // assert
        assertThat(ruleService).isInstanceOf(DnD5eRuleServiceImpl::class.java)
    }

}