package com.android.ash.charactersheet.gui.main.characterlist

import com.android.ash.charactersheet.boc.model.GameSystemType
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

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