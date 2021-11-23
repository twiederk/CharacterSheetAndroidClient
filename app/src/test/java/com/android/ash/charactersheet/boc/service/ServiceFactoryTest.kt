package com.android.ash.charactersheet.boc.service

import android.content.Context
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.d20charactersheet.framework.boc.service.DnD5eRuleServiceImpl
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ServiceFactoryTest {

    @Test
    fun getRuleService_gameSystemIsDnD5e_returnDnD5eRuleService() {

        // act
        val ruleService = ServiceFactory().getRuleService(GameSystemType.DND5E)

        // assert
        assertThat(ruleService).isInstanceOf(DnD5eRuleServiceImpl::class.java)
    }

    @Test
    fun getDisplayService_gameSystemIsDnD5e_returnDnD5eDisplayService() {

        // arrange
        val context: Context = mock()
        whenever(context.resources).doReturn(mock())

        // act
        val displayService = ServiceFactory().getDisplayService(GameSystemType.DND5E, context)

        // assert
        assertThat(displayService).isInstanceOf(DnD5eAndroidDisplayService::class.java)
    }
}