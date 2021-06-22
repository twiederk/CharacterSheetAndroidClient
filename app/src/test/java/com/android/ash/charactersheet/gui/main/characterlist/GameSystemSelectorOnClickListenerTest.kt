package com.android.ash.charactersheet.gui.main.characterlist

import com.android.ash.charactersheet.boc.model.GameSystemType
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GameSystemSelectorOnClickListenerTest {

    @Test
    fun onClick() {
        // arrange
        val gameSystemSelector: GameSystemSelector = mock()

        // act
        GameSystemSelectorOnClickListener(gameSystemSelector, GameSystemType.DNDV35).onClick(mock())

        // assert
        verify(gameSystemSelector).switchGameSystem(GameSystemType.DNDV35)
    }

}