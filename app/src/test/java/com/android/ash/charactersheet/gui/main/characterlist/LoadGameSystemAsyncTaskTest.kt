package com.android.ash.charactersheet.gui.main.characterlist

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import com.android.ash.charactersheet.R
import com.android.ash.charactersheet.boc.model.GameSystemType
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LoadGameSystemAsyncTaskTest {

    @Test
    fun getLoadGameSystemText_loadDnD5e_returnTextForDnD5e() {

        // arrange
        val resources: Resources = mock()
        whenever(resources.getString(R.string.character_list_wait_load_dnd5e)).doReturn("Load DnD 5e")
        val activity: AppCompatActivity = mock()
        whenever(activity.resources).doReturn(resources)

        // act
        val loadGameSystemText = LoadGameSystemAsyncTask(activity, mock(), GameSystemType.DND5E).loadGameSystemText

        // assert
        assertThat(loadGameSystemText).isEqualTo("Load DnD 5e")
    }

}